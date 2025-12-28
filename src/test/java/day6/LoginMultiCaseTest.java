package day6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginMultiCaseTest {
    WebDriver driver;

    @BeforeSuite
    public void setUpSuite() {
        System.out.println("===== Test Suite Started =====");
        //System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
    }

    @BeforeClass //chỉ mở trang web duy nhất 1 lần
    public void setUpClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        System.out.println("===== Test Class Setup =====");
    }

    @BeforeMethod //mỗi test mở lại trang mới
    public void setUp() {
        System.out.println("===== Before Each Test Method =====");

    }

    @Test
    public void testLoginWithValidCredentials() {
        driver.get("https://www.saucedemo.com/");

        // Kiểm tra tiêu đề và URL
        Assert.assertEquals(driver.getTitle(), "Swag Labs", "Title mismatch!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "URL mismatch!");

        // Điền thông tin đúng
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Xác nhận vào trang inventory
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html", "Login failed!");

        // Kiểm tra logo & sản phẩm
        WebElement appLogo = driver.findElement(By.className("app_logo4"));
        Assert.assertTrue(appLogo.isDisplayed(), "Logo not displayed!");

        int itemCount = driver.findElements(By.className("inventory_item")).size();
        Assert.assertTrue(itemCount > 0, "No products displayed!");
    }

    @Test
    public void testLoginWithInvalidUsername() {
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("invalid_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Kiểm tra thông báo lỗi
        WebElement errorMsg = driver.findElement(By.cssSelector("[data-test='error']"));
        Assert.assertTrue(errorMsg.isDisplayed(), "Error message not displayed!");
        Assert.assertTrue(errorMsg.getText().contains("Username and password do not match"), "Unexpected error message!");
    }

    @Test
    public void testLoginWithInvalidPassword() {
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("invalid_pass");
        driver.findElement(By.id("login-button")).click();
        WebElement errorMsg = driver.findElement(By.cssSelector("[data-test='error']"));
        Assert.assertTrue(errorMsg.isDisplayed(), "Error message not displayed!");
        Assert.assertTrue(errorMsg.getText().contains("Username and password do not match"), "Unexpected error message!");
    }

    @Test
    public void testLoginWithEmptyUsername() {
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        WebElement errorMsg = driver.findElement(By.cssSelector("[data-test='error']"));
        Assert.assertTrue(errorMsg.isDisplayed(), "Error message not displayed!");
        Assert.assertTrue(errorMsg.getText().contains("Username is required"), "Unexpected error message!");
    }

    @Test
    public void testLoginWithEmptyPassword() {
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("");
        driver.findElement(By.id("login-button")).click();

        WebElement errorMsg = driver.findElement(By.cssSelector("[data-test='error']"));
        Assert.assertTrue(errorMsg.isDisplayed(), "Error message not displayed!");
        Assert.assertTrue(errorMsg.getText().contains("Password is required"), "Unexpected error message!");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("===== After Each Test Method =====");
    }

    @AfterClass
    public void tearDownClass() {
        if (driver != null) {
            driver.quit();
        }
        System.out.println("===== Test Class TearDown =====");
    }

    @AfterSuite
    public void tearDownSuite() {
        System.out.println("===== Test Suite Completed =====");
    }
}
