package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;

public class LoginSteps {

    private LoginPage loginPage;
    private HomePage homePage;

    public LoginSteps() {
        loginPage = new LoginPage();
        homePage = new HomePage();
    }

    @Given("the user is on the login screen")
    public void userIsOnLoginScreen() {
        loginPage.dismissCompatibilityPopupIfDisplayed();
        Assert.assertTrue(loginPage.isUsernameDisplayed());
    }

    @When("the user logs in with valid credentials")
    public void loginWithValidCredentials() {
        loginPage.login("standard_user", "secret_sauce");
    }

    @When("the user logs in with invalid credentials")
    public void loginWithInvalidCredentials() {
        loginPage.login("invalid_user", "invalid_password");
    }

    @Then("the home screen should be displayed")
    public void homeScreenShouldBeDisplayed() {
        Assert.assertTrue(homePage.isProductsDisplayed());
    }

    @Then("an error message should be displayed")
    public void errorMessageShouldBeDisplayed() {
        Assert.assertTrue(loginPage.isLoginErrorDisplayed());
    }

    @When("the user logs out")
    public void userLogsOut() {
        homePage.openMenu();
        homePage.clickLogout();
    }

    @Then("the login screen should be displayed")
    public void loginScreenShouldBeDisplayed() {
        Assert.assertTrue(loginPage.isUsernameDisplayed());
    }
}