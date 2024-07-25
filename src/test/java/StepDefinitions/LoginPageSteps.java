
package StepDefinitions;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import org.testng.Assert;

import Hooks.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import utilities.ExcelReader;
import utilities.configReader;

public class LoginPageSteps{

    String url;
    String invalidUrl;
    configReader cp;
    Properties prop;
	int rownumber;
	 public String sheetName;

      
    private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
    public static ResourceBundle config =ResourceBundle.getBundle("config");
    
    
    @Given("User launches the browser")
    public void user_launches_the_browser() {

    }
    @When("User gives correct website link")
    public void user_gives_correct_website_link() {
        url = config.getString("validURL");
         DriverFactory.getDriver().get(url);
    }
    @Then("user is navigated to the home page of website")
    public void user_is_navigated_to_the_home_page_of_website() {
        Assert.assertEquals(loginPage.pageTitle(),"OrangeHRM");
    }
    @When("user gives invalid link")
    public void user_gives_invalid_link() throws IOException {
        //invalidUrl = config.getString("invalidURL");
    	loginPage.checkInvalURL();
    }
    @Then("user lands on invalid link")
    public void user_receives_page_not_found_error() throws IOException {
    	 
             int expectedStatusCode = 404; // Assuming 404 for page not found
             int actualStatusCode = loginPage.checkInvalURL(); // Modify checkInvalURL to return the status code

             Assert.assertEquals(expectedStatusCode, actualStatusCode);

         }
     
    @Then("HTTP response >= {int}. Then the link is broken")
    public void http_response_then_the_link_is_broken(Integer int1) throws IOException, InterruptedException {
    //loginPage.brokenLinksOnPage(config.getString("validURL"));
    	loginPage.verifyBrokenLinks();
    }
    @Then("user should see correct spellings in all fields")
    public void user_should_see_correct_spellings_in_all_fields() {
            loginPage.spellCheckLoginPage();
    }
    @Then("user should see logo on the right side")
    public void user_should_see_logo_on_the_right_side() throws InterruptedException {
            Boolean locationFlag = loginPage.LogoAlignment();
            Assert.assertTrue(locationFlag, "The logo is not aligned to the right side");
    }
    @Then("user should see username in gray color")
    public void user_should_see_username_in_gray_color() throws InterruptedException {
        Assert.assertTrue(loginPage.textColor());
    }

    @Given("user enters credentials from {string} and {int}")
    public void user_enters_credentials_from_and(String sheetName, Integer rownumber) throws IOException, InterruptedException {
    	
		ExcelReader sheetreader = new ExcelReader();

			List<Map<String, String>> data = sheetreader.getTestDataFromSheet(sheetName);
			 Map<String, String> userData = data.get(rownumber);
			    System.out.println("User Data from Excel: " + userData);
			    
			    String username = userData.get("username");
			    String password = userData.get("password");
			    
			loginPage.enterUserName(username);
			
		//	Thread.sleep(2000);
			loginPage.enterPassword(password);
    }
    @When("user clicks login")
    public void user_clicks_login() {
    	loginPage.loginButtonClick();
    }

    @Then("user is logged in")
    public void user_is_logged_in() {
       
    }

}