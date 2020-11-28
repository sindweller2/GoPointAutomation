package gopointautomation;

import org.openqa.selenium.*;

public class AddPoint
{
    public Selenium Selenium = new Selenium();

    public Executer Executer = new Executer();
    
    public XML XML = new XML();

    public String Status = "Successfully allocated";
    
    public Integer x = 0;
    
    public Integer y = 0;
    
    public void Run(String[] args)
    {
        try
        {
            Selenium.Start();
            
            Selenium.driver.get("https://sso.gojek.co.id/cas/login?service=http%3A%2F%2F10.14.1.117%2Fusers%2Fservice");
            
            Selenium.driver.findElement(By.id("username")).clear();
            
            Selenium.driver.findElement(By.id("username")).sendKeys(XML.ReadXML("username"));
            
            Selenium.driver.findElement(By.id("password")).clear();
            
            Selenium.driver.findElement(By.id("password")).sendKeys(Executer.TOTPCode(XML.ReadXML("token")));
            
            Selenium.driver.findElement(By.name("submit")).click();
            
            Selenium.driver.findElement(By.id("lookup_identifier")).click();
            
            Selenium.driver.findElement(By.id("lookup_identifier")).clear();
            
            Selenium.driver.findElement(By.id("lookup_identifier")).sendKeys(args[2]);
            
            Selenium.driver.findElement(By.name("commit")).click();
            
            Selenium.driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
            
            Selenium.driver.findElement(By.linkText("Points")).click();

            for (int i = 0; i < Integer.parseInt(args[1]); i++)
            {
                Selenium.driver.findElement(By.id("points_allocation_amount")).click();
                
                Selenium.driver.findElement(By.id("points_allocation_amount")).clear();
                
                Selenium.driver.findElement(By.id("points_allocation_amount")).sendKeys("500");
                
                Selenium.driver.findElement(By.id("points_allocation_description")).clear();
                
                Selenium.driver.findElement(By.id("points_allocation_description")).sendKeys("testing");
                
                Selenium.driver.findElement(By.name("commit")).click();
            
                 if (Selenium.driver.getPageSource().contains(Status))
                {
                    System.out.println((i + 1) + ". Success");
                    
                    x++;
                }
                 else
                {
                    System.out.println((i + 1) + ". Fail");
                    
                    y++;
                }
            }

            System.out.println("\nResult : " + (x * 500) + " point(s) Success");
            
            System.out.println("\nResult : " + (y * 500) + " point(s) Fail\n");
            
            Selenium.driver.findElement(By.linkText("Sign Out")).click();
            
            Selenium.Stop();
        }
        catch (Exception e)
        {
            
            System.out.println("\nException : " + e.getMessage());
            
            System.out.println("\nResult : " + (x * 500) + " point(s) Success");
            
            System.out.println("\nResult : " + (y * 500) + " point(s) Fail\n");
            
            Selenium.Stop();
        }
    }
}
