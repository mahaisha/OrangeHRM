package testrunners;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import utilities.configReader;


@io.cucumber.testng.CucumberOptions(
		features= {"src/test/resources/Features"},
		tags = ("@VerifyDescriptive"),
		glue= {"StepDefinitions","Hooks"},

		plugin= {"pretty","html:target/cucumber-reports/reports.html",
				"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
				"json:target/cucumber-reports/cucumber.json",
				"junit:target/cucumber-reports/cucumber.xml",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"rerun:target/rerun.txt"},
				publish=true
			
)

public class MyTestRunner extends AbstractTestNGCucumberTests{
    @Override
    @DataProvider(parallel = false)
    public Object [][] scenarios() {
	return super.scenarios();
}
    @BeforeTest
	@Parameters({ "browser" })
	public void defineBrowser(String browser) throws Throwable {

		configReader.setBrowserType(browser);


	}
}
	
	
	

