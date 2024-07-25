package Hooks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverFactory {

	
	public WebDriver driver;
	public final static int TIMEOUT = 50;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	

	public WebDriver init_driver(String browser)
	{
		System.out.println("browser value is:" + browser);

		if(browser.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
			tlDriver.set(driver);
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{			
			driver = new FirefoxDriver();
			tlDriver.set(driver);
		}
		else if(browser.equalsIgnoreCase("edge"))
		{
			driver = new EdgeDriver();
			tlDriver.set(driver);  
		}
		else {
			System.out.println("Please pass the correct browser value " + browser);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		return getDriver();
	}
	
	public synchronized static WebDriver getDriver() {
		
		return tlDriver.get();
		
	}
	
	
	
	
}
 
