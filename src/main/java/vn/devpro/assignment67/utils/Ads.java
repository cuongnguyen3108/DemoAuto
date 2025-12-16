package vn.devpro.assignment67.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Ads {
    public static void close(WebDriver driver, int timeoutSec) {
        long end = System.currentTimeMillis() + timeoutSec * 1000;
        boolean closed = false;

        while (System.currentTimeMillis() < end && !closed) {
            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;

                // 1️⃣ Try in main document
                Object result = js.executeScript("""
                var btn = document.querySelector("button[data-test-id='ModalCloseButton']");
                if (btn) {
                    btn.click();
                    return true;
                }
                return false;
            """);

                if (Boolean.TRUE.equals(result)) {
                    System.out.println("✅ Advertisement closed (main document)");
                    closed = true;
                    break;
                }

                // 2️⃣ Try in all iframes
                List<WebElement> iframes = driver.findElements(By.tagName("iframe"));

                for (WebElement iframe : iframes) {
                    try {
                        driver.switchTo().frame(iframe);

                        Object iframeResult = js.executeScript("""
                        var btn = document.querySelector("button[data-test-id='ModalCloseButton']");
                        if (btn) {
                            btn.click();
                            return true;
                        }
                        return false;
                    """);

                        driver.switchTo().defaultContent();

                        if (Boolean.TRUE.equals(iframeResult)) {
                            System.out.println("✅ Advertisement closed (iframe)");
                            closed = true;
                            break;
                        }
                    } catch (Exception e) {
                        driver.switchTo().defaultContent();
                    }
                }

                Thread.sleep(300);

            } catch (Exception ignored) {}
        }

        if (!closed) {
            System.out.println("⚠️ Advertisement not found – skipped");
        }
    }

}
