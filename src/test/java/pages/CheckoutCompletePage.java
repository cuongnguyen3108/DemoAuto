package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import vn.devpro.assignment67.utils.WaitElement;
import vn.devpro.assignment67.utils.helpers.LocatorHelper;

import java.util.Map;

public class CheckoutCompletePage extends BasePage {

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
        loadExcelData();
    }

    @Override
    protected String getFilePath() {
        return "data-test.xltx";
    }

    @Override
    protected String getSheetName() {
        return "Complete";
    }

    @Override
    protected String getExpectedPath() {
        return "/checkout-complete.html";
    }

    public void complete() {
        System.out.println("\n\nPage Checkout: Complete!");
        Map<String, String> firstRow = excelData.get(0);

        WebElement completeHeader = WaitElement.visible(driver, LocatorHelper.getBy(firstRow.get("element_completeHeader")), 10);
        System.out.println(completeHeader.getText());
        WebElement completeText = WaitElement.visible(driver, LocatorHelper.getBy(firstRow.get("element_completeText")), 10);
        System.out.println(completeText.getText());
        WebElement btnBackHome = WaitElement.clickable(driver, LocatorHelper.getBy(firstRow.get("element_btnBackHome")), 15);
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

