package day3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Demo {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://saucelabs.com/request-demo");
        WebElement inputEmail = driver.findElement(By.id("Email"));
        inputEmail.sendKeys("hantp@gmail.com");
        WebElement inputCompany = driver.findElement(By.name("Company"));
        inputCompany.sendKeys("Vnext");
//        WebElement inputPhone = driver.findElement(By.xpath("//input[@id='Phone']"));
//        inputPhone.sendKeys("0379093127");
//        WebElement pullInterest = driver.findElement(By.xpath("//select[contains(@name,'Country')]"));
//        pullInterest.click();
        WebElement areaComment = driver.findElement(By.xpath("//textarea[@id=\"Sales_Contact_Comments__c\"]"));
        areaComment.sendKeys("This is the test content");
        WebElement checkbox = driver.findElement(By.xpath("//input[@id='mktoCheckbox_47208_0']"));
        checkbox.click();
        WebElement btnSubmit = driver.findElement(By.xpath("//button[@class='mktoButton']"));
        btnSubmit.click();
        WebElement msg = driver.findElement(By.xpath("//div[@class='mktoErrorMsg']"));
        msg.isEnabled();
        System.out.printf("Noi dung msg la: " + msg.getText());




        driver.quit();
    }
}
