package Hooks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverFactory {

	
	public static WebDriver driver;
	public final static int TIMEOUT = 50;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	

	public void init_driver(String browser)
	{
		System.out.println("browser value is:" + browser);

		if(browser.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
			
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{	
			System.out.println("browser value is:" + browser);
			driver = new FirefoxDriver();
			
		}
		else if(browser.equalsIgnoreCase("edge"))
		{
			System.out.println("browser value is:" + browser);
			driver = new EdgeDriver();
			 
		}
		else {
			System.out.println("Please pass the correct browser value " + browser);
		}

	       driver.manage().deleteAllCookies();
		   driver.manage().window().maximize();
		   driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
	}
	
	public synchronized static WebDriver getDriver() {
		
		return tlDriver.get();
		
	}
	
	
	
	
}
 
