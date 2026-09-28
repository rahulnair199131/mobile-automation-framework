package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {

    @AndroidFindBy(accessibility = "test-CHECKOUT")
    @iOSXCUITFindBy(accessibility = "test-CHECKOUT")
    private WebElement checkoutButton;

    public void clickCheckout() {
        checkoutButton.click();
    }

    public boolean isCheckoutDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOf(checkoutButton)
        ).isDisplayed();
    }

}