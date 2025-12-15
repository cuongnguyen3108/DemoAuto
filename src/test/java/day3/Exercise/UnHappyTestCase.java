package day3.Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import vn.devpro.assignment67.utils.ElementValidate;
import vn.devpro.assignment67.utils.WaitElement;

import java.time.Duration;
import java.util.List;

public class UnHappyTestCase {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://saucelabs.com/request-demo");

        WebElement inputEmail = WaitElement.getElementVisible(driver, By.xpath("//input[@id=\"Email\"]"), 5);
        inputEmail.sendKeys("john.doe@yourcompany.com");
        WebElement btnSubmit = WaitElement.getElementVisible(driver, By.xpath("//button[@class=\"mktoButton\"]"), 5);
        btnSubmit.click();

        String msgEmail = ElementValidate.validate(inputEmail, "Email");
        if (msgEmail.contains("Must be valid email")) {
            System.out.println(msgEmail);
            driver.close();
            driver.quit();
            return;
        } else {
            System.out.println(msgEmail);
        }
        WebElement inputFirstName = WaitElement.getElementVisible(driver, By.xpath("//input[@id=\"FirstName\"]"), 15);
        inputFirstName.sendKeys("nguyen");
        WebElement inputLastName = WaitElement.getElementVisible(driver, By.xpath("//input[@id=\"LastName\"]"), 5);
        inputLastName.sendKeys("cuong");
        WebElement inputPhone = WaitElement.getElementVisible(driver, By.xpath("//input[@id=\"Phone\"]"), 5);
//        inputPhone.sendKeys("0379093127");

        WebElement selectCountry = WaitElement.getElementVisible(driver, By.xpath("//option[@value='Vietnam']"), 15);
        selectCountry.click();

        WebElement inputCompany = WaitElement.getElementVisible(driver, By.xpath("//input[@id=\"Company\"]"), 5);
        inputCompany.sendKeys("Vnback");

        WebElement selectInterest = WaitElement.getElementVisible(driver, By.xpath("//option[@value=\"Scalable Test Automation\"]"), 5);
        selectInterest.click();

        WebElement areaComment = WaitElement.getElementVisible(driver, By.xpath("//textarea[@id=\"Sales_Contact_Comments__c\"]"), 5);
        areaComment.sendKeys("This is the test content");

        WebElement checkbox = WaitElement.getElementVisible(driver,By.xpath("//label[@id=\"LblmktoCheckbox_47208_0\"]"), 15);
        checkbox.click();


        btnSubmit.click();

        String msgFirstName = ElementValidate.validate(inputFirstName, "FirstName");
        System.out.println(msgFirstName);
        String msgLastName =ElementValidate.validate(inputLastName, "LastName");
        System.out.println(msgLastName);
        String msgPhone = ElementValidate.validate(inputPhone, "Phone");
        System.out.println(msgPhone);
        String msgCountry = ElementValidate.validate(selectCountry, "Country");
        System.out.println(msgCountry);
        String msgCompany = ElementValidate.validate(inputCompany, "Company");
        System.out.println(msgCompany);
        String msgInterest = ElementValidate.validate(selectInterest, "Interest");
        System.out.println(msgInterest);
        driver.close();
        driver.quit();
    }


}
