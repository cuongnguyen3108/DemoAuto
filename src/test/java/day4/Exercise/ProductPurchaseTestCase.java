package day4.Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import vn.devpro.assignment67.utils.ElementValidate;
import vn.devpro.assignment67.utils.WaitElement;

public class ProductPurchaseTestCase {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        String pageTitleLogin = driver.getTitle();
        login(driver);
//        String alert = driver.switchTo().alert().getText();
//        System.out.println("Alert: " + alert);
//        driver.switchTo().alert().accept();
    }

    public static void login(WebDriver driver) {
        WebElement inputUsername= WaitElement.visible(driver, By.xpath("//input[@id='user-name']"),10);
        inputUsername.sendKeys("standard_user");
//                inputUsername.sendKeys("standard_use");

        WebElement inputPassword=WaitElement.visible(driver, By.xpath("//input[@id='password']"),10);
        inputPassword.sendKeys("secret_sauce");
        WebElement btnLogin =WaitElement.visible(driver, By.xpath("//input[@id=\"login-button\"]"),10);
        btnLogin.click();

        String msg= ElementValidate.validate(driver,null, "Login",  By.cssSelector(".error-message-container h3")
        );
        System.out.println(msg);
    }


}
