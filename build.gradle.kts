plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.seleniumhq.selenium:selenium-java:4.27.0")
    implementation("io.appium:java-client:9.3.0")

    testImplementation("io.cucumber:cucumber-java:7.18.1")
    testImplementation("io.cucumber:cucumber-testng:7.18.1")

    implementation("com.aventstack:extentreports:5.1.2")

    testImplementation("org.testng:testng:7.11.0")
    testImplementation("io.rest-assured:rest-assured:5.5.7")
}

tasks.test {
    useTestNG()

    setScanForTestClasses(false)

    include(
        "**/*Test.class",
        "**/*Tests.class",
        "**/*TestCase.class",
        "**/TestRunner.class"
    )

    systemProperty(
        "cucumber.filter.tags",
        System.getProperty("cucumber.filter.tags")
    )

    systemProperty(
        "platform",
        System.getProperty("platform")
    )
}
