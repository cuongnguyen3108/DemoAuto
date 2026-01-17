package day7.feature;

import day7.action.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import vn.devpro.assignment67.utils.ElementValidate;
import vn.devpro.assignment67.utils.ExelUtils;
import vn.devpro.assignment67.utils.WaitElement;
import vn.devpro.assignment67.utils.helpers.LocatorHelper;

import java.util.List;
import java.util.Map;

public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;
    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        loginPage =new LoginPage(driver);
    }

    @Test
    public void testLoginSuccessful() {
        // Kiểm tra tiêu đề và URL
        Assert.assertEquals(driver.getTitle(), "Swag Labs", "Title mismatch!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "URL mismatch!");

        // Điền thông tin đúng
        loginPage.enterUseName("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html", "Login failed");
        WebElement listProduct = WaitElement.visible(driver, By.className("inventory_list"), 10);
        Assert.assertTrue(listProduct.isDisplayed(), "Login failed");
        WebElement sortProduct = WaitElement.visible(driver, By.className("product_sort_container"), 10);
        Assert.assertTrue(sortProduct.isDisplayed(), "Login failed");

    }

    @AfterMethod
    public void afterMethod() {
        if (driver!=null) driver.quit();
        System.out.println("--------Text After Method---------");
    }
}

