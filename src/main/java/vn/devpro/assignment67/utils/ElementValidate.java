package vn.devpro.assignment67.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ElementValidate {
    public static String validate(WebDriver driver, WebElement field, String fieldName, By errorLocator) {

        List<WebElement> errors = (field != null) ? field.findElements(errorLocator) : driver.findElements(errorLocator);

        if (!errors.isEmpty()) {
            return "❌ Error " + fieldName + ": '" + errors.get(0).getText() + "'";
        }

        if (field != null) {
            return "✅ Field " + fieldName + ": " + field.getAttribute("value");
        }

        return "✅ Field " + fieldName + ": successfully";
    }

}
