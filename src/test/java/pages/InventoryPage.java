package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import vn.devpro.assignment67.models.Product;
import vn.devpro.assignment67.utils.WaitElement;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage extends BasePage {

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String getExpectedPath() {
        return "/inventory.html";
    }

    public List<Product> addProductToCart(int quantity) {
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
        if (Integer.parseInt(totalShoppingCart.getText()) != q) {
            System.out.println("The total number ofFproducts in the cart is incorrect!");
        } else {
            System.out.println("The total number of products in the cart:: " + totalShoppingCart.getText());
            totalShoppingCart.click();
        }
        return products;
    }


}
