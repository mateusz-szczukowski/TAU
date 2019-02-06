package seleniumbdd;

import org.openqa.selenium.*;

public class LogInPage extends Page
{   
    public LogInPage(WebDriver driver)
    {
        super(driver);
    }
    
    public LogInPage open()
    {
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        
        return this;
    }
    
    public LogInPage setEmail(String email)
    {
        element = driver.findElement(By.id("email"));
        element.sendKeys(email);
        
        return this;
    }
    
    public LogInPage setPassword(String password)
    {
        element = driver.findElement(By.id("passwd"));
        element.sendKeys(password);
        
        return this;
    }
    
    public Page submit()
    {
        element = driver.findElement(By.id("SubmitLogin"));
        element.click();
        
        if (isLoggedIn())
        {
            return new Page(driver);
        }
        
        return this;
    }
}
