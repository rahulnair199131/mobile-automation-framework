package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    @AndroidFindBy(xpath = "//*[@text='PRODUCTS']")
    @iOSXCUITFindBy(xpath = "//*[@text='PRODUCTS']")
    private WebElement productsLabel;

    @AndroidFindBy(accessibility = "test-Menu")
    @iOSXCUITFindBy(accessibility = "test-Menu")
    private WebElement menuButton;

    @AndroidFindBy(accessibility = "test-LOGOUT")
    @iOSXCUITFindBy(accessibility = "test-LOGOUT")
    private WebElement logoutButton;

    @AndroidFindBy(xpath = "//*[@text='Sauce Labs Backpack']")
    @iOSXCUITFindBy(xpath = "//*[@text='Sauce Labs Backpack']")
    private WebElement backpack;

    @AndroidFindBy(accessibility = "test-Cart")
    @iOSXCUITFindBy(accessibility = "test-Cart")
    private WebElement cartButton;

    public boolean isProductsDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOf(productsLabel)
        ).isDisplayed();
    }

    public void openMenu() {
        menuButton.click();
    }

    public void clickLogout() {
        logoutButton.click();
    }

    public void selectBackpack() {
        backpack.click();
    }
    public void openCart() {
        cartButton.click();
    }

}