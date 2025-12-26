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
            System.out.println(msg("msg_not_pass_cart_badge2"));
        } else {
            System.out.println(msg("msg_pass_cart_badge", totalShoppingCart.getText()));
        }

        List<WebElement> listProduct = WaitElement.visibleElements(driver, LocatorHelper.getBy(firstRow.get("element_listProduct")), 10);
        if (listProduct.size() != products.size()) {
            System.out.println(msg("msg_not_pass_display_sizeProduct", products.size(), listProduct.size()));
            driver.quit();
            return;
        }
        System.out.println(msg("msg_pass_display_sizeProduct", listProduct.size()));

        for (int i = 0; i < products.size(); i++) {
            WebElement nameProduct = listProduct.get(i).findElement(LocatorHelper.getBy(firstRow.get("element_nameProduct")));
            if (!products.get(i).getProductName().equals(nameProduct.getText())) {
                System.out.println(msg("msg_not_pass_display_nameProduct", i + 1, products.get(i).getProductName(), nameProduct.getText()));
                driver.quit();
                return;
            }
            System.out.println(msg("msg_pass_display_nameProduct", i + 1, nameProduct.getText()));

            WebElement priceProduct = listProduct.get(i).findElement(LocatorHelper.getBy(firstRow.get("element_priceProduct")));
            if (!priceProduct.getText().equals("$" + products.get(i).getPrice())) {
                System.out.println(msg("msg_not_pass_display_priceProduct", i + 1, products.get(i).getPrice(), priceProduct.getText()));
                driver.quit();
                return;
            }
            System.out.println(msg("msg_pass_display_priceProduct", (i + 1), priceProduct.getText()));

            WebElement quantityProduct = listProduct.get(i).findElement(LocatorHelper.getBy(firstRow.get("element_quantityProduct")));
            if (Integer.parseInt(quantityProduct.getText()) != products.get(i).getQuantity()) {
                System.out.println(msg("msg_not_pass_display_quantityProduct", i + 1, products.get(i).getQuantity(), quantityProduct.getText()));
                driver.quit();
                return;
            }
            System.out.println(msg("msg_pass_display_quantityProduct", quantityProduct.getText()));

        }
        WebElement btnContinueShopping = WaitElement.clickable(driver, LocatorHelper.getBy(firstRow.get("element_btnContinueShopping")), 15);
        if (!btnContinueShopping.isDisplayed()) {
            System.out.println(msg("msg_not_pass_display_btn", btnContinueShopping.getText()));
            driver.quit();
            return;
        }
        System.out.println(msg("msg_pass_display_btn", btnContinueShopping.getText()));
        WebElement btnCheckout = WaitElement.clickable(driver, LocatorHelper.getBy(firstRow.get("element_btnCheckout")), 15);
        if (!btnCheckout.isDisplayed()) {
            System.out.println(msg("msg_not_pass_display_btn", btnCheckout.getText()));
            driver.quit();
            return;
        }
        System.out.println(msg("msg_pass_display_btn", btnCheckout.getText()));
        System.out.println(msg("msg_pass_click_btn", btnCheckout.getText()));
        btnCheckout.click();
    }

}
