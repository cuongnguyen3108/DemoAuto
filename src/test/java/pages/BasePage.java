package pages;

import org.openqa.selenium.WebDriver;
import vn.devpro.assignment67.utils.ExelUtils;
import vn.devpro.assignment67.utils.MessageUtils;
import vn.devpro.assignment67.utils.WaitElement;

import java.util.List;
import java.util.Map;

public abstract class BasePage {

    protected WebDriver driver;

    // Test data
    protected List<Map<String, String>> excelData;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    // 👉 Page nào cần data thì gọi
    protected void loadExcelData() {
        excelData = ExelUtils.readFileExcelData(
                getFilePath(),
                getSheetName()
        );
    }

    // 👉 Page nào cần message thì gọi
    protected void loadExcelMessageData() {
        MessageUtils.loadFromExcel(
                getFilePath(),
                "Messages"
        );
    }

    // Helper dùng chung cho Page
    protected String msg(String key, Object... args) {
        return MessageUtils.get(key, args);
    }
    // Bắt buộc Page con phải implement
    protected abstract String getFilePath();

    protected abstract String getSheetName();

    protected abstract String getExpectedPath();

    public boolean hasRedirectedTo(int timeoutSec) {
        try {
            return WaitElement.waitFor(driver, d -> {
                if (d == null) return false;

                String currentUrl = d.getCurrentUrl();
                System.out.println("⏳ Current URL: " + currentUrl);

                return currentUrl != null && currentUrl.contains(getExpectedPath());
            }, timeoutSec);
        } catch (Exception e) {
            System.out.println("❌ Timeout waiting for page: " + getExpectedPath());
            System.out.println("❌ Final URL: " + driver.getCurrentUrl());
            return false;
        }
    }
    public void verifyPageNavigation(int timeoutSec) {
        if (!hasRedirectedTo(timeoutSec)) {
            driver.quit();
            throw new RuntimeException("❌ Navigation failed to " + getClass().getSimpleName());
        }
    }
}
