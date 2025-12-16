package day4;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import vn.devpro.assignment67.utils.WaitElement;

public class Demo5 {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://saucelabs.com/request-demo");
            WebElement inputInterest = WaitElement.visible(driver, By.xpath("//select[@id=\"Solution_Interest__c\"]"),20);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].value='Crash & Error Reporting';", inputInterest);
            Thread.sleep(5000);
        }catch (Exception e){
            System.out.println("loi: "+e.getMessage());
        }finally {
            driver.quit();
        }
    }
}
