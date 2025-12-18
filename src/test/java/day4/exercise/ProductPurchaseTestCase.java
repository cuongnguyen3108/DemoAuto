package day4.exercise;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.*;
import vn.devpro.assignment67.models.Product;

import java.util.List;

public class ProductPurchaseTestCase {
    public static void main(String[] args) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        String pageUrlLogin = driver.getCurrentUrl();
        System.out.println("Url Page: " + pageUrlLogin);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        String pageUrlProducts = driver.getCurrentUrl();
        System.out.println("Url Page: " + pageUrlProducts);
        if (!loginPage.hasRedirectedTo(10)) {
            driver.quit();
            return;
        }

        InventoryPage inventoryPage = new InventoryPage(driver);
        List<Product> products = inventoryPage.addProductToCart(3);
        Thread.sleep(5000);

        String pageUrlYourCart = driver.getCurrentUrl();
        System.out.println("Url Page: " + pageUrlYourCart);
        if (!inventoryPage.hasRedirectedTo(10)) {
            driver.quit();
            return;
        }
        CartPage cartPage = new CartPage(driver);
        cartPage.yourCart(products);
        Thread.sleep(5000);

        String pageUrlCheckoutYourInformation = driver.getCurrentUrl();
        System.out.println("Url Page: " + pageUrlCheckoutYourInformation);
        if (!cartPage.hasRedirectedTo(10)) {
            driver.quit();
            return;
        }
        CheckoutYourInformationPage yourInformationPage = new CheckoutYourInformationPage(driver);
        yourInformationPage.fillInformation("cuong","nguyen","12345");
        Thread.sleep(5000);

        String pageUrlCheckoutOverview = driver.getCurrentUrl();
        System.out.println("Url Page: " + pageUrlCheckoutOverview);
        if (!yourInformationPage.hasRedirectedTo(10)) {
            driver.quit();
            return;
        }
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        overviewPage.finish(products);
        Thread.sleep(5000);

        String pageUrlCheckoutComplete = driver.getCurrentUrl();
        System.out.println("Url Page: " + pageUrlCheckoutComplete);
        if (!overviewPage.hasRedirectedTo(10)) {
            driver.quit();
            return;
        }
        CheckoutCompletePage completePage = new CheckoutCompletePage(driver);
        completePage.complete();
        Thread.sleep(5000);

        String pageUrlFinal = driver.getCurrentUrl();
        System.out.println("Url Page: " + pageUrlFinal);
        System.out.println("\n");
        if (!completePage.hasRedirectedTo(10)) {
            driver.quit();
            return;
        }
        System.out.println("✅ Text case successful");
        driver.quit();
    }


}
