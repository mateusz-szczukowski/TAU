package seleniumbdd;

import cucumber.api.java.en.*;
import java.util.concurrent.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

public class AutomationPracticeTest
{    
    private static WebDriver driver;
    private LogInPage logInPage;
    private RegisterPage registerPage;
    
    @Given("^Selenium$")
    public void selenium() throws Throwable 
    {
        if (driver == null)
        {
            System.setProperty("webdriver.chrome.driver", "./bin/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);   
        }
    }
    
    @When("^I open login page$")
    public void i_open_login_page() throws Throwable 
    {
        logInPage = new LogInPage(driver);
        logInPage.logOutIfLoggedIn();
        logInPage.open();
    }
    
    @When("^I enter email as \"([^\"]*)\"$")
    public void i_enter_email_as(String email) throws Throwable 
    {
        logInPage.setEmail(email);
    }

    @When("^I enter password as \"([^\"]*)\"$")
    public void i_enter_password_as(String password) throws Throwable 
    {
        logInPage.setPassword(password);
    }

    @Then("^Login should work$")
    public void login_should_work() throws Throwable 
    {
        Page resultPage;
        
        resultPage = logInPage.submit();
        assertTrue(resultPage.isLoggedIn());
    }    
    
    @Then("^Login should fail$")
    public void login_should_fail() throws Throwable 
    {
        Page resultPage;
        
        resultPage = logInPage.submit();
        assertFalse(resultPage.isLoggedIn());
    }
    
    @When("^I open register page$")
    public void i_open_register_page() throws Throwable 
    {
        registerPage = new RegisterPage(driver);
        registerPage.logOutIfLoggedIn();
        registerPage.open();
    }

    @When("^I enter registration email as \"([^\"]*)\"$")
    public void i_enter_registration_email_as(String email) throws Throwable 
    {
        registerPage.setEmail(email);
    }

    @When("^I enter registration password as \"([^\"]*)\"$")
    public void i_enter_registration_password_as(String password) throws Throwable 
    {
        registerPage.setPassword(password);
    }
    
    @When("^I enter first name as \"([^\"]*)\"$")
    public void i_enter_first_name_as(String firstName) throws Throwable 
    {
        registerPage.setFirstName(firstName);
    }
    
    @When("^I enter last name as \"([^\"]*)\"$")
    public void i_enter_last_name_as(String lastName) throws Throwable 
    {
        registerPage.setLastName(lastName);
    }

    @When("^I enter address as \"([^\"]*)\"$")
    public void i_enter_address_as(String address) throws Throwable 
    {
        registerPage.setAddress(address);
    }

    @When("^I enter city as \"([^\"]*)\"$")
    public void i_enter_city_as(String city) throws Throwable 
    {
        registerPage.setCity(city);
    }

    @When("^I select default state$")
    public void i_select_default_state() throws Throwable 
    {
        registerPage.setDefaultState();
    }

    @When("^I enter post code as \"([^\"]*)\"$")
    public void i_enter_post_code_as(String postCode) throws Throwable 
    {
        registerPage.setPostCode(postCode);
    }

    @When("^I enter phone number as \"([^\"]*)\"$")
    public void i_enter_phone_number_as(String phoneNumber) throws Throwable 
    {
        registerPage.setPhoneNumber(phoneNumber);
    }
    
    @Then("^Registration should work$")
    public void registration_should_work() throws Throwable 
    {
        Page resultPage;
        
        resultPage = registerPage.submit();
        assertTrue(resultPage.isLoggedIn());
    }
}
