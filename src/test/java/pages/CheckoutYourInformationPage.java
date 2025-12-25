package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import vn.devpro.assignment67.models.Product;
import vn.devpro.assignment67.utils.ElementValidate;
import vn.devpro.assignment67.utils.WaitElement;
import vn.devpro.assignment67.utils.helpers.LocatorHelper;

import java.util.List;
import java.util.Map;

public class CheckoutYourInformationPage extends BasePage {
    public CheckoutYourInformationPage(WebDriver driver) {
        super(driver);
        loadExcelData();
    }

    @Override
    protected String getFilePath() {
        return "data-test-product-purchase.xlsx";
    }

    @Override
    protected String getSheetName() {
        return "Information";
    }

    @Override
    protected String getExpectedPath() {
        return "/checkout-step-one.html";
    }

    public void fillInformation(String firstName, String lastName, String postalCode, List<Product> products) {
        System.out.println("\n\nPage Checkout: Your Information");
        Map<String, String> firstRow = excelData.get(0);

        WebElement totalShoppingCart = WaitElement.visible(driver, LocatorHelper.getBy(firstRow.get("element_totalShoppingCart")), 10);

        if (Integer.parseInt(totalShoppingCart.getText()) != products.size()) {
            System.out.println("❌ The total number off products in the cart is incorrect!");
        } else {
            System.out.println(msg("msg_pass_cart_badge",totalShoppingCart.getText()));
        }

        for (Map<String, String> data : excelData) {
            WebElement inputFirstName = WaitElement.visible(driver, LocatorHelper.getBy(firstRow.get("element_inputFirstName")), 10);
            ElementValidate.clearAndType(inputFirstName, data.get(firstName));
            WebElement inputLastName = WaitElement.visible(driver, LocatorHelper.getBy(firstRow.get("element_inputLastName")), 10);
            ElementValidate.clearAndType(inputLastName, data.get(lastName));
            WebElement inputPostalCode = WaitElement.visible(driver, LocatorHelper.getBy(firstRow.get("element_inputPostalCode")), 10);
            ElementValidate.clearAndType(inputPostalCode, data.get(postalCode));
            WebElement btnContinue = WaitElement.clickable(driver, LocatorHelper.getBy(firstRow.get("element_btnContinue")), 10);
            btnContinue.click();
            String msg = ElementValidate.validate(driver, null, "submit", LocatorHelper.getBy(firstRow.get("element_msg")));
            System.out.println(msg);
        }
    }
}
