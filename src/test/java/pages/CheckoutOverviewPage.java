package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import vn.devpro.assignment67.models.Product;
import vn.devpro.assignment67.utils.WaitElement;

import java.util.List;

public class CheckoutOverviewPage extends BasePage {

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String getExpectedPath() {
        return "/checkout-step-two.html";
    }

    public void finish(List<Product> products) {
        System.out.println("\n\nPage Checkout: Overview");
        WebElement totalShoppingCart = WaitElement.visible(driver, By.className("shopping_cart_badge"), 10);

        if (Integer.parseInt(totalShoppingCart.getText()) != products.size()) {
            System.out.println("❌ The total number ofF products in the cart is incorrect!");
        } else {
            System.out.println("✅ The total number of products in the cart:: " + totalShoppingCart.getText());
        }

        List<WebElement> listProduct = WaitElement.visibleElements(driver, By.xpath("//div[@class='cart_item']"), 10);
        if (listProduct.size() != products.size()) {
            System.out.println("\tThe number of products displayed is incorrect; \n\t" + products.size() + " products were added, but " + listProduct.size() + " are shown.");
            driver.quit();
            return;
        }
        System.out.println("✅ Number of products on display: " + listProduct.size());
        double sumPrice = 0.0;
        for (int i = 0; i < products.size(); i++) {
            WebElement nameProduct = listProduct.get(i).findElement(By.xpath("./descendant::div[@class=\"inventory_item_name\"]"));
            if (!products.get(i).getProductName().equals(nameProduct.getText())) {
                System.out.println("\tProduct name number " + i + 1 + " is displayed incorrectly.\n\tAdded name:" + products.get(i).getProductName() + ".\n\tDisplayed name:" + nameProduct.getText() + ".");
                driver.quit();
                return;
            }
            System.out.println("Name Product " + (i + 1) + ": " + nameProduct.getText());

            WebElement priceProduct = listProduct.get(i).findElement(By.xpath("./descendant::div[@class=\"inventory_item_price\"]"));
            if (!priceProduct.getText().equals("$" + products.get(i).getPrice())) {
                System.out.println("\tProduct price number " + i + 1 + " is displayed incorrectly.\n\tAdded price:" + products.get(i).getPrice() + ".\n\tDisplayed price:" + priceProduct.getText() + ".");
                driver.quit();
                return;
            }
            System.out.println("Price Product" + (i + 1) + ": " + priceProduct.getText());
            sumPrice += products.get(i).getPrice();

            WebElement quantityProduct = listProduct.get(i).findElement(By.xpath("./descendant::div[@class=\"cart_quantity\"]"));
            if (Integer.parseInt(quantityProduct.getText()) != products.get(i).getQuantity()) {
                System.out.println("\tProduct quantity number " + i + 1 + " is displayed incorrectly.\n\tAdded quantity:" + products.get(i).getQuantity() + ".\n\tDisplayed quantity:" + quantityProduct.getText() + ".");
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
        if (Double.parseDouble(subtotal.getText().replace("Item total: $", "")) != sumPrice) {
            System.out.println("Item total price is incorrect");
            return;
        }
        System.out.println(subtotal.getText());
        WebElement tax = WaitElement.visible(driver, By.xpath("//div[@class=\"summary_tax_label\"]"), 10);
        System.out.println(tax.getText());
        double taxPrice = Double.parseDouble(tax.getText().replace("Tax: $", ""));
        double totalPrice = sumPrice + taxPrice;

        WebElement total = WaitElement.visible(driver, By.xpath("//div[@class=\"summary_total_label\"]"), 10);
        if (Double.parseDouble(total.getText().replace("Total: $", "")) != totalPrice) {
            System.out.println("Total price is incorrect");
            return;
        }
        System.out.println(total.getText());
        WebElement btnFinish = WaitElement.clickable(driver, By.id("finish"), 10);
        if (!btnFinish.isDisplayed()) {
            System.out.println("❌ Button " + btnFinish.getText() + " is not displayed");
            driver.quit();
            return;
        }
        System.out.println("✅ Button " + btnFinish.getText() + " is displayed");
        System.out.println("✅ Button " + btnFinish.getText() + " is clicked");
        btnFinish.click();
    }

}
