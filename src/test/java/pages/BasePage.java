package pages;

import org.openqa.selenium.WebDriver;
import vn.devpro.assignment67.utils.WaitElement;

public abstract class BasePage {
    protected final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected abstract String getExpectedPath();

    public boolean isPageLoaded(int timeoutSec) {
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
