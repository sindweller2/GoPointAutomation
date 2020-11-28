package gopointautomation;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class Selenium 
{
    public XML XML = new XML();
    
    public WebDriver driver;
    
    public String baseUrl;
    
    public StringBuffer verificationErrors = new StringBuffer();
    
    public void Start()
    {
        try
        {
            System.setProperty("webdriver.chrome.driver", XML.ReadXML("driver"));
            
            ChromeOptions options = new ChromeOptions();
            
            if (XML.ReadXML("ui").equals("false")) 
            {
                options.addArguments("headless");
            }

            driver = new ChromeDriver(options);
            
            baseUrl = "https://www.katalon.com/";
            
            driver.manage().window().fullscreen();
            
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
        catch (Exception e)
        {
            System.out.println("\nException : " + e.getMessage());
            
            Stop();
        }
    }

    public void Stop()
    {
        driver.quit();
        
        String verificationErrorString = verificationErrors.toString();
        
//        if (!"".equals(verificationErrorString))
//        {
//            fail(verificationErrorString);
//        }
    }    
}
