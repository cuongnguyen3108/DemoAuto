package pages;

import org.openqa.selenium.WebDriver;
import vn.devpro.assignment67.utils.ExelUtils;
import vn.devpro.assignment67.utils.WaitElement;

import java.util.List;
import java.util.Map;

public abstract class BasePage {
    protected final WebDriver driver;
    protected List<Map<String, String>> excelData;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    // 👉 Page nào cần Excel thì gọi
    protected void loadExcelData() {
        excelData = ExelUtils.readFIleExcelData(
                getFilePath(),
                getSheetName()
        );
    }

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
}
