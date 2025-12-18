package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import vn.devpro.assignment67.utils.WaitElement;

public class CheckoutCompletePage extends BasePage {

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String getExpectedPath() {
        return "/checkout-complete.html";
    }

    public void complete() {
        System.out.println("\n\nPage Checkout: Complete!");

        WebElement completeHeader = WaitElement.visible(driver, By.xpath("//h2[@class=\"complete-header\"]"), 10);
        System.out.println(completeHeader.getText());
        WebElement completeText = WaitElement.visible(driver, By.xpath("//div[@class=\"complete-text\"]"), 10);
        System.out.println(completeText.getText());
        WebElement btnBackHome = WaitElement.clickable(driver, By.id("back-to-products"), 15);
        if (!btnBackHome.isDisplayed()) {
            System.out.println("❌ Button " + btnBackHome.getText() + " is not displayed");
            driver.quit();
            return;
        }
        System.out.println("✅ Button " + btnBackHome.getText() + " is displayed");
        System.out.println("✅ Button " + btnBackHome.getText() + " is clicked");
        btnBackHome.click();
    }

}

