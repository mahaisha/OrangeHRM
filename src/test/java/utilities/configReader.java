package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class configReader {

	private Properties prop;
	private static String browserType = null;
	
	public Properties init_prop() {
		
		prop = new Properties();
		try {
		
		FileInputStream input = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
		prop.load(input);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return prop;
	}
	
	

	public static void setBrowserType(String browser) {

		browserType = browser;

	}

	public  String getBrowserType() throws Throwable {

		if (browserType != null)

			return browserType;

		else

			throw new RuntimeException("browser not specified in the testng.xml");

	}
	
}
