package day3.exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import vn.devpro.assignment67.models.ItemDemo;
import vn.devpro.assignment67.models.User;
import vn.devpro.assignment67.utils.ElementValidate;

import java.util.List;

public class UnHappyTestCase {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://saucelabs.com/request-demo");
        By error = By.xpath("./ancestor::div[contains(@class,'mktoFieldWrap')]/div[contains(@class,'mktoError')]");

        System.out.println("\n\tForm submitted without email");
        submitFormWithMissingFields(driver,
                new User("", "nguyen", "cuong", "Albania",
                        "Vnback", "Scalable Test Automation",
                        "This is the test content", (long) Double.parseDouble("0987654321")), error);

        System.out.println("\n\tForm submitted without firstName");
        submitFormWithMissingFields(driver,
                new User("john.doe@yourcompany.com", "", "cuong", "Albania",
                        "Vnback", "Scalable Test Automation",
                        "This is the test content", (long) Double.parseDouble("0987654321"))
                , error);

        System.out.println("\n\tForm submitted without lastName");
        submitFormWithMissingFields(driver,
                new User("john.doe@yourcompany.com", "nguyen", "", "Albania",
                        "Vnback", "Scalable Test Automation",
                        "This is the test content", (long) Double.parseDouble("0987654321"))
                , error);

        System.out.println("\n\tForm submitted without Company");
        submitFormWithMissingFields(driver,
                new User("john.doe@yourcompany.com", "nguyen", "cuong", "Albania",
                        "", "Scalable Test Automation",
                        "This is the test content", (long) Double.parseDouble("0987654321"))
                , error);

        System.out.println("\n\tForm submitted without Phone");
        submitFormWithMissingFields(driver,
                new User("john.doe@yourcompany.com", "nguyen",
                        "cuong", "Albania", "Vnback",
                        "Scalable Test Automation", "This is the test content",null
                        ), error);

        System.out.println("\n\tForm submitted without Country");
        submitFormWithMissingFields(driver,
                new User("john.doe@yourcompany.com", "nguyen",
                        "cuong", "", "Vnback",
                        "Scalable Test Automation", "This is the test content",
                        (long) Double.parseDouble("0987654321")), error);

        System.out.println("\n\tForm submitted without Interest");
        submitFormWithMissingFields(driver,
                new User("john.doe@yourcompany.com", "nguyen",
                        "cuong", "Albania", "Vnback",
                        "", "This is the test content",
                        (long) Double.parseDouble("0987654321")), error);

        driver.quit();
    }

    public static void submitFormWithMissingFields(WebDriver driver, User user, By error) {
        List<ItemDemo> list = ItemDemo.fillForm(driver, user, error);

        if (list == null || list.isEmpty()) {
            return;
        }

        if (!ElementValidate.validateForm(driver, list, error)) {
            return;
        }

        System.out.println("Validation passed");

    }


}
