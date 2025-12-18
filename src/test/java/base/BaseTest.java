package base;

import org.openqa.selenium.WebDriver;
import vn.devpro.assignment67.utils.WaitElement;

public class BaseTest {
    protected static boolean isPageRedirected(
            WebDriver driver,
            String expectedPath
    ) {
        try {
            boolean isLoaded = WaitElement.waitFor(driver, d -> {
                if (d == null) return false;
                String url = d.getCurrentUrl();
                return url != null && url.contains(expectedPath);
            }, 10);

            if (isLoaded) {
                System.out.println("✅ Page redirection successful - Page loaded: " + expectedPath);
            }
            return isLoaded;

        } catch (Exception e) {
            System.out.println("❌ Page redirection failed - Expected path: "
                    + expectedPath + " | Current URL: " + driver.getCurrentUrl());
            return false;
        }
    }


}

