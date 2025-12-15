package vn.devpro.assignment67.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ElementValidate {
    public static String validate(WebElement field, String fieldName) {

        List<WebElement> errors = field.findElements(
                By.xpath("./ancestor::div[contains(@class,'mktoFieldWrap')]/div[contains(@class,'mktoError')]")
        );

        if (!errors.isEmpty()) {
            return "❌ Error " + fieldName + ": " + errors.get(0).getText();
        }

        return "✅ Field " + fieldName + ": " + field.getAttribute("value");
    }
}
