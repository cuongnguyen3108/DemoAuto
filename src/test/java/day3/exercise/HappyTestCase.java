package day3.exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import vn.devpro.assignment67.utils.Ads;
import vn.devpro.assignment67.utils.ElementValidate;
import vn.devpro.assignment67.utils.WaitElement;

import java.util.Objects;

public class HappyTestCase {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://saucelabs.com/request-demo");
        By error = By.xpath("./ancestor::div[contains(@class,'mktoFieldWrap')]/div[contains(@class,'mktoError')]");

        WebElement inputEmail = WaitElement.visible(driver, By.xpath("//input[@id=\"Email\"]"), 10);
        inputEmail.sendKeys("john.doe@yourcompany.com");
        WebElement btnSubmit = WaitElement.visible(driver, By.xpath("//button[@class=\"mktoButton\"]"), 10);
        btnSubmit.click();
        String msgEmail = ElementValidate.validate(driver, inputEmail, "Email", error);
        if (msgEmail.contains("Must be valid email")) {
            System.out.println(msgEmail);
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

        WebElement checkbox = WaitElement.present(driver, By.xpath("//div[contains(@class,\"mktoCheckboxList\")]/input"), 20);
        checkbox.click();

        btnSubmit.click();
        String msgFirstName = ElementValidate.validate(driver, inputFirstName, "FirstName", error);
        System.out.println(msgFirstName);
        String msgLastName = ElementValidate.validate(driver, inputLastName, "LastName", error);
        System.out.println(msgLastName);
        String msgPhone = ElementValidate.validate(driver, inputPhone, "Phone", error);
        System.out.println(msgPhone);
        String msgCountry = ElementValidate.validate(driver, selectCountry, "Country", error);
        System.out.println(msgCountry);
        String msgCompany = ElementValidate.validate(driver, inputCompany, "Company", error);
        System.out.println(msgCompany);
        String msgInterest = ElementValidate.validate(driver, selectInterest, "Interest", error);
        System.out.println(msgInterest);
        Ads.close(driver, 15);

        String pageTitle2 = driver.getTitle();
        if (Objects.equals(pageTitle2, "Thank you")) {
            System.out.println("✅ Test case successfully");
        } else {
            System.out.println(pageTitle2);
        }
        driver.quit();
    }

}
