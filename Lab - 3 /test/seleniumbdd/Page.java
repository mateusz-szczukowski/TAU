package seleniumbdd;

import org.openqa.selenium.*;

public class Page
{
    protected WebDriver driver;
    protected WebElement element;

    public Page(WebDriver driver)
    {
        this.driver = driver;
    }
    
    public Page logOutIfLoggedIn()
    {
        driver.get("http://automationpractice.com");
        
        if (isLoggedIn())
        {
            element = driver.findElement(By.className("logout"));
            element.click();
        }
        
        return this;
    }
    
    public boolean isLoggedIn()
    {
        return driver.findElements(By.className("logout")).size() == 1;
    }
}
