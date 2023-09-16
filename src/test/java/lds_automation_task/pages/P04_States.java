package lds_automation_task.pages;

import lds_automation_task.Hooks;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class P04_States {
    /* ___States List Page___ */


    /* _Elements addresses_ */

    // URL of States List page
    public final String URL = "http://beta.lds-stg.com:30/mylab/states/list";

    // open state form button
    @FindBy(css = "div.card-options>button.btn-primary")
    public WebElement openStateFormButton;

    // Add State button
    @FindBy(xpath = "(//form/button)[1]")
    public WebElement addStateButton;

    // Table body container
    @FindBy(css = "#vgt-table>tbody>tr>td>span")
    public List<WebElement> statesListSpans;

    //Range Error message
    @FindBy(className = "error")
    public WebElement rangeErrorMessage;

    //State Name Edittext
    @FindBy(xpath = "(//form/div/input)[1]")
    public WebElement stateNameET;

    /* _Messages addresses_ */

    // State added successful message
    // Incorrect format warning message
    @FindBy(className = "Vue-Toastification__toast-body")
    public WebElement statesAlertMessage;

    public P04_States() {
        PageFactory.initElements(Hooks.Browser.getDriver(), this);
    }
}
