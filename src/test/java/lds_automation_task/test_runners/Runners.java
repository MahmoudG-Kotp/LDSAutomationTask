package lds_automation_task.test_runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/main/resources/features",
        glue = "lds_automation_task",
        tags = "@LISTest",
        plugin = {"pretty",
                "html:target/cucumber.html",
                "json:target/cucumber.json",
                "junit:target/cukes.xml",
                "rerun:target/rerun.xml",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
class Runners extends AbstractTestNGCucumberTests {

}
