package day3.Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class UnHappyTestCase {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://saucelabs.com/request-demo");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement inputEmail = driver.findElement(By.xpath("//input[@id=\"Email\"]"));
        inputEmail.sendKeys("john.doe@yourcompany.com");

        WebElement btnSubmit = driver.findElement(By.xpath("//button[@class='mktoButton']"));
        btnSubmit.click();

        String msgEmail = validate(inputEmail, "Email");
        if (msgEmail.contains("Must be valid email")) {
            System.out.println(msgEmail);
            driver.close();
            driver.quit();
            return;
        } else {
            System.out.println(msgEmail);
        }
        WebElement inputFirstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"FirstName\"]")));
        inputFirstName.sendKeys("nguyen");
        WebElement inputLastName = driver.findElement(By.xpath("//input[@id=\"LastName\"]"));
        inputLastName.sendKeys("cuong");
        WebElement inputPhone = driver.findElement(By.xpath("//input[@id=\"Phone\"]"));
//        inputPhone.sendKeys("0379093127");
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

        btnSubmit.click();

        String msgFirstName = validate(inputFirstName, "FirstName");
        System.out.println(msgFirstName);
        String msgLastName = validate(inputLastName, "LastName");
        System.out.println(msgLastName);
        String msgPhone = validate(inputPhone, "Phone");
        System.out.println(msgPhone);
        String msgCountry = validate(selectCountry, "Country");
        System.out.println(msgCountry);
        String msgCompany = validate(inputCompany, "Company");
        System.out.println(msgCompany);
        String msgInterest = validate(selectInterest, "Interest");
        System.out.println(msgInterest);
        driver.close();
        driver.quit();
    }

    public static String validate(WebElement field, String fieldName) {

        List<WebElement> errors = field.findElements(
                By.xpath("./ancestor::div[contains(@class,'mktoFieldWrap')]//div[contains(@class,'mktoError')]")
        );

        if (!errors.isEmpty()) {
            return "❌ Error " + fieldName + ": " + errors.get(0).getText();
        }

        return "✅ Field " + fieldName + ": " + field.getAttribute("value");
    }
}
