package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import vn.devpro.assignment67.models.Product;
import vn.devpro.assignment67.utils.WaitElement;
import vn.devpro.assignment67.utils.helpers.LocatorHelper;

import java.util.List;
import java.util.Map;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
        loadExcelMessageData();
        loadExcelData();
    }

    @Override
    protected String getFilePath() {
        return "data-test-product-purchase.xlsx";
    }


    @Override
    protected String getSheetName() {
        return "Cart";
    }

    @Override
    protected String getExpectedPath() {
        return "/cart.html";
    }

    public void yourCart(List<Product> products) {
        System.out.println("\n\nPage Your Cart");
        Map<String, String> firstRow = excelData.get(0);

        WebElement totalShoppingCart = WaitElement.visible(driver, LocatorHelper.getBy(firstRow.get("element_totalShoppingCart")), 10);

        if (Integer.parseInt(totalShoppingCart.getText()) != products.size()) {
            System.out.println("❌ The total number off products in the cart is incorrect!");
        } else {
            System.out.println(msg("msg_pass_cart_badge",totalShoppingCart.getText()));
            totalShoppingCart.click();
        }

        List<WebElement> listProduct = WaitElement.visibleElements(driver,LocatorHelper.getBy(firstRow.get("element_listProduct")), 10);
        if (listProduct.size() != products.size()) {
            System.out.println("\tThe number of products displayed is incorrect; \n\t" + products.size() + " products were added, but " + listProduct.size() + " are shown.");
            driver.quit();
            return;
        }
        System.out.println("✅ Number of products on display: " + listProduct.size());

        for (int i = 0; i < products.size(); i++) {
            WebElement nameProduct = listProduct.get(i).findElement(LocatorHelper.getBy(firstRow.get("element_nameProduct")));
            if (!products.get(i).getProductName().equals(nameProduct.getText())) {
                System.out.println("\tProduct name number " + i + 1 + " is displayed incorrectly.\n\tAdded name:" + products.get(i).getProductName() + ".\n\tDisplayed name:" + nameProduct.getText() + ".");
                driver.quit();
                return;
            }
            System.out.println("Name Product " + (i + 1) + ": " + nameProduct.getText());

            WebElement priceProduct = listProduct.get(i).findElement(LocatorHelper.getBy(firstRow.get("element_priceProduct")));
            if (!priceProduct.getText().equals("$" + products.get(i).getPrice())) {
                System.out.println("\tProduct price number " + i + 1 + " is displayed incorrectly.\n\tAdded price:" + products.get(i).getPrice() + ".\n\tDisplayed price:" + priceProduct.getText() + ".");
                driver.quit();
                return;
            }
            System.out.println("Price Product" + (i + 1) + ": " + priceProduct.getText());

            WebElement quantityProduct = listProduct.get(i).findElement(LocatorHelper.getBy(firstRow.get("element_quantityProduct")));
            if (Integer.parseInt(quantityProduct.getText()) != products.get(i).getQuantity()) {
                System.out.println("\tProduct quantity number " + i + 1 + " is displayed incorrectly.\n\tAdded quantity:" + products.get(i).getQuantity() + ".\n\tDisplayed quantity:" + quantityProduct.getText() + ".");
                driver.quit();
                return;
            }
            System.out.println("Quantity Product: " + quantityProduct.getText());

        }
        WebElement btnContinueShopping = WaitElement.clickable(driver, LocatorHelper.getBy(firstRow.get("element_btnContinueShopping")), 15);
        if (!btnContinueShopping.isDisplayed()) {
            System.out.println("❌ Button " + btnContinueShopping.getText() + " is not displayed");
            driver.quit();
            return;
        }
        System.out.println("✅ Button " + btnContinueShopping.getText() + " is displayed");
        WebElement btnCheckout = WaitElement.clickable(driver, LocatorHelper.getBy(firstRow.get("element_btnCheckout")), 15);
        if (!btnCheckout.isDisplayed()) {
            System.out.println("❌ Button " + btnCheckout.getText() + " is not displayed");
            driver.quit();
            return;
        }
        System.out.println("✅ Button " + btnCheckout.getText() + " is displayed");
        System.out.println("✅ Button " + btnCheckout.getText() + " is clicked");
        btnCheckout.click();
    }

}
