package seleniumbdd;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class RegisterPage extends Page
{
    public RegisterPage(WebDriver driver)
    {
        super(driver);
    }
    
    public RegisterPage open()
    {
        driver.get("http://automationpractice.com/index.php?controller=authentication");
        
        return this;
    }
    
    public RegisterPage setEmail(String email)
    {
        element = driver.findElement(By.id("email_create"));
        element.sendKeys(email);
        element = driver.findElement(By.id("SubmitCreate"));
        element.click();
        try { Thread.sleep(3000); } catch (InterruptedException e) { }
        
        return this;
    }
    
    public RegisterPage setPassword(String password)
    {
        element = driver.findElement(By.id("passwd"));
        element.sendKeys(password);
        
        return this;
    }
    
    public RegisterPage setFirstName(String firstName)
    {
        element = driver.findElement(By.id("customer_firstname"));
        element.sendKeys(firstName);
        
        return this;
    }
    
    public RegisterPage setLastName(String lastName)
    {
        element = driver.findElement(By.id("customer_lastname"));
        element.sendKeys(lastName);
        
        return this;
    }
    
    public RegisterPage setAddress(String address)
    {
        element = driver.findElement(By.id("address1"));
        element.sendKeys(address);
        
        return this;
    }

    public RegisterPage setCity(String city)
    {
        element = driver.findElement(By.id("city"));
        element.sendKeys(city);
        
        return this;
    }

    public RegisterPage setPostCode(String postCode)
    {
        element = driver.findElement(By.id("postcode"));
        element.sendKeys(postCode);
        
        return this;
    }

    public RegisterPage setDefaultState()
    {
        Select select;
        
        element = driver.findElement(By.id("id_state"));
        select = new Select(element);
        select.selectByIndex(1);
        
        return this;
    }

    public RegisterPage setPhoneNumber(String phoneNumber)
    {
        element = driver.findElement(By.id("phone_mobile"));
        element.sendKeys(phoneNumber);
        
        return this;
    }
    
    public Page submit()
    {
        element = driver.findElement(By.id("submitAccount"));
        element.click();
        
        return new Page(driver);
    }
}
