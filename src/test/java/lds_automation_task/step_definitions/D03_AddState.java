package lds_automation_task.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lds_automation_task.Hooks;
import lds_automation_task.pages.P02_Dashboard;
import lds_automation_task.pages.P04_States;
import lds_automation_task.type_defintions.State;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class D03_AddState {
    private final P02_Dashboard dashboardPage = new P02_Dashboard();
    private final P04_States statesPage = new P04_States();
    private State enteredState;

    //Background
    @When("admin clicks on branches tab")
    public void clickBranchesTab() {
        dashboardPage.getSideTabElement(P02_Dashboard.BRANCHES_MAIN_TAB_INDEX).click();
    }

    @And("admin selects states tab")
    public void clickStatesTab() {
        dashboardPage.getSideTabElement(P02_Dashboard.BRANCHES_MAIN_TAB_INDEX, P02_Dashboard.STATES_BRANCHES_SIDE_TAB_INDEX).click();
    }

    @And("click open state form button")
    public void clickOpenStateButton() {
        statesPage.openStateFormButton.click();
    }

    //General Functions
    @When("admin sets {string} as state name")
    public void setsStateNameET(String stateName) {
        Hooks.Browser.explicitWait(2).until(ExpectedConditions.visibilityOf(statesPage.stateNameET));
        statesPage.stateNameET.sendKeys(stateName);
    }

    @And("click add state button")
    public void clickAddStateButton() {
        statesPage.addStateButton.click();
    }

    @Then("add state button won't be clickable")
    public void assertAddStateButtonStatus() {
        try {
            Hooks.Browser.explicitWait(1).until(ExpectedConditions.elementToBeClickable(statesPage.addStateButton));
            Assert.fail("Add state button clickable!");
        } catch (TimeoutException ignored) {
        }
    }

    @And("successful message of added state shown")
    public void assertAddedSuccessfullyMessage() {
        //Explicit wait till message shown if not fail the assertion
        try {
            Hooks.Browser.explicitWait(5).until(ExpectedConditions.textToBePresentInElement(statesPage.statesAlertMessage, "Added successfully !"));
        } catch (TimeoutException exception) {
            Assert.fail("The successful message didn't display");
        }
    }

    @And("state will be added successfully to the list")
    public void assertAdditionOfState() {
        boolean isStateExist = false;
        for (int stateCounter = 0; stateCounter < statesPage.statesListSpans.size(); stateCounter += 2) {
            String currentStateName = statesPage.statesListSpans.get(stateCounter).getText();
            if (currentStateName.equals(enteredState.getName())) {
                isStateExist = true;
                break;
            }
        }
        Assert.assertTrue(isStateExist);
    }

    @When("admin sets state name with {string} length")
    public void setValidStateName(String nameLength) {
        enteredState = new State(getRandomString(Integer.parseInt(nameLength)));
        Hooks.Browser.explicitWait(2).until(ExpectedConditions.visibilityOf(statesPage.stateNameET));
        statesPage.stateNameET.sendKeys(enteredState.getName());
    }

    @Then("state name field doesn't show error message")
    public void assertNoRangeErrorMessageShown() {
        try {
            Assert.assertFalse(statesPage.rangeErrorMessage.isDisplayed());
        } catch (NoSuchElementException ignored) {
            //Succeed if element not exist
        }
    }

    private String getRandomString(int nameLength) {
        // choose a Character random from this String
        String alphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of alphaNumeric
        StringBuilder randomStringBuilder = new StringBuilder(nameLength);

        for (int i = 0; i < nameLength; i++) {
            // generate a random number between
            // 0 to alphaNumeric variable length
            int index = (int) (alphaNumeric.length() * Math.random());

            // add Character one by one in end of randomStringBuilder
            randomStringBuilder.append(alphaNumeric.charAt(index));
        }
        return randomStringBuilder.toString();
    }

    //Scenario 2
    @Then("state name field shows warning message")
    public void assertNameETWarningMessage() {
        //Explicit wait till warning message shown if not fail the assertion
        try {
            Hooks.Browser.explicitWait(3).until(ExpectedConditions.attributeToBeNotEmpty(statesPage.stateNameET, "validationMessage"));
        } catch (TimeoutException exception) {
            Assert.fail("There's no warning message of state name edittext");
        }
    }

    //Scenario 3
    @Then("error message of existent state will be shown")
    public void assertExistentStateErrorMessage() {
        //Explicit wait till message shown if not fail the assertion
        try {
            Hooks.Browser.explicitWait(3).until(ExpectedConditions.textToBePresentInElement(statesPage.statesAlertMessage, "The name has already been taken."));
        } catch (TimeoutException exception) {
            Assert.fail("The error message didn't display");
        }
    }

    //Scenario 4
    @Then("state name field shows error message")
    public void assertRangeErrorMessageShown() {
        Assert.assertTrue(statesPage.rangeErrorMessage.isDisplayed());
    }
}
