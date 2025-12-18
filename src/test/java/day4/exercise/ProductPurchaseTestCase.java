package day4.exercise;

import base.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import vn.devpro.assignment67.models.Product;
import vn.devpro.assignment67.utils.ElementValidate;
import vn.devpro.assignment67.utils.WaitElement;

import java.util.ArrayList;
import java.util.List;

public class ProductPurchaseTestCase extends BaseTest {
    public static void main(String[] args) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        String pageUrlLogin = driver.getCurrentUrl();
        System.out.println("Url Page: " + pageUrlLogin);

        login(driver);
        Thread.sleep(5000);

        String pageUrlProducts = driver.getCurrentUrl();
        System.out.println("Url Page: " + pageUrlProducts);
        if (!isPageRedirected(driver, "/inventory.html")) {
            driver.close();
            driver.quit();
            return;
        }
        List<Product> products = addToCart(driver, 3);
        Thread.sleep(5000);
        String pageUrlYourCart = driver.getCurrentUrl();
        System.out.println("Url Page: " + pageUrlYourCart);
        if (!isPageRedirected(driver, "/cart.html")) {
            driver.close();
            driver.quit();
            return;
        }
        yourCart(driver, products);
        Thread.sleep(5000);
        String pageUrlCheckoutYourInformation = driver.getCurrentUrl();
        System.out.println("Url Page: " + pageUrlCheckoutYourInformation);
        if (!isPageRedirected(driver, "/checkout-step-one.html")) {
            driver.close();
            driver.quit();
            return;
        }

        checkoutYourInformation(driver);
        Thread.sleep(5000);
        String pageUrlCheckoutOverview = driver.getCurrentUrl();
        System.out.println("Url Page: " + pageUrlCheckoutOverview);
        if (!isPageRedirected(driver, "/checkout-step-two.html")) {
            driver.close();
            driver.quit();
            return;
        }
        checkoutOverview(driver, products);
        Thread.sleep(5000);
        String pageUrlCheckoutComplete = driver.getCurrentUrl();
        System.out.println("Url Page: " + pageUrlCheckoutComplete);
        if (!isPageRedirected(driver, "/checkout-complete.html")) {
            driver.close();
            driver.quit();
            return;
        }
        checkoutComplete(driver);

