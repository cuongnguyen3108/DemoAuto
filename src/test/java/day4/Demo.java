package day4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Demo {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://saucelabs.com/request-demo");

        WebElement pullInterest = driver.findElement(By.xpath("//select[@id=\"Solution_Interest__c\"]"));
//        selectInterest.click();
        Select selectInterest = new Select(pullInterest);
//        selectInterest.selectByIndex(3);
//        selectInterest.selectByValue("Scalable Test Automation");
        selectInterest.selectByVisibleText("Sauce AI Agents ");
//        driver.quit();
    }
}
