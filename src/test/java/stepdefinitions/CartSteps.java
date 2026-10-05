package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;

public class CartSteps {

    private CheckoutPage checkoutPage;
    private HomePage homePage;

    public CartSteps() {
        checkoutPage = new CheckoutPage();
        homePage = new HomePage();
    }

    @When("the user adds Sauce Labs Backpack to the cart")
    public void userAddsBackpackToCart() {
        homePage.selectBackpack();
        homePage.openCart();
    }

    @Then("the checkout screen should be displayed")
    public void checkoutScreenShouldBeDisplayed() {
        Assert.assertTrue(checkoutPage.isCheckoutDisplayed());
    }


}