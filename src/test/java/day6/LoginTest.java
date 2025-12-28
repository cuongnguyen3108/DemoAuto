package day6;

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

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("--------Text Before Suite---------");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("--------Text Before Class---------");
    }

    @BeforeMethod
    public void beforeMethod() {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--incognito");
//
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        System.out.println("--------Text Before Method---------");
    }


    @Test
    public void testLoginInvalidateUserName() {
        driver.get("https://www.saucedemo.com/");
        Assert.assertEquals(driver.getTitle(), "Swag Labs", "Incorrect title");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "Incorrect URL");

        String filePath = "data-test-product-purchase.xlsx";
        String sheetName = "Login";
        List<Map<String, String>> list = ExelUtils.readFileExcelData(
                filePath,
                sheetName
        );
        System.out.println("Url Page: " + driver.getCurrentUrl());
        System.out.println("\n\nLogin");
        Map<String, String> firstRow = list.get(0);
//        for (Map<String, String> data : list) {
        WebElement inputUsername = WaitElement.visible(driver, LocatorHelper.getBy(firstRow.get("element_username")), 10);
//            ElementValidate.clearAndType(inputUsername, data.get("username"));
        ElementValidate.clearAndType(inputUsername, "standard_user1");

        WebElement inputPassword = WaitElement.visible(driver, LocatorHelper.getBy(firstRow.get("element_password")), 10);
//            ElementValidate.clearAndType(inputPassword, data.get("password"));
        ElementValidate.clearAndType(inputPassword, "secret_sauce");


        WebElement btnLogin = WaitElement.visible(driver, LocatorHelper.getBy(firstRow.get("element_btnLogin")), 10);
        btnLogin.click();

//        String msg = ElementValidate.validate(driver, null, "Login", LocatorHelper.getBy(firstRow.get("element_msg")));
//        System.out.println(msg);
//        }
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html", "Login failed");
        WebElement listProduct = WaitElement.visible(driver, By.className("inventory_list"), 10);
        Assert.assertTrue(listProduct.isDisplayed(), "Login failed");
        WebElement sortProduct = WaitElement.visible(driver, By.className("product_sort_container"), 10);
        Assert.assertTrue(sortProduct.isDisplayed(), "Login failed");

    }
    @Test
    public void testLoginInvalidatePassword() {
        driver.get("https://www.saucedemo.com/");
        Assert.assertEquals(driver.getTitle(), "Swag Labs", "Incorrect title");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "Incorrect URL");

        String filePath = "data-test-product-purchase.xlsx";
        String sheetName = "Login";
        List<Map<String, String>> list = ExelUtils.readFileExcelData(
                filePath,
                sheetName
        );
        System.out.println("Url Page: " + driver.getCurrentUrl());
        System.out.println("\n\nLogin");
        Map<String, String> firstRow = list.get(0);
//        for (Map<String, String> data : list) {
        WebElement inputUsername = WaitElement.visible(driver, LocatorHelper.getBy(firstRow.get("element_username")), 10);
//            ElementValidate.clearAndType(inputUsername, data.get("username"));
        ElementValidate.clearAndType(inputUsername, "standard_user");

        WebElement inputPassword = WaitElement.visible(driver, LocatorHelper.getBy(firstRow.get("element_password")), 10);
//            ElementValidate.clearAndType(inputPassword, data.get("password"));
        ElementValidate.clearAndType(inputPassword, "secret_sauce2");


        WebElement btnLogin = WaitElement.visible(driver, LocatorHelper.getBy(firstRow.get("element_btnLogin")), 10);
        btnLogin.click();

