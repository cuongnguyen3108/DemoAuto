package day4.exercise;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.*;
import vn.devpro.assignment67.models.Product;

import java.util.List;

public class ProductPurchaseTestCase {
    public static void main(String[] args)  {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        LoginPage loginPage = new LoginPage(driver);
        if (!loginPage.hasRedirectedTo(10)) {
            driver.quit();
            return;
        }
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        if (!inventoryPage.hasRedirectedTo(10)) {
            driver.quit();
            return;
        }
        List<Product> products = inventoryPage.addProductToCart(3);


        CartPage cartPage = new CartPage(driver);
        if (!cartPage.hasRedirectedTo(10)) {
            driver.quit();
            return;
        }
        cartPage.yourCart(products);


        CheckoutYourInformationPage yourInformationPage = new CheckoutYourInformationPage(driver);
        if (!yourInformationPage.hasRedirectedTo(10)) {
            driver.quit();
            return;
        }
        yourInformationPage.fillInformation("cuong", "nguyen", "12345");


        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        if (!overviewPage.hasRedirectedTo(10)) {
            driver.quit();
            return;
        }
        overviewPage.finish(products);


        CheckoutCompletePage completePage = new CheckoutCompletePage(driver);
        if (!completePage.hasRedirectedTo(10)) {
            driver.quit();
            return;
        }
        completePage.complete();

        System.out.println("\n");
        if (!inventoryPage.hasRedirectedTo(10)) {
            driver.quit();
            return;
        }
        System.out.println("✅ Text case successful");
        driver.quit();
    }
}
