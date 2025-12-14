package day3.Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HappyTestCase {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://saucelabs.com/request-demo");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement inputEmail = driver.findElement(By.xpath("//input[@id=\"Email\"]"));
        inputEmail.sendKeys("john.doe@yourcompany.com");

        WebElement inputFirstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"FirstName\"]")));
        inputFirstName.sendKeys("nguyen");
        WebElement inputLastName = driver.findElement(By.xpath("//input[@id=\"LastName\"]"));
        inputLastName.sendKeys("cuong");
        WebElement inputPhone = driver.findElement(By.xpath("//input[@id=\"Phone\"]"));
        inputPhone.sendKeys("0379093127");
        WebElement selectCountry = driver.findElement(By.xpath("//option[@value='Vietnam']"));
        selectCountry.click();

        WebElement inputCompany = driver.findElement(By.xpath("//input[@id=\"Company\"]"));
        inputCompany.sendKeys("Vnback");

        WebElement selectInterest = driver.findElement(By.xpath("//option[@value=\"Scalable Test Automation\"]"));
        selectInterest.click();

        WebElement areaComment = driver.findElement(By.xpath("//textarea[@id=\"Sales_Contact_Comments__c\"]"));
        areaComment.sendKeys("This is the test content");

        WebElement checkbox = driver.findElement(By.xpath("//input[@id='mktoCheckbox_47208_0']"));
        checkbox.click();

        WebElement btnSubmit = driver.findElement(By.xpath("//button[@class='mktoButton']"));
        btnSubmit.click();

        System.out.println("Test case successfully");

        driver.quit();
    }

}
