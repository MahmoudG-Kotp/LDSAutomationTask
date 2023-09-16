package lds_automation_task.pages;

import lds_automation_task.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class P02_Dashboard {

    /* ___Dashboard Page___ */


    /* _Constants_ */

    //Dashboard Main Tab Index
    public final static int DASHBOARD_MAIN_TAB_INDEX = 2

            //Users Management Main Tab Index
            , USERS_MANAGEMENT_MAIN_TAB_INDEX = 11

            //Branches Main Tab Index
            , BRANCHES_MAIN_TAB_INDEX = 14

            //State Side Tab Index
            , STATES_BRANCHES_SIDE_TAB_INDEX = 1;


    /* _Elements addresses_ */

    //URL of Dashboard page
    public final String URL = "http://beta.lds-stg.com:30/dashboard";

    //Tabs of sidebar >> to get (Dashboard, Users Management, States) tabs
    public WebElement getSideTabElement(int index) {
        return Hooks.Browser.getDriver().findElement(By.cssSelector(".main-sidebar-body>ul>li:nth-child(" + index + ")"));
    }

    public WebElement getSideTabElement(int mainTabIndex, int sideTabIndex) {
        return Hooks.Browser.getDriver().findElement(By.cssSelector(".main-sidebar-body>ul>li:nth-child(" + mainTabIndex + ")>ul>li:nth-child(" + sideTabIndex + ")"));
    }

    public P02_Dashboard() {
        PageFactory.initElements(Hooks.Browser.getDriver(), this);
    }
}
