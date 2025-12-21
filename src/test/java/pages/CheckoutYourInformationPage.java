package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import vn.devpro.assignment67.models.Product;
import vn.devpro.assignment67.utils.ElementValidate;
import vn.devpro.assignment67.utils.WaitElement;

import java.util.List;

public class CheckoutYourInformationPage extends BasePage {
    public CheckoutYourInformationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String getExpectedPath() {
        return "/checkout-step-one.html";
    }

    public void fillInformation(String firstName, String lastName, String postalCode, List<Product> products) {
        System.out.println("\n\nPage Checkout: Your Information");
        WebElement totalShoppingCart = WaitElement.visible(driver, By.className("shopping_cart_badge"), 10);

        if (Integer.parseInt(totalShoppingCart.getText()) != products.size()) {
            System.out.println("❌ The total number ofF products in the cart is incorrect!");
        } else {
            System.out.println("✅ The total number of products in the cart:: " + totalShoppingCart.getText());
        }

        WebElement inputFirstName = WaitElement.visible(driver, By.id("first-name"), 10);
        inputFirstName.sendKeys(firstName);
        WebElement inputLastName = WaitElement.visible(driver, By.id("last-name"), 10);
        inputLastName.sendKeys(lastName);
        WebElement inputPostalCode = WaitElement.visible(driver, By.id("postal-code"), 10);
        inputPostalCode.sendKeys(postalCode);
        WebElement btnContinue = WaitElement.clickable(driver, By.id("continue"), 10);
        btnContinue.click();
        String msg = ElementValidate.validate(driver, null, "submit", By.xpath("//div[@class='error-message-container error']/h3"));
        System.out.println(msg);
    }
}
