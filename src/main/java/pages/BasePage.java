package pages;

import driver.DriverManager;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
        this.driver = DriverManager.getDriver();

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        PageFactory.initElements(
                new AppiumFieldDecorator(driver),
                this
        );
    }
}