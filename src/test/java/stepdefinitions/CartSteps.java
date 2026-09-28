package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.CheckoutPage;
import pages.HomePage;

public class CartSteps {

    CheckoutPage checkoutPage;

    @When("the user adds Sauce Labs Backpack to the cart")
    public void userAddsBackpackToCart() {
        HomePage homePage = new HomePage();
        homePage.selectBackpack();
        homePage.openCart();
    }

    @Then("the checkout screen should be displayed")
    public void checkoutScreenShouldBeDisplayed() {
        checkoutPage  = new CheckoutPage();
        Assert.assertTrue(checkoutPage.isCheckoutDisplayed());
    }


}