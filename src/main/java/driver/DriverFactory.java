package driver;

import config.ConfigManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    public static AndroidDriver createAndroidDriver() {

        UiAutomator2Options options =
                new UiAutomator2Options();

        options.setDeviceName(
                ConfigManager.getDeviceName()
        );

        options.setUdid(
                ConfigManager.getDeviceUdid()
        );

        options.setApp(
                ConfigManager.getAppPath()
        );

        options.setAppPackage("com.swaglabsmobileapp");
        options.setAppActivity("com.swaglabsmobileapp.MainActivity");

        try {
            return new AndroidDriver(
                    new URL("http://127.0.0.1:4723"),
                    options
            );

        } catch (MalformedURLException e) {
            throw new RuntimeException(
                    "Invalid Appium server URL", e);
        }
    }

    public static IOSDriver createIOSDriver() {

        XCUITestOptions options = new XCUITestOptions();

        options.setAutomationName("XCUITest");

        options.setDeviceName(
                ConfigManager.getDeviceName()
        );

        options.setApp(
                ConfigManager.getAppPath()
        );

        options.setBundleId(
                ConfigManager.getProperty("ios.bundle.id")
        );

        try {
            return new IOSDriver(
                    new URL("http://127.0.0.1:4723"),
                    options
            );

        } catch (MalformedURLException e) {
            throw new RuntimeException(
                    "Invalid Appium server URL", e
            );
        }
    }
}