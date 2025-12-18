package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import vn.devpro.assignment67.utils.ElementValidate;
import vn.devpro.assignment67.utils.WaitElement;

public class CheckoutYourInformationPage extends BasePage {
    public CheckoutYourInformationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String getExpectedPath() {
        return "/checkout-step-one.html";
    }

    public void fillInformation(String firstName, String lastName, String postalCode) {
        System.out.println("\n\nPage Checkout: Your Information");

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
