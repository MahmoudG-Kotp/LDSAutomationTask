package lds_automation_task.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lds_automation_task.Hooks;
import lds_automation_task.pages.P01_Login;
import lds_automation_task.pages.P02_Dashboard;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class D01_Login {
    private final P01_Login loginPage = new P01_Login();

    //Background
    @Given("admin navigates to login page")
    public void navigateToLoginPage() {
        Hooks.Browser.navigateTo(loginPage.URL);
    }

    //General functions
    @When("admin sets {string} as email")
    public void setEmailET(String email) {
        loginPage.emailET.sendKeys(email);
    }

    @And("admin sets {string} as password")
    public void setPasswordET(String password) {
        loginPage.passwordET.sendKeys(password);
    }

    @And("click login button")
    public void clickLoginButton() {
        loginPage.signInButton.click();
    }

    @Then("error message shown")
    public void assertErrorMessage() {
        //Explicit wait till message shown if not fail the assertion
        try {
            Hooks.Browser.explicitWait(3).until(ExpectedConditions.textToBePresentInElement(loginPage.loginAlertMessage, "Invalid Email Or Password"));
        } catch (TimeoutException exception) {
            Assert.fail("The error message didn't display");
        }
    }

    //Scenario 2
    @Then("email field shows warning message")
    public void assertEmailETWarningMessage() {
        //Explicit wait till warning message shown if not fail the assertion
        try {
            Hooks.Browser.explicitWait(5).until(ExpectedConditions.attributeToBeNotEmpty(loginPage.emailET, "validationMessage"));
        } catch (TimeoutException exception) {
            Assert.fail("There's no warning message of email edittext");
        }
    }

    //Scenario 4
    @Then("login proceed successfully")
    public void assertLoginProcedure() {
        try {
            //Wait too long in case of there is network issues
            Hooks.Browser.explicitWait(10).until(ExpectedConditions.urlToBe(new P02_Dashboard().URL));
        } catch (TimeoutException exception) {
            Assert.fail("Login didn't proceeding");
        }
    }

    @And("dashboard selected from sidebar")
    public void assertSidebarSelection() {
        Assert.assertTrue(new P02_Dashboard().getSideTabElement(P02_Dashboard.DASHBOARD_MAIN_TAB_INDEX).isSelected());
    }
}
