package day6.exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import vn.devpro.assignment67.utils.Ads;
import vn.devpro.assignment67.utils.ElementValidate;
import vn.devpro.assignment67.utils.WaitElement;

public class BookYouDemoHereTextCase {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://saucelabs.com/request-demo");

    }

    @Test
    public void testHappilyWithValidCredentials() {
        WebElement inputEmail = WaitElement.present(driver, By.xpath("//input[@id=\"Email\"]"), 10);
        inputEmail.sendKeys("john.doe@yourcompany.com");
        Assert.assertEquals(inputEmail.getAttribute("value").trim(), "john.doe@yourcompany.com", "Incorrect Email entered");
        WebElement inputFirstName = WaitElement.present(driver, By.xpath("//input[@id=\"FirstName\"]"), 10);
        inputFirstName.sendKeys("nguyen");
        Assert.assertEquals(inputFirstName.getAttribute("value").trim(), "nguyen", "Incorrect FirstName entered");

        WebElement inputLastName = WaitElement.visible(driver, By.xpath("//input[@id=\"LastName\"]"), 10);
        inputLastName.sendKeys("cuong");
        Assert.assertEquals(inputLastName.getAttribute("value").trim(), "cuong", "Incorrect LastName entered");

        WebElement inputPhone = WaitElement.visible(driver, By.xpath("//input[@id=\"Phone\"]"), 10);
        inputPhone.sendKeys("0331393427");
        Assert.assertEquals(inputPhone.getAttribute("value").trim(), "0331393427", "Incorrect Phone entered");

        WebElement selectCountry = WaitElement.visible(driver, By.id("Country"), 10);
        Select country = new Select(selectCountry);
        country.selectByValue("Albania");
        Assert.assertEquals(selectCountry.getAttribute("value").trim(), "Albania", "Incorrect Country entered");

        WebElement inputCompany = WaitElement.visible(driver, By.xpath("//input[@id=\"Company\"]"), 10);
        inputCompany.sendKeys("Vnback");
        Assert.assertEquals(inputCompany.getAttribute("value").trim(), "Vnback", "Incorrect Company entered");

        WebElement selectInterest = WaitElement.visible(driver, By.id("Solution_Interest__c"), 10);
        Select interest = new Select(selectInterest);
        interest.selectByValue("Scalable Test Automation");
        Assert.assertEquals(selectInterest.getAttribute("value").trim(), "Scalable Test Automation", "Incorrect Interest entered");

        WebElement areaComment = WaitElement.visible(driver, By.xpath("//textarea[@id=\"Sales_Contact_Comments__c\"]"), 10);
        areaComment.sendKeys("This is the test content");
        Assert.assertEquals(areaComment.getAttribute("value").trim(), "This is the test content", "Incorrect Interest entered");

        WebElement checkbox = WaitElement.present(driver, By.xpath("//div[contains(@class,'mktoCheckboxList')]//input"), 25);
        ElementValidate.clearAndClickCheckbox(checkbox);


        WebElement btnSubmit = WaitElement.clickable(driver, By.xpath("//button[@class='mktoButton']"), 10);
        btnSubmit.click();
        Ads.close(driver, 15);

        WaitElement.waitFor(driver,ExpectedConditions.urlToBe(
                "https://saucelabs.com/thank-you-contact"
        ),25);
        Assert.assertEquals(driver.getCurrentUrl(), "https://saucelabs.com/thank-you-contact", "Page redirection failed");
        Assert.assertEquals(driver.getTitle(), "Thank you", "Page redirection failed");

        String  label= WaitElement.present(driver, By.xpath("//h2[contains(text(),'Got it!')]"), 15).getText();
        Assert.assertEquals(label, "Got it!", "Page redirection failed");
        WebElement imgPage = WaitElement.present(driver, By.xpath("//img[@alt=\"Saucelabs Image\"]"), 10);
        Assert.assertTrue(!imgPage.isDisplayed(), "Page redirection failed");


    }

    @Test
    public void testUnHappilyWithInvalidInterest() {
        driver.get("https://saucelabs.com/request-demo");
        Assert.assertEquals(driver.getCurrentUrl(), "https://saucelabs.com/request-demo", "Did not go to the correct page");
        Assert.assertEquals(driver.getTitle(), "Request a Sauce Labs Demo", "Did not go to the correct page");

        WebElement inputEmail = WaitElement.present(driver, By.xpath("//input[@id=\"Email\"]"), 10);
        inputEmail.sendKeys("john.doe@yourcompany.com");
        Assert.assertEquals(inputEmail.getAttribute("value").trim(), "john.doe@yourcompany.com", "Incorrect Email entered");
        WebElement inputFirstName = WaitElement.present(driver, By.xpath("//input[@id=\"FirstName\"]"), 10);
        inputFirstName.sendKeys("nguyen");
        Assert.assertEquals(inputFirstName.getAttribute("value").trim(), "nguyen", "Incorrect FirstName entered");

        WebElement inputLastName = WaitElement.visible(driver, By.xpath("//input[@id=\"LastName\"]"), 10);
        inputLastName.sendKeys("cuong");
        Assert.assertEquals(inputLastName.getAttribute("value").trim(), "cuong", "Incorrect LastName entered");

        WebElement inputPhone = WaitElement.visible(driver, By.xpath("//input[@id=\"Phone\"]"), 10);
        inputPhone.sendKeys("0331393427");
        Assert.assertEquals(inputPhone.getAttribute("value").trim(), "0331393427", "Incorrect Phone entered");

        WebElement selectCountry = WaitElement.visible(driver, By.id("Country"), 10);
        Select country = new Select(selectCountry);
        country.selectByValue("Albania");
        Assert.assertEquals(selectCountry.getAttribute("value").trim(), "Albania", "Incorrect Country entered");

        WebElement inputCompany = WaitElement.visible(driver, By.xpath("//input[@id=\"Company\"]"), 10);
        inputCompany.sendKeys("Vnback");
        Assert.assertEquals(inputCompany.getAttribute("value").trim(), "Vnback", "Incorrect Company entered");

//        WebElement selectInterest = WaitElement.visible(driver, By.id("Solution_Interest__c"), 10);
//        Select interest = new Select(selectInterest);
//        interest.selectByValue("Scalable Test Automation");
//        Assert.assertEquals(selectInterest.getAttribute("value").trim(), "Scalable Test Automation", "Incorrect Interest entered");

        WebElement areaComment = WaitElement.visible(driver, By.xpath("//textarea[@id=\"Sales_Contact_Comments__c\"]"), 10);
        areaComment.sendKeys("This is the test content");
        Assert.assertEquals(areaComment.getAttribute("value").trim(), "This is the test content", "Incorrect Interest entered");

        WebElement checkbox = WaitElement.present(driver, By.xpath("//div[contains(@class,'mktoCheckboxList')]//input"), 25);
        ElementValidate.clearAndClickCheckbox(checkbox);

        WebElement btnSubmit = WaitElement.clickable(driver, By.xpath("//button[@class='mktoButton']"), 10);
        btnSubmit.click();
        WebElement msg = WaitElement.visible(
                driver,
                By.id("ValidMsgSolution_Interest__c"),
                10
        );
        Assert.assertTrue(msg.isDisplayed(), "Error message not displayed!");
        Assert.assertTrue(msg.getText().trim().contains("This field is required."), "Unexpected error message!");
    }

    @Test
    public void testUnHappilyWithInvalidEmail() {
        driver.get("https://saucelabs.com/request-demo");
        Assert.assertEquals(driver.getCurrentUrl(), "https://saucelabs.com/request-demo", "Did not go to the correct page");
        Assert.assertEquals(driver.getTitle(), "Request a Sauce Labs Demo", "Did not go to the correct page");

        WebElement inputEmail = WaitElement.present(driver, By.xpath("//input[@id=\"Email\"]"), 10);
        ElementValidate.clearAndType(inputEmail, "john");
        Assert.assertEquals(inputEmail.getAttribute("value").trim(), "john", "Incorrect Email entered");

        WebElement inputCompany = WaitElement.visible(driver, By.xpath("//input[@id=\"Company\"]"), 10);
        ElementValidate.clearAndType(inputCompany, "Vnback");
        Assert.assertEquals(inputCompany.getAttribute("value").trim(), "Vnback", "Incorrect Company entered");

        WebElement selectInterest = WaitElement.visible(driver, By.id("Solution_Interest__c"), 10);
        ElementValidate.selectByVisibleText(selectInterest, "Scalable Test Automation");
        Assert.assertEquals(selectInterest.getAttribute("value").trim(), "Scalable Test Automation", "Incorrect Interest entered");

        WebElement areaComment = WaitElement.visible(driver, By.xpath("//textarea[@id=\"Sales_Contact_Comments__c\"]"), 10);
        ElementValidate.clearAndType(areaComment, "This is the test content");
        Assert.assertEquals(areaComment.getAttribute("value").trim(), "This is the test content", "Incorrect Interest entered");

        WebElement checkbox = WaitElement.present(driver, By.xpath("//div[contains(@class,'mktoCheckboxList')]//input"), 25);
        ElementValidate.clearAndClickCheckbox(checkbox);

        WebElement btnSubmit = WaitElement.clickable(driver, By.xpath("//button[@class='mktoButton']"), 10);
        btnSubmit.click();
        WebElement msg = WaitElement.visible(
                driver,
                By.id("ValidMsgEmail"),
                10
        );
        Assert.assertTrue(msg.isDisplayed(), "Error message not displayed!");
        Assert.assertTrue(msg.getText().trim().contains("Must be valid email."), "Unexpected error message!");
    }

    @Test
    public void testUnHappilyWithInvalidPhone() {
        driver.get("https://saucelabs.com/request-demo");
        Assert.assertEquals(driver.getCurrentUrl(), "https://saucelabs.com/request-demo", "Did not go to the correct page");
        Assert.assertEquals(driver.getTitle(), "Request a Sauce Labs Demo", "Did not go to the correct page");

        WebElement inputEmail = WaitElement.present(driver, By.xpath("//input[@id=\"Email\"]"), 10);
        inputEmail.sendKeys("john.doe@yourcompany.com");
        Assert.assertEquals(inputEmail.getAttribute("value").trim(), "john.doe@yourcompany.com", "Incorrect Email entered");
        WebElement inputFirstName = WaitElement.present(driver, By.xpath("//input[@id=\"FirstName\"]"), 10);
        inputFirstName.sendKeys("nguyen");
        Assert.assertEquals(inputFirstName.getAttribute("value").trim(), "nguyen", "Incorrect FirstName entered");

        WebElement inputLastName = WaitElement.visible(driver, By.xpath("//input[@id=\"LastName\"]"), 10);
        inputLastName.sendKeys("cuong");
        Assert.assertEquals(inputLastName.getAttribute("value").trim(), "cuong", "Incorrect LastName entered");

//        WebElement inputPhone = WaitElement.visible(driver, By.xpath("//input[@id=\"Phone\"]"), 10);
//        inputPhone.sendKeys("0331393427");
//        Assert.assertEquals(inputPhone.getAttribute("value").trim(), "0331393427", "Incorrect Phone entered");

        WebElement selectCountry = WaitElement.visible(driver, By.id("Country"), 10);
        Select country = new Select(selectCountry);
        country.selectByValue("Albania");
        Assert.assertEquals(selectCountry.getAttribute("value").trim(), "Albania", "Incorrect Country entered");

        WebElement inputCompany = WaitElement.visible(driver, By.xpath("//input[@id=\"Company\"]"), 10);
        inputCompany.sendKeys("Vnback");
        Assert.assertEquals(inputCompany.getAttribute("value").trim(), "Vnback", "Incorrect Company entered");

        WebElement selectInterest = WaitElement.visible(driver, By.id("Solution_Interest__c"), 10);
        Select interest = new Select(selectInterest);
        interest.selectByValue("Scalable Test Automation");
        Assert.assertEquals(selectInterest.getAttribute("value").trim(), "Scalable Test Automation", "Incorrect Interest entered");

        WebElement areaComment = WaitElement.visible(driver, By.xpath("//textarea[@id=\"Sales_Contact_Comments__c\"]"), 10);
        areaComment.sendKeys("This is the test content");
        Assert.assertEquals(areaComment.getAttribute("value").trim(), "This is the test content", "Incorrect Interest entered");

        WebElement checkbox = WaitElement.present(driver, By.xpath("//div[contains(@class,'mktoCheckboxList')]//input"), 25);
        ElementValidate.clearAndClickCheckbox(checkbox);

        WebElement btnSubmit = WaitElement.clickable(driver, By.xpath("//button[@class='mktoButton']"), 10);
        btnSubmit.click();
        WebElement msg = WaitElement.visible(
                driver,
                By.id("ValidMsgPhone"),
                10
        );
        Assert.assertTrue(msg.isDisplayed(), "Error message not displayed!");
        Assert.assertTrue(msg.getText().trim().contains("Must be a phone number."), "Unexpected error message!");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
