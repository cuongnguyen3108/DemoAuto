package day4.exercise;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.*;
import vn.devpro.assignment67.models.Product;

import java.util.List;

public class ProductPurchaseTestCase {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyPageNavigation( 10);

        loginPage.login("username", "password");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.verifyPageNavigation(10);

        List<Product> products = inventoryPage.addProductToCart("quantity");

        CartPage cartPage = new CartPage(driver);
        cartPage.verifyPageNavigation(10);

        cartPage.yourCart(products);

        CheckoutYourInformationPage yourInformationPage = new CheckoutYourInformationPage(driver);
        yourInformationPage.verifyPageNavigation( 10);

        yourInformationPage.fillInformation("firstName", "lastName", "postalCode", products);


        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        overviewPage.verifyPageNavigation( 10);

        overviewPage.finish(products);


        CheckoutCompletePage completePage = new CheckoutCompletePage(driver);
        completePage.verifyPageNavigation( 10);

        completePage.complete();

        System.out.println("\n");
        inventoryPage.verifyPageNavigation( 10);

        System.out.println("✅ Text case successful");
        driver.quit();
    }


}
