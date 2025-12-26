package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import vn.devpro.assignment67.models.Product;
import vn.devpro.assignment67.utils.WaitElement;
import vn.devpro.assignment67.utils.helpers.LocatorHelper;

import java.util.List;
import java.util.Map;

public class CheckoutOverviewPage extends BasePage {

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
        loadExcelData();
    }

    @Override
    protected String getFilePath() {
        return "data-test-product-purchase.xlsx";
    }

    @Override
    protected String getSheetName() {
        return "Overview";
    }

    @Override
    protected String getExpectedPath() {
        return "/checkout-step-two.html";
    }

    public void finish(List<Product> products) {
        System.out.println("\n\nPage Checkout: Overview");
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
        double sumPrice = 0.0;
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
            sumPrice += products.get(i).getPrice();

            WebElement quantityProduct = listProduct.get(i).findElement(LocatorHelper.getBy(firstRow.get("element_quantityProduct")));
            if (Integer.parseInt(quantityProduct.getText()) != products.get(i).getQuantity()) {
                System.out.println(msg("msg_not_pass_display_quantityProduct", i + 1, products.get(i).getQuantity(), quantityProduct.getText()));
                driver.quit();
                return;
            }
            System.out.println(msg("msg_pass_display_quantityProduct", quantityProduct.getText()));
        }

        WebElement payment = WaitElement.visible(driver,LocatorHelper.getBy(firstRow.get("element_payment")), 10);
        System.out.println("\nPayment Information: " + payment.getText());
        WebElement shipping = WaitElement.visible(driver, LocatorHelper.getBy(firstRow.get("element_shipping")), 10);
        System.out.println("Shipping Information: " + shipping.getText());
        System.out.println("Price Total");
        WebElement subtotal = WaitElement.visible(driver, LocatorHelper.getBy(firstRow.get("element_subtotal")), 10);
        if (Double.parseDouble(subtotal.getText().replace("Item total: $", "")) != sumPrice) {
            System.out.println("Item total price is incorrect");
            return;
        }
        System.out.println(subtotal.getText());
        WebElement tax = WaitElement.visible(driver, LocatorHelper.getBy(firstRow.get("element_tax")), 10);
        System.out.println(tax.getText());
        double taxPrice = Double.parseDouble(tax.getText().replace("Tax: $", ""));
        double totalPrice = sumPrice + taxPrice;

        WebElement total = WaitElement.visible(driver,  LocatorHelper.getBy(firstRow.get("element_total")), 10);
        if (Double.parseDouble(total.getText().replace("Total: $", "")) != totalPrice) {
            System.out.println("Total price is incorrect");
            return;
        }
        System.out.println(total.getText());
        WebElement btnFinish = WaitElement.clickable(driver, LocatorHelper.getBy(firstRow.get("element_btnFinish")), 10);
        if (!btnFinish.isDisplayed()) {
            System.out.println(msg("msg_not_pass_display_btn", btnFinish.getText()));
            driver.quit();
            return;
        }
        System.out.println(msg("msg_pass_display_btn", btnFinish.getText()));
        System.out.println(msg("msg_pass_click_btn", btnFinish.getText()));
        btnFinish.click();
    }

}
