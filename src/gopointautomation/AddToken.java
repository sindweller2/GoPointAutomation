package gopointautomation;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class AddToken {
    
    public String product = "";

    public Selenium Selenium = new Selenium();

    public Executer Executer = new Executer();

    public XML XML = new XML();

    public String randomstring = "openssl rand -base64 9";

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

            Selenium.driver.findElement(By.linkText("Tokens")).click();

            for (int i = 0; i < Integer.parseInt(args[1]); i++) 
            {
                Selenium.driver.findElement(By.id("points_token_allocation_token_type")).click();

                new Select(Selenium.driver.findElement(By.id("points_token_allocation_payment_type"))).selectByVisibleText(Executer.RandomPaymentType());

//                if (driver.findElement(By.id("points_token_allocation_payment_type")).equals("CASH"))
//                {
//                    new Select(driver.findElement(By.id("points_token_allocation_token_type"))).selectByVisibleText(Executer.RandomServiceCash());
//                }
//                else
//                {

                product = Executer.RandomServiceGoPay();
                
                new Select(Selenium.driver.findElement(By.id("points_token_allocation_token_type"))).selectByVisibleText(product);
                
                Selenium.driver.findElement(By.id("points_token_allocation_token_type")).getText();
//                }

                Selenium.driver.findElement(By.id("points_token_allocation_transaction_reference")).clear();

                Selenium.driver.findElement(By.id("points_token_allocation_transaction_reference")).sendKeys(Executer.ExecuteCommand(randomstring));

                Selenium.driver.findElement(By.id("points_token_allocation_transaction_amount")).clear();

                Selenium.driver.findElement(By.id("points_token_allocation_transaction_amount")).sendKeys("100000");

                Selenium.driver.findElement(By.name("commit")).click();

                if (Selenium.driver.getPageSource().contains(Status)) 
                {
                    System.out.println((i + 1) + ". " + product + " Success");

                    x++;
                } 
                else 
                {
                    System.out.println((i + 1) + ". " + product + " Fail");

                    y++;
                }
            }

            System.out.println("\nResult : " + x + " token(s) Success");

            System.out.println("\nResult : " + y + " token(s) Fail\n");

            Selenium.driver.findElement(By.linkText("Sign Out")).click();

            Selenium.Stop();
        } 
        catch (Exception e) 
        {
            System.out.println("\nException : " + e.getMessage());
            
            System.out.println("\nResult : " + x + " token(s) Success");

            System.out.println("\nResult : " + y + " token(s) Fail\n");

            Selenium.Stop();
        }
    }
}
