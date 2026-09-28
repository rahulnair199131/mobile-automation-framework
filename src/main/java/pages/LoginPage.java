package pages;


import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class LoginPage extends BasePage {

    @AndroidFindBy(accessibility = "test-Username")
    @iOSXCUITFindBy(accessibility = "test-Username")
    private WebElement username;

    @AndroidFindBy(accessibility = "test-Password")
    @iOSXCUITFindBy(accessibility = "test-Password")
    private WebElement password;

    @AndroidFindBy(accessibility = "test-LOGIN")
    @iOSXCUITFindBy(accessibility = "test-LOGIN")
    private WebElement loginButton;

    @AndroidFindBy(xpath = "//*[@text='Username and password do not match any user in this service.']")
    @iOSXCUITFindBy(xpath = "//*[@value='Username and password do not match any user in this service.']")
    private WebElement loginError;

    @AndroidFindBy(xpath = "//*[@text='OK']")
    @iOSXCUITFindBy(id = "//*[@value='OK']")
    private WebElement okButton;

    public void dismissCompatibilityPopupIfDisplayed() {
        try {
            if (okButton.isDisplayed()) {
                okButton.click();
            }
        } catch (Exception e) {
            // Popup not displayed
        }
    }



    public void enterUsername(String value) {
        dismissCompatibilityPopupIfDisplayed();
        username.sendKeys(value);
    }

    public void enterPassword(String value) {
        password.sendKeys(value);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public boolean isLoginErrorDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOf(loginError)
        ).isDisplayed();
    }

    public boolean isUsernameDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOf(username)
        ).isDisplayed();
    }
}