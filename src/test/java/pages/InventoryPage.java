package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import vn.devpro.assignment67.models.Product;
import vn.devpro.assignment67.utils.WaitElement;
import vn.devpro.assignment67.utils.helpers.LocatorHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InventoryPage extends BasePage {

    public InventoryPage(WebDriver driver) {
        super(driver);
        loadExcelData();
        loadExcelMessageData();
    }

    @Override
    protected String getFilePath() {
        return "data-test-product-purchase.xlsx";
    }

    @Override
    protected String getSheetName() {
        return "Inventory";
    }

    @Override
    protected String getExpectedPath() {
        return "/inventory.html";
    }

    public List<Product> addProductToCart(String quantity) {
        System.out.println("\n\nPage Add To Cart");
        Map<String, String> firstRow = excelData.get(0);

        WebElement sortProduct = WaitElement.clickable(driver, LocatorHelper.getBy(firstRow.get("element_selectSort")), 5);
        Select selectSort = new Select(sortProduct);
        selectSort.selectByValue(firstRow.get("selectSort"));

        List<WebElement> listProduct = WaitElement.visibleElements(driver, LocatorHelper.getBy(firstRow.get("element_listProduct")), 10);
        List<Product> products = new ArrayList<>();
        int q = 0;
        for (Map<String, String> data : excelData) {
            int requestedQty = 0;

            try {
                requestedQty = Integer.parseInt(data.get(quantity));
            } catch (Exception e) {
                System.out.println("⚠️ Skip row: quantity must be an integer greater than 0 (value = "
                        + requestedQty + ")");

                // ❌ Không phải số → bỏ qua dòng này
                continue;
            }
            if (requestedQty <= 0) {
                System.out.println("⚠️ Skip row: quantity must be an integer greater than 0 (value = "
                        + requestedQty + ")");

                continue;
            }
            q = Math.min(listProduct.size(),requestedQty);
            for (int i = 0; i < q; i++) {
                Product product = new Product();
                WebElement addToCartProduct = listProduct.get(i).findElement(LocatorHelper.getBy(firstRow.get("element_addToCartProduct")));
                addToCartProduct.click();
                WebElement nameProduct = listProduct.get(i).findElement(LocatorHelper.getBy(firstRow.get("element_nameProduct")));
                System.out.println(msg("msg_pass_display_nameProduct", i + 1, nameProduct.getText()));
                product.setProductName(nameProduct.getText());
                WebElement priceProduct = listProduct.get(i).findElement(LocatorHelper.getBy(firstRow.get("element_priceProduct")));
                System.out.println(msg("msg_pass_display_priceProduct", (i + 1), priceProduct.getText()));
                String priceNumber = priceProduct.getText().replace("$", "");
                product.setPrice(Double.parseDouble(priceNumber));

                int quantityProduct = 1;
                product.setQuantity(quantityProduct);
                System.out.println(msg("msg_pass_display_quantityProduct", quantityProduct));
                products.add(product);
            }
        }

        WebElement totalShoppingCart = WaitElement.visible(driver, LocatorHelper.getBy(firstRow.get("element_totalShoppingCart")), 10);

        if (Integer.parseInt(totalShoppingCart.getText()) != q) {
            System.out.println(msg("msg_not_pass_cart_badge2"));
        } else {
            System.out.println(msg("msg_pass_cart_badge", totalShoppingCart.getText()));
            totalShoppingCart.click();
        }
        return products;
    }


}