//        String msg = ElementValidate.validate(driver, null, "Login", LocatorHelper.getBy(firstRow.get("element_msg")));
//        System.out.println(msg);
//        }
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html", "Login failed");
        WebElement listProduct = WaitElement.visible(driver, By.className("inventory_list"), 10);
        Assert.assertTrue(listProduct.isDisplayed(), "Login failed");
        WebElement sortProduct = WaitElement.visible(driver, By.className("product_sort_container"), 10);
        Assert.assertTrue(sortProduct.isDisplayed(), "Login failed");

    }
    @Test
    public void testLoginInvalidateUserNameAndPassword() {
        driver.get("https://www.saucedemo.com/");
        Assert.assertEquals(driver.getTitle(), "Swag Labs", "Incorrect title");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "Incorrect URL");

        String filePath = "data-test-product-purchase.xlsx";
        String sheetName = "Login";
        List<Map<String, String>> list = ExelUtils.readFileExcelData(
                filePath,
                sheetName
        );
        System.out.println("Url Page: " + driver.getCurrentUrl());
        System.out.println("\n\nLogin");
        Map<String, String> firstRow = list.get(0);
//        for (Map<String, String> data : list) {
        WebElement inputUsername = WaitElement.visible(driver, LocatorHelper.getBy(firstRow.get("element_username")), 10);
//            ElementValidate.clearAndType(inputUsername, data.get("username"));
        ElementValidate.clearAndType(inputUsername, "standard_user1");

        WebElement inputPassword = WaitElement.visible(driver, LocatorHelper.getBy(firstRow.get("element_password")), 10);
//            ElementValidate.clearAndType(inputPassword, data.get("password"));
        ElementValidate.clearAndType(inputPassword, "secret_sauce2");


        WebElement btnLogin = WaitElement.visible(driver, LocatorHelper.getBy(firstRow.get("element_btnLogin")), 10);
        btnLogin.click();

//        String msg = ElementValidate.validate(driver, null, "Login", LocatorHelper.getBy(firstRow.get("element_msg")));
//        System.out.println(msg);
//        }
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html", "Login failed");
        WebElement listProduct = WaitElement.visible(driver, By.className("inventory_list"), 10);
        Assert.assertTrue(listProduct.isDisplayed(), "Login failed");
        WebElement sortProduct = WaitElement.visible(driver, By.className("product_sort_container"), 10);
        Assert.assertTrue(sortProduct.isDisplayed(), "Login failed");

    }
    @Test
    public void testLoginSuccessful() {
        driver.get("https://www.saucedemo.com/");
        Assert.assertEquals(driver.getTitle(), "Swag Labs", "Incorrect title");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "Incorrect URL");

        String filePath = "data-test-product-purchase.xlsx";
        String sheetName = "Login";
        List<Map<String, String>> list = ExelUtils.readFileExcelData(
                filePath,
                sheetName
        );
        System.out.println("Url Page: " + driver.getCurrentUrl());
        System.out.println("\n\nLogin");
        Map<String, String> firstRow = list.get(0);
//        for (Map<String, String> data : list) {
        WebElement inputUsername = WaitElement.visible(driver, LocatorHelper.getBy(firstRow.get("element_username")), 10);
//            ElementValidate.clearAndType(inputUsername, data.get("username"));
        ElementValidate.clearAndType(inputUsername, "standard_user");

        WebElement inputPassword = WaitElement.visible(driver, LocatorHelper.getBy(firstRow.get("element_password")), 10);
//            ElementValidate.clearAndType(inputPassword, data.get("password"));
        ElementValidate.clearAndType(inputPassword, "secret_sauce");


        WebElement btnLogin = WaitElement.visible(driver, LocatorHelper.getBy(firstRow.get("element_btnLogin")), 10);
        btnLogin.click();

//        String msg = ElementValidate.validate(driver, null, "Login", LocatorHelper.getBy(firstRow.get("element_msg")));
//        System.out.println(msg);
//        }
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
    @AfterClass
    public void afterClass() {
        System.out.println("--------Text After Class---------");
    }
    @AfterSuite
    public void afterSuite() {
        System.out.println("--------Text After Suite---------");
    }
}
