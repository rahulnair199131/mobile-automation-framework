package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import config.ConfigManager;
import driver.DriverFactory;
import driver.DriverManager;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class TestHooks {

    @Before
    public void setUp() {

        if ("android".equalsIgnoreCase(ConfigManager.getPlatform())) {

            DriverManager.setDriver(
                    DriverFactory.createAndroidDriver()
            );

        } else if ("ios".equalsIgnoreCase(ConfigManager.getPlatform())) {

            DriverManager.setDriver(
                    DriverFactory.createIOSDriver()
            );
        }
    }

    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver())
                    .getScreenshotAs(OutputType.BYTES);

            scenario.attach(
                    screenshot,
                    "image/png",
                    "Failure Screenshot"
            );
        }

        DriverManager.quitDriver();
    }
}