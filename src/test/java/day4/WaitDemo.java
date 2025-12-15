package day4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import vn.devpro.assignment67.utils.WaitElement;

import java.time.Duration;

public class WaitDemo {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)) ;
        try {
            driver.get("https://saucelabs.com/request-demo");

            WebElement inputEmail = WaitElement.getElementVisible(driver,By.xpath("//input[@id=\"Email\"]"),20);
//            WebElement inputEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"Email1\"]")));
            inputEmail.sendKeys("john.doe@yourcompany.com");

        }catch (Exception e){
            System.out.println("loi: "+e.getMessage());
        }
        finally {
//            driver.quit();
        }
    }
}
