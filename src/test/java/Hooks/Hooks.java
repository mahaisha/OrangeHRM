package Hooks;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterMethod;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import utilities.configReader;


public class Hooks extends DriverFactory{

	private static DriverFactory driverFactory;

	private static configReader configReader;
	static Properties prop;
	
	  public static Logger log = LogManager.getLogger();
	
	@BeforeAll(order=0)
	public static void getProperty()
	{
		configReader = new configReader();
		prop = configReader.init_prop();
	}
	@Before
	public static void before() throws Throwable {
		String browser = configReader.getBrowserType();
		System.out.println(browser);
		//Initialize driver from driver factory
		driverFactory = new DriverFactory();
		driverFactory.init_driver(browser);

	}

	
		
	@AfterMethod
	@After(order = 1)
	public void tearDown(Scenario scenario) {
		if(scenario.isFailed()) {
			//take screenshot:
			String screenshotName = scenario.getName().replaceAll(" ","_");
			byte [] sourcePath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);
			
		}
	}
}