        Thread.sleep(5000);
        String pageUrlFinal = driver.getCurrentUrl();
        System.out.println("Url Page: " + pageUrlFinal);
        System.out.println("\n");
        if (!isPageRedirected(driver, "/inventory.html")) {
            driver.close();
            driver.quit();
            return;
        }
        System.out.println("✅ Text case successful");
        driver.quit();
    }

    public static void login(WebDriver driver) {
        System.out.println("\n\nLogin");

        WebElement inputUsername = WaitElement.visible(driver, By.xpath("//input[@id='user-name']"), 10);
        inputUsername.sendKeys("standard_user");

        WebElement inputPassword = WaitElement.visible(driver, By.xpath("//input[@id='password']"), 10);
        inputPassword.sendKeys("secret_sauce");
        WebElement btnLogin = WaitElement.visible(driver, By.xpath("//input[@id=\"login-button\"]"), 10);
        btnLogin.click();

        String msg = ElementValidate.validate(driver, null, "Login", By.cssSelector(".error-message-container h3"));
        System.out.println(msg);
    }

    public static List<Product> addToCart(WebDriver driver, int quantity) {
        System.out.println("\n\nPage Add To Cart");

        WebElement sortProduct = WaitElement.clickable(driver, By.xpath("//select[@class='product_sort_container']"), 5);
        Select selectSort = new Select(sortProduct);
        selectSort.selectByValue("hilo");

        List<WebElement> listProduct = WaitElement.visibleElements(driver, By.xpath("//div[@class='inventory_item']"), 10);
        List<Product> products = new ArrayList<>();
        int q = Math.min(listProduct.size(), quantity);
        for (int i = 0; i < q; i++) {
            Product product = new Product();
            WebElement addToCartProduct = listProduct.get(i).findElement(By.xpath("./descendant::button"));
            addToCartProduct.click();
            WebElement nameProduct = listProduct.get(i).findElement(By.xpath("./descendant::div[@class=\"inventory_item_name \"]"));
            System.out.println("Name Product: " + nameProduct.getText());
            product.setProductName(nameProduct.getText());
            WebElement priceProduct = listProduct.get(i).findElement(By.xpath("./descendant::div[@class=\"inventory_item_price\"]"));
            System.out.println("Price Product: " + priceProduct.getText());
            String priceNumber = priceProduct.getText().replace("$", "");
            product.setPrice(Double.parseDouble(priceNumber));

            int quantityProduct = 1;
            product.setQuantity(quantityProduct);
            System.out.println("Quantity Product: " + quantityProduct);

            products.add(product);
        }
        WebElement totalShoppingCart = WaitElement.visible(driver, By.className("shopping_cart_badge"), 10);
        System.out.println("Total Shopping Cart: " + totalShoppingCart.getText());
        totalShoppingCart.click();
        return products;
    }

    public static void yourCart(WebDriver driver, List<Product> products) {
        System.out.println("\n\nPage Your Cart");

        List<WebElement> listProduct = WaitElement.visibleElements(driver, By.xpath("//div[@class='cart_item']"), 10);
        if (listProduct.size() != products.size()) {
            System.out.println("\tThe number of products displayed is incorrect; \n\t" + products.size() + " products were added, but " + listProduct.size() + " are shown.");
            driver.close();
            driver.quit();
            return;
        }
        System.out.println("✅ Number of products on display: " + listProduct.size());

        for (int i = 0; i < products.size(); i++) {
            WebElement nameProduct = listProduct.get(i).findElement(By.xpath("./descendant::div[@class=\"inventory_item_name\"]"));
            if (!products.get(i).getProductName().equals(nameProduct.getText())) {
                System.out.println("\tProduct name number " + i + 1 + " is displayed incorrectly.\n\tAdded name:" + products.get(i).getProductName() + ".\n\tDisplayed name:" + nameProduct.getText() + ".");
                driver.close();
                driver.quit();
                return;
            }
            System.out.println("Name Product " + (i + 1) + ": " + nameProduct.getText());

            WebElement priceProduct = listProduct.get(i).findElement(By.xpath("./descendant::div[@class=\"inventory_item_price\"]"));
            if (!priceProduct.getText().equals("$" + products.get(i).getPrice())) {
                System.out.println("\tProduct price number " + i + 1 + " is displayed incorrectly.\n\tAdded price:" + products.get(i).getPrice() + ".\n\tDisplayed price:" + priceProduct.getText() + ".");
                driver.close();
                driver.quit();
                return;
            }
            System.out.println("Price Product" + (i + 1) + ": " + priceProduct.getText());

            WebElement quantityProduct = listProduct.get(i).findElement(By.xpath("./descendant::div[@class=\"cart_quantity\"]"));
            if (Integer.parseInt(quantityProduct.getText()) != products.get(i).getQuantity()) {
                System.out.println("\tProduct quantity number " + i + 1 + " is displayed incorrectly.\n\tAdded quantity:" + products.get(i).getQuantity() + ".\n\tDisplayed quantity:" + quantityProduct.getText() + ".");
                driver.close();
                driver.quit();
                return;
            }
            System.out.println("Quantity Product: " + quantityProduct.getText());

        }
        WebElement btnContinueShopping = WaitElement.clickable(driver, By.xpath("//button[@id=\"continue-shopping\"]"), 15);
        if (!btnContinueShopping.isDisplayed()) {
            System.out.println("❌ Button " + btnContinueShopping.getText() + " is not displayed");
            driver.close();
            driver.quit();
            return;
        }
        System.out.println("✅ Button " + btnContinueShopping.getText() + " is displayed");
        WebElement btnCheckout = WaitElement.clickable(driver, By.xpath("//button[@id=\"checkout\" and @name=\"checkout\"]"), 15);
        if (!btnCheckout.isDisplayed()) {
            System.out.println("❌ Button " + btnCheckout.getText() + " is not displayed");
            driver.close();
            driver.quit();
            return;
        }
        System.out.println("✅ Button " + btnCheckout.getText() + " is displayed");
        System.out.println("✅ Button " + btnCheckout.getText() + " is clicked");
        btnCheckout.click();
    }


    public static void checkoutYourInformation(WebDriver driver) {
        System.out.println("\n\nPage Checkout: Your Information");

        WebElement inputFirstName = WaitElement.visible(driver, By.id("first-name"), 10);
        inputFirstName.sendKeys("cuong");
        WebElement inputLastName = WaitElement.visible(driver, By.id("last-name"), 10);
        inputLastName.sendKeys("nguyen");
        WebElement inputPostalCode = WaitElement.visible(driver, By.id("postal-code"), 10);
        inputPostalCode.sendKeys("12345");
        WebElement btnContinue = WaitElement.clickable(driver, By.id("continue"), 10);
        btnContinue.click();
        String msg = ElementValidate.validate(driver, null, "submit", By.xpath("//div[@class='error-message-container error']/h3"));
        System.out.println(msg);
    }
    public static void checkoutOverview(WebDriver driver, List<Product> products) {
        System.out.println("\n\nPage Checkout: Overview");

        List<WebElement> listProduct = WaitElement.visibleElements(driver, By.xpath("//div[@class='cart_item']"), 10);
        if (listProduct.size() != products.size()) {
            System.out.println("\tThe number of products displayed is incorrect; \n\t" + products.size() + " products were added, but " + listProduct.size() + " are shown.");
            driver.close();
            driver.quit();
            return;
        }
        System.out.println("✅ Number of products on display: " + listProduct.size());

        for (int i = 0; i < products.size(); i++) {
            WebElement nameProduct = listProduct.get(i).findElement(By.xpath("./descendant::div[@class=\"inventory_item_name\"]"));
            if (!products.get(i).getProductName().equals(nameProduct.getText())) {
                System.out.println("\tProduct name number " + i + 1 + " is displayed incorrectly.\n\tAdded name:" + products.get(i).getProductName() + ".\n\tDisplayed name:" + nameProduct.getText() + ".");
                driver.close();
                driver.quit();
                return;
            }
            System.out.println("Name Product " + (i + 1) + ": " + nameProduct.getText());

            WebElement priceProduct = listProduct.get(i).findElement(By.xpath("./descendant::div[@class=\"inventory_item_price\"]"));
            if (!priceProduct.getText().equals("$" + products.get(i).getPrice())) {
                System.out.println("\tProduct price number " + i + 1 + " is displayed incorrectly.\n\tAdded price:" + products.get(i).getPrice() + ".\n\tDisplayed price:" + priceProduct.getText() + ".");
                driver.close();
                driver.quit();
                return;
            }
            System.out.println("Price Product" + (i + 1) + ": " + priceProduct.getText());

            WebElement quantityProduct = listProduct.get(i).findElement(By.xpath("./descendant::div[@class=\"cart_quantity\"]"));
            if (Integer.parseInt(quantityProduct.getText()) != products.get(i).getQuantity()) {
                System.out.println("\tProduct quantity number " + i + 1 + " is displayed incorrectly.\n\tAdded quantity:" + products.get(i).getQuantity() + ".\n\tDisplayed quantity:" + quantityProduct.getText() + ".");
                driver.close();
                driver.quit();
                return;
            }
            System.out.println("Quantity Product: " + quantityProduct.getText());
        }

        WebElement payment = WaitElement.visible(driver, By.xpath("//div[@class=\"summary_value_label\"]"), 10);
        System.out.println("\nPayment Information: " + payment.getText());
        WebElement shipping = WaitElement.visible(driver, By.xpath("//div[@class=\"summary_value_label\"]"), 10);
        System.out.println("Shipping Information: " + shipping.getText());
        System.out.println("Price Total");
        WebElement subtotal = WaitElement.visible(driver, By.xpath("//div[@class=\"summary_subtotal_label\"]"), 10);
        System.out.println(subtotal.getText());
        WebElement tax = WaitElement.visible(driver, By.xpath("//div[@class=\"summary_tax_label\"]"), 10);
        System.out.println(tax.getText());
        WebElement total=WaitElement.visible(driver, By.xpath("//div[@class=\"summary_total_label\"]"), 10);
        System.out.println(total.getText());
        WebElement btnFinish=WaitElement.clickable(driver,By.id("finish"),10);
        if (!btnFinish.isDisplayed()) {
            System.out.println("❌ Button " + btnFinish.getText() + " is not displayed");
            driver.close();
            driver.quit();
            return;
        }
        System.out.println("✅ Button " + btnFinish.getText() + " is displayed");
        System.out.println("✅ Button " + btnFinish.getText() + " is clicked");
        btnFinish.click();
    }

    public static void checkoutComplete(WebDriver driver) {
        System.out.println("\n\nPage Checkout: Complete!");

        WebElement completeHeader=WaitElement.visible(driver, By.xpath("//h2[@class=\"complete-header\"]"), 10);
        System.out.println(completeHeader.getText());
        WebElement completeText=WaitElement.visible(driver, By.xpath("//div[@class=\"complete-text\"]"), 10);
        System.out.println(completeText.getText());
        WebElement btnBackHome = WaitElement.clickable(driver, By.id("back-to-products"), 15);
        if (!btnBackHome.isDisplayed()) {
            System.out.println("❌ Button " + btnBackHome.getText() + " is not displayed");
            driver.close();
            driver.quit();
            return;
        }
        System.out.println("✅ Button " + btnBackHome.getText() + " is displayed");
        System.out.println("✅ Button " + btnBackHome.getText() + " is clicked");
        btnBackHome.click();
    }
}
