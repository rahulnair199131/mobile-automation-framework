# Mobile Automation Framework

## Overview

A mobile UI automation framework built using **Java 11, Appium, Selenium, TestNG and Cucumber BDD**, with support for Android and iOS.

The framework is designed around:
- Page Object Model (POM)
- Platform-specific locators with shared page classes
- Cucumber BDD
- TestNG execution
- Configuration-driven platform/device selection
- Explicit waits for synchronization
- Failure screenshots
- Smoke and regression tagging
- Extensible Android/iOS driver creation

## Technology Stack

- Java 11
- Gradle
- Appium 2
- Appium Java Client
- Selenium WebDriver
- TestNG
- Cucumber

## Framework Structure

```text
mobile-automation-framework/
├── apps/
│   ├── android/
│   │   └── SauceLabs.apk
│   └── ios/
│       └── SauceLabs.app
│
├── src/
│   ├── main/java/
│   │   ├── config/
│   │   ├── driver/
│   │   └── pages/
│   │
│   └── test/
│       ├── java/
│       │   ├── hooks/
│       │   ├── runners/
│       │   └── stepdefinitions/
│       │
│       └── resources/
│           ├── config/
│           ├── features/
│           └── testng.xml
│
└── build.gradle.kts
```

## Configuration

Configuration is maintained in:

```text
src/test/resources/config/config.properties
```

Example:

```properties
platform=android

android.device.name=emulator-5554
android.device.udid=emulator-5554
android.app.path=apps/android/SauceLabs.apk

ios.device.name=iPhone 15
ios.bundle.id=<actual_ios_bundle_id>
ios.app.path=apps/ios/SauceLabs.ipa

explicit.wait=10
```

The `platform` property acts as the default platform. It can be overridden at runtime for CI/CD execution using the `-Dplatform` JVM property.

## Prerequisites

Install/configure:

1. Java 11
2. Gradle (or use the Gradle wrapper)
3. Appium 2
4. Appium UiAutomator2 driver for Android
5. Appium XCUITest driver for iOS
6. Android SDK and an Android emulator/device
7. Xcode and an iOS simulator/device for local iOS execution
8. Git

For Android, verify the device is available:

```bash
adb devices
```

Start the Appium server before execution:

```bash
appium
```

## Running the Tests

### Android

Set:

```properties
platform=android
```

Make sure the Android emulator/device is running and Appium is available on:

```text
http://127.0.0.1:4723
```

Run:

```bash
./gradlew test
```

### iOS

Set:

```properties
platform=ios
```

Ensure the iOS simulator/device and Xcode/Appium environment are configured.

The framework uses IOSDriver with XCUITestOptions and a Simulator-compatible .app bundle configured through ios.app.path. The iOS bundle ID is configured separately in ios.bundle.id.

```bash
./gradlew test
```

## CI/CD Runtime Execution

Platform and Cucumber tags can be supplied at runtime without modifying the framework source code or configuration file.

### Android smoke

```bash
./gradlew test -Dplatform=android -Dcucumber.filter.tags="@smoke"
```

### Android regression

```bash
./gradlew test -Dplatform=android -Dcucumber.filter.tags="@regression"
```

### iOS smoke

```bash
./gradlew test -Dplatform=ios -Dcucumber.filter.tags="@smoke"
```

### iOS regression

```bash
./gradlew test -Dplatform=ios -Dcucumber.filter.tags="@regression"
```

The `platform` system property overrides the default `platform` value in `config.properties`.

## Test Scenarios

Current mobile scenarios include:

### Login

- Successful login
- Invalid login
- Logout

### Additional Flow

- Select Sauce Labs Backpack
- Add product to cart
- Proceed to checkout

## BDD and Tags

Tests are written using Cucumber feature files.

Examples:

```gherkin
@smoke
Scenario: Successful login
```

```gherkin
@regression
Scenario: Unsuccessful login
```

Tags can be selected at runtime using:

```bash
-Dcucumber.filter.tags="@smoke"
```

or:

```bash
-Dcucumber.filter.tags="@regression"
```

## Synchronization

Explicit waits are centralized through `BasePage` using `WebDriverWait`.

Example:

```java
wait.until(
    ExpectedConditions.visibilityOf(element)
);
```

This avoids relying on fixed sleeps and provides synchronization around elements that need to become available.

## Failure Handling

Cucumber hooks capture a screenshot when a scenario fails and attach it to the Cucumber report.

This provides visual evidence for failures without adding screenshot code to individual test steps.

## Android / iOS Design

The test steps are platform independent.

Platform-specific differences are handled through:
- Separate Android/iOS driver creation
- Platform-specific Appium PageFactory locators

Example:

```java
@AndroidFindBy(id = "android_locator")
@iOSXCUITFindBy(accessibility = "ios_locator")
private WebElement element;
```

This allows the same page object and Cucumber step to support both platforms where the user flow is the same.

## Design Decisions

### Page Object Model

Application interactions are separated from Cucumber step definitions.

- `LoginPage` handles login-screen interactions.
- `HomePage` handles home-screen interactions.
- `CheckoutPage` handles checkout-screen interactions.

### Driver Management

`DriverFactory` is responsible for creating platform-specific drivers.

`DriverManager` stores the active driver using `ThreadLocal`, allowing the framework to be extended for parallel execution.

### Hooks

Cucumber hooks handle driver setup and teardown automatically for each scenario.

### Configuration

Device/platform-specific values are kept outside Java code in `config.properties`.

The platform can also be overridden at runtime for CI/CD.

## Flakiness Considerations

The framework avoids fixed sleeps and uses explicit waits for element visibility.

Potential sources of flakiness include:
- Emulator/device performance
- App startup time
- Network-dependent application behavior
- Differences between local and cloud devices
- Platform-specific UI/locator differences
- Some iOS controls required tapping within a specific portion of the element's hit area. Platform-specific interaction handling was added where a standard click() did not reliably trigger the intended action, while Android retains the standard interaction.

The framework can be extended with retry handling, additional synchronization utilities, and device/cloud execution configuration if required.

## AI Usage

AI was used as a development assistant during framework creation for:
- Reviewing framework structure and design choices
- Assisting with Appium/TestNG/Cucumber configuration
- Suggesting maintainable Page Object patterns
- Reviewing synchronization and failure-handling approaches

## Reports

Cucumber HTML reporting is configured at:

```text
build/reports/cucumber.html
```

Failure screenshots are attached to failed Cucumber scenarios.

## Future Extensions

Potential future enhancements include:
- Cloud device execution
- ExtentReports integration
- API automation utilities and tests
- Additional utility classes as framework needs grow
- Parallel execution configuration
- CI/CD pipeline configuration
- Additional retry/flakiness handling
- Secure credential handling using environment variables or protected CI/CD variables
