package day3.exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import vn.devpro.assignment67.models.ItemDemo;
import vn.devpro.assignment67.models.User;
import vn.devpro.assignment67.utils.Ads;
import vn.devpro.assignment67.utils.ElementValidate;

import java.util.List;
import java.util.Objects;

public class HappyTestCase {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://saucelabs.com/request-demo");
        By error = By.xpath("./ancestor::div[contains(@class,'mktoFieldWrap')]/div[contains(@class,'mktoError')]");

        List<ItemDemo> list = ItemDemo.fillForm(driver, new User("john.doe@yourcompany.com", "nguyen", "cuong", "Albania", "Vnback", "Scalable Test Automation", "This is the test content", (long) Double.parseDouble("0987654321")), error);

        if (list == null || list.isEmpty()) {
            driver.quit();
            return;
        }

        if (!ElementValidate.validateForm(driver, list, error)) {
            driver.quit();
            return;
        }
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
