package vn.devpro.assignment67.utils.helpers;

import org.openqa.selenium.By;

public class LocatorHelper {
    public static By getBy(String locator) {
        if (locator == null || locator.trim().isEmpty()) {
            throw new RuntimeException("❌ Locator null hoặc rỗng");
        }

        locator = locator.trim();

        if (locator.startsWith("id=")) {
            return By.id(locator.replace("id=", ""));
        }
        if (locator.startsWith("name=")) {
            return By.name(locator.replace("name=", ""));
        }
        if (locator.startsWith("class=") || locator.startsWith("className=")) {
            return By.className(locator.replace("class=", "").replace("className=", ""));
        }
        if (locator.startsWith("tag=")) {
            return By.tagName(locator.replace("tag=", ""));
        }
        if (locator.startsWith("linkText=")) {
            return By.linkText(locator.replace("linkText=", ""));
        }
        if (locator.startsWith("partialLinkText=")) {
            return By.partialLinkText(locator.replace("partialLinkText=", ""));
        }
        if (locator.startsWith("css=")) {
            return By.cssSelector(locator.replace("css=", ""));
        }
        if (locator.startsWith("xpath=")) {
            return By.xpath(locator.replace("xpath=", ""));
        }

        throw new RuntimeException("❌ Locator không hỗ trợ: " + locator);
    }
}
