package vn.devpro.assignment67.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import vn.devpro.assignment67.models.ItemDemo;

import java.util.List;

public class ElementValidate {

    public static String validate(
            WebDriver driver,
            WebElement field,
            String fieldName,
            By errorLocator
    ) {
        List<WebElement> errors =
                (field != null) ? field.findElements(errorLocator)
                        : driver.findElements(errorLocator);

        if (!errors.isEmpty()) {
            return "❌ Error " + fieldName + ": '" + errors.get(0).getText() + "'";
        }

        if (field != null) {
            String tag = field.getTagName();

            // input / textarea
            if ("input".equalsIgnoreCase(tag) || "textarea".equalsIgnoreCase(tag)) {

                String value = field.getAttribute("value");

                if (value == null || value.trim().isEmpty()) {
                    return "❌ Error " + fieldName + ": 'This field is required.'";
                }

                return "✅ Field " + fieldName + ": " + value;
            }


            // select (thêm required check)
            if ("select".equalsIgnoreCase(tag)) {
                Select select = new Select(field);
                WebElement option = select.getFirstSelectedOption();
                String value = option.getAttribute("value");

                if (value == null || value.isEmpty()) {
                    return "❌ Error " + fieldName + ": 'This field is required.'";
                }

                return "✅ Field " + fieldName + ": " + option.getText();
            }

            // div hoặc element khác
            return "✅ Field " + fieldName + ": " + field.getText();
        }

        return "✅ Field " + fieldName + ": successfully";
    }

    public static boolean validateForm(WebDriver driver, List<ItemDemo> list, By error) {

        boolean isPass = true;

        for (int i = 1; i < list.size(); i++) {

            String msg = ElementValidate.validate(
                    driver,
                    list.get(i).getElement(),
                    list.get(i).getName(),
                    error
            );

            System.out.println(msg);

            // ❌ chỉ cần 1 field fail là cả form fail
            if (msg.contains("This field is required.")
                    || msg.startsWith("❌ Error")) {
                isPass = false;
            }
        }

        return isPass;
    }
    public static void clearAndType(WebElement element, String text) {

        try {
            // Scroll element vào giữa màn hình
            ((JavascriptExecutor) ((WrapsDriver) element).getWrappedDriver())
                    .executeScript(
                            "arguments[0].scrollIntoView({block:'center'});",
                            element
                    );

            element.click();

        } catch (ElementClickInterceptedException e) {
            // Fallback click bằng JS nếu bị che
            ((JavascriptExecutor) ((WrapsDriver) element).getWrappedDriver())
                    .executeScript("arguments[0].click();", element);
        }

        // Clear chuẩn cho Mac & Windows
        Keys selectAllKey = System.getProperty("os.name").toLowerCase().contains("mac")
                ? Keys.COMMAND
                : Keys.CONTROL;

        element.sendKeys(Keys.chord(selectAllKey, "a"), Keys.DELETE);

        if (text != null && !text.isBlank()) {
            element.sendKeys(text);
        }
    }
    public static void selectByVisibleText(WebElement selectElement, String text) {

        JavascriptExecutor js =
                (JavascriptExecutor) ((WrapsDriver) selectElement).getWrappedDriver();

        Select select = new Select(selectElement);

        // 🔥 TỰ ĐỘNG GÁN NAME NẾU CHƯA CÓ
        js.executeScript(
                """
                if (!arguments[0].getAttribute('data-field-name')) {
                    let name =
                        arguments[0].getAttribute('aria-label') ||
                        arguments[0].getAttribute('name') ||
                        arguments[0].getAttribute('id') ||
                        'Interest';
                    arguments[0].setAttribute('data-field-name', name);
                }
                """,
                selectElement
        );

        // 🔥 CLEAR select
        if (text == null || text.isBlank()) {
            js.executeScript(
                    """
                    arguments[0].selectedIndex = 0;
                    arguments[0].dispatchEvent(new Event('change'));
                    """,
                    selectElement
            );
            return;
        }

        try {
            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    selectElement
            );

            select.selectByVisibleText(text);

        } catch (ElementClickInterceptedException e) {
            js.executeScript(
                    """
                    const select = arguments[0];
                    const value = arguments[1];
                    for (let option of select.options) {
                        if (option.text.trim() === value.trim()) {
                            option.selected = true;
                            select.dispatchEvent(new Event('change'));
                            break;
                        }
                    }
                    """,
                    selectElement, text
            );
        }
    }

}
