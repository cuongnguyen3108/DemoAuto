package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import vn.devpro.assignment67.models.Product;
import vn.devpro.assignment67.utils.WaitElement;

import java.util.List;

public class CartPage extends BasePage{

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void yourCart(List<Product> products) {
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

    @Override
    protected String getExpectedPath() {
        return "/checkout-step-one.html";
    }
}
