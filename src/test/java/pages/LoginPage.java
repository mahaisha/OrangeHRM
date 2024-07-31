
package pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.RuleMatch;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.PageFactory;

import Hooks.DriverFactory;
import utilities.configReader;


public class LoginPage extends DriverFactory {
   
    String invalidUrl;
    String validUrl;
    
    
    public static ResourceBundle config =ResourceBundle.getBundle("config");
    configReader cp;
    Properties prop;

    

    //locators or Object Repository
    private By userName = By.name("username");
    private By passwordLocator = By.name("password");
    private By loginButton = By.xpath("//button[@type='submit']");
    private By logoImg = By.xpath("//div[@class='orangehrm-login-logo']");
    private By forgotPwdLink = By.linkText("Forgot your password? ");
    private By pageNotFoundError = By.xpath("//span[@jsselect='heading']");
    private By reloadButtonPageError = By.id("reload-button");
    
  //constructor
    public LoginPage( ) {
    	PageFactory.initElements(driver, this);
    }
    
    public static void getURL(String URL) {
    	driver.get(URL);
    }
    
    public int checkInvalURL() throws IOException {
         invalidUrl = config.getString("invalidURL");
        URL url = new URL(invalidUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("HEAD");
        connection.connect();

        int statusCode = connection.getResponseCode();
        if (statusCode ==200) {
        	
        	System.out.println("the valid URL is: " + url + "-" + statusCode);
        }
        if (statusCode == HttpURLConnection.HTTP_NOT_FOUND ) {
        	
        	System.out.println("The invalid URL is: " + url + " #statusCode: " + statusCode);
       
        }
        connection.disconnect();
        return statusCode;
    }

    public void verifyBrokenLinks() throws IOException, InterruptedException {
   
Thread.sleep(5000);
        List<WebElement> links = driver.findElements(By.tagName("a"));

        System.out.println("Total links are " + links.size());

        for (int i = 0; i < links.size(); i++) {
          WebElement element = links.get(i);

          String url = element.getAttribute("href");
          checkLinks(url);
        }
        }
          private void checkLinks(String link) throws IOException {
		
             URL url = new URL(link);
             HttpURLConnection connection = (HttpURLConnection) url.openConnection();
             connection.setConnectTimeout(3000);
             connection.connect();

             int statusCode = connection.getResponseCode();

             if (statusCode == HttpURLConnection.HTTP_NOT_FOUND ) {
             	
             	System.out.println( url + "link is broken" + connection.getResponseMessage());
            
             }
             System.out.println(url + "Status Code: " + connection.getResponseMessage());

          }
        // methods/actions
    public void enterUserName(String username){
       driver.findElement(userName).sendKeys(username);
    }
    public boolean isReloadButtonVisible(){
        boolean flag = false;
        WebElement reload = driver.findElement(reloadButtonPageError);
        if(reload.isDisplayed()){
            return true;
        }
        return flag;
    }
    public void enterPassword(String password){
        driver.findElement(passwordLocator).sendKeys(password);
    }
    public void loginButtonClick() {

        driver.findElement(loginButton).click();
    }
    public void forgotPasswordLinkClick()
    {
        driver.findElement(forgotPwdLink).click();
    }
    public String getURL(){

        return driver.getCurrentUrl();
    }
    public boolean logoIsPresent(){

        return driver.findElement(logoImg).isDisplayed();
    }
    public String pageTitle(){

        return driver.getTitle();
    }

    public String getPageNotFoundErrorMsg(){
        return driver.findElement(pageNotFoundError).getText().trim();
    }
    public void spellCheckLoginPage(){
        WebElement allPage = driver.findElement(By.tagName("body"));
        String PageText = allPage.getText();
        JLanguageTool languageTool = new JLanguageTool(new AmericanEnglish());
        try {
            List<RuleMatch> errors = languageTool.check(PageText);
            if(errors.isEmpty()){
                System.out.println("No spelling errors on the page");
            }
            else{
                System.out.println("Please find the below spelling errors");
                for(RuleMatch eachError:errors){
                    System.out.println("Potential typo "+eachError.getSentence());
                    System.out.println("possible match to be replaced "+eachError.getSuggestedReplacements());
                    System.out.println("-------------------------------------------------------------");
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
  
    	public Boolean LogoAlignment() throws InterruptedException {
            boolean logoLocation = false;
            Thread.sleep(1000);
            WebElement logo = driver.findElement(logoImg);
            // Get the element's X coordinate
            int xCoord = logo.getLocation().getX();
            // Get the viewport width
            Dimension dim = driver.manage().window().getSize();
            int viewportWidth = dim.getWidth();
            // Determine if the element is on the right side
            boolean isElementOnRightSide = xCoord > (viewportWidth / 2);
            if (isElementOnRightSide) {
                System.out.println("The element is positioned on the right side of the webpage.");
                logoLocation = true;
            } else {
                System.out.println("The element is not positioned on the right side of the webpage.");
                logoLocation = false;
            }
            return logoLocation;
        }
    public Boolean textColor() throws InterruptedException {
        Thread.sleep(5000);
        boolean flag = false;
        WebElement color = driver.findElement(userName);
        String color1 = color.getCssValue("color");
        String hexaValue = Color.fromString(color1).asHex();
        System.out.println(hexaValue);
        if(hexaValue.equalsIgnoreCase("#64728c")){
            return flag = true;
        }
        else{
        return flag; }
    }

}
   
