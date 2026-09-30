package pages;

import config.ConfigManager;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    @AndroidFindBy(xpath = "//*[@text='PRODUCTS']")
    @iOSXCUITFindBy(xpath = "//*[@value='PRODUCTS']")
    private WebElement productsLabel;

    @AndroidFindBy(accessibility = "test-Menu")
    @iOSXCUITFindBy(accessibility = "test-Menu")
    private WebElement menuButton;

    @AndroidFindBy(accessibility = "test-LOGOUT")
    @iOSXCUITFindBy(accessibility = "test-LOGOUT")
    private WebElement logoutButton;

    @AndroidFindBy(xpath = "//*[@text='Sauce Labs Backpack']")
    @iOSXCUITFindBy(xpath = "//*[@value='Sauce Labs Backpack']/../../XCUIElementTypeOther[contains(@name,'ADD')]//XCUIElementTypeOther[@name='ADD TO CART']")
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

        if ("android".equalsIgnoreCase(ConfigManager.getPlatform())) {

            menuButton.click();

        } else if ("ios".equalsIgnoreCase(ConfigManager.getPlatform())) {

            int xOffset = menuButton.getSize().getWidth() / 3;
            int yOffset = menuButton.getSize().getHeight() / 3;

            new Actions(driver)
                    .moveToElement(menuButton, xOffset, yOffset)
                    .click()
                    .perform();
        }
    }

    public void clickLogout() {
        logoutButton.click();
    }

    public void selectBackpack() {
        backpack.click();
    }
    public void openCart() {

        if ("android".equalsIgnoreCase(ConfigManager.getPlatform())) {

            cartButton.click();

        } else if ("ios".equalsIgnoreCase(ConfigManager.getPlatform())) {

            int xOffset = cartButton.getSize().getWidth() / 3;
            int yOffset = cartButton.getSize().getHeight() / 3;

            new Actions(driver)
                    .moveToElement(cartButton, xOffset, yOffset)
                    .click()
                    .perform();
        }
    }

}