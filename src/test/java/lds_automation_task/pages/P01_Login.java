package lds_automation_task.pages;

import lds_automation_task.Hooks;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class P01_Login {

    /* ___Login Page___ */


    /* _Elements addresses_ */

    // URL of login page
    public final String URL = "http://beta.lds-stg.com:30/signin";

    // Email editbox
    @FindBy(css = "input[name='email']")
    public WebElement emailET;

    // Password editbox
    @FindBy(css = "input[name='password']")
    public WebElement passwordET;

    // SignIn button
    @FindBy(css = "button[type='submit']")
    public WebElement signInButton;

    /* _Messages addresses_ */

    // Error message
    @FindBy(className = "Vue-Toastification__toast-body")
    public WebElement loginAlertMessage;

    // Incorrect email format warning message
    public String getEmailETWarningMessage() {
        return emailET.getAttribute("validationMessage");
    }

    public P01_Login() {
        PageFactory.initElements(Hooks.Browser.getDriver(), this);
    }
}
