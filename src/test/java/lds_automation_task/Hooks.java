package lds_automation_task;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;

public class Hooks {

    //Opens browser (order = 0) to give it a prior to run before each test scenario
    @Before(order = 0)
    public void openBrowser() {
        Browser.open();
    }

    @After(order = 0)
    public void closeBrowser() throws InterruptedException {
        Browser.quit();
    }

    @After(order = 1)
    public void takeScreenshotOnFailure(Scenario runningScenario) {
        //Currently running scenario will be passed automatically from the TestNG
        if (runningScenario.isFailed()) {
            //Convert driver to takesSS interface to call ss function and output it to bytes
            byte[] source = ((TakesScreenshot) Browser.getDriver()).getScreenshotAs(OutputType.BYTES);
            runningScenario.attach(source, "image/png", "Failure Screenshot at >> " + runningScenario.getName());
        }
    }

    public static class Browser {
        private static WebDriver mainDriver;

        public static WebDriver getDriver() {
            return mainDriver;
        }

        private static void open() {
            WebDriverManager.chromedriver().setup();
            mainDriver = new ChromeDriver();
            mainDriver.manage().window().maximize();
            implicitWait(5);
        }

        public static void navigateTo(String Link) {
            //Navigate to specific webpage
            mainDriver.navigate().to(Link);
        }

        public static WebDriver switchTo(int windowIndex) {
            return mainDriver.switchTo().window(new ArrayList<>(mainDriver.getWindowHandles()).get(windowIndex));
        }

        public static void implicitWait(int seconds) {
            mainDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
        }

        public static WebDriverWait explicitWait(int seconds) {
            return new WebDriverWait(mainDriver, Duration.ofSeconds(seconds));
        }

        public static void closeWindow(int windowIndex) {
            switchTo(windowIndex).close();
            switchTo(windowIndex - 1);
        }

        private static void quit() throws InterruptedException {
            //Sleep used to give time for human observation
            Thread.sleep(2000);
            mainDriver.quit();
        }

    }
}