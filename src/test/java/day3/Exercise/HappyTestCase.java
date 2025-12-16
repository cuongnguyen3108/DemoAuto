package day3.Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import vn.devpro.assignment67.utils.Ads;
import vn.devpro.assignment67.utils.ElementValidate;
import vn.devpro.assignment67.utils.WaitElement;

import java.util.Objects;

public class HappyTestCase {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://saucelabs.com/request-demo");
        String pageTitle1 = driver.getTitle();

        WebElement inputEmail = WaitElement.visible(driver, By.xpath("//input[@id=\"Email\"]"), 10);
        inputEmail.sendKeys("john.doe@yourcompany.com");
        WebElement btnSubmit = WaitElement.visible(driver, By.xpath("//button[@class=\"mktoButton\"]"), 10);
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
        WebElement inputFirstName = WaitElement.visible(driver, By.xpath("//input[@id=\"FirstName\"]"), 15);
        inputFirstName.sendKeys("nguyen");
        WebElement inputLastName = WaitElement.visible(driver, By.xpath("//input[@id=\"LastName\"]"), 10);
        inputLastName.sendKeys("cuong");
        WebElement inputPhone = WaitElement.visible(driver, By.xpath("//input[@id=\"Phone\"]"), 10);
        inputPhone.sendKeys("0379093127");

        WebElement selectCountry = WaitElement.visible(driver, By.xpath("//option[@value='Vietnam']"), 15);
        selectCountry.click();

        WebElement inputCompany = WaitElement.visible(driver, By.xpath("//input[@id=\"Company\"]"), 10);
        inputCompany.sendKeys("Vnback");

        WebElement selectInterest = WaitElement.visible(driver, By.xpath("//option[@value=\"Scalable Test Automation\"]"), 10);
        selectInterest.click();

        WebElement areaComment = WaitElement.visible(driver, By.xpath("//textarea[@id=\"Sales_Contact_Comments__c\"]"), 10);
        areaComment.sendKeys("This is the test content");

        WebElement checkbox = WaitElement.visible(driver, By.xpath("//label[@id=\"LblmktoCheckbox_47208_0\"]"), 15);
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

        Ads.close(driver, 10);

        WaitElement.waitFor(
                driver,
                ExpectedConditions.not(ExpectedConditions.titleIs(pageTitle1)),
                30
        );

        String pageTitle2 = driver.getTitle();
        if (Objects.equals(pageTitle2, "Thank you")) {
        System.out.println("Test case successfully");
        } else {
            System.out.println(pageTitle2);
        }
        driver.close();
        driver.quit();
    }

}
