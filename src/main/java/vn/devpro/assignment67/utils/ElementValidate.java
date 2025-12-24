package vn.devpro.assignment67.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import vn.devpro.assignment67.models.ItemDemo;
import vn.devpro.assignment67.models.User;

import java.util.ArrayList;
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
    public static boolean submitEmailAndValidate(WebDriver driver, String email, By error) {

        WebElement inputEmail = WaitElement.visible(driver, By.id("Email"), 10);
        ElementValidate.clearAndType(inputEmail, email);

        WebElement btnSubmit = WaitElement.visible(driver,
                By.cssSelector("button.mktoButton"), 10);
        btnSubmit.click();

        String msg = ElementValidate.validate(driver, inputEmail, "Email", error);
        System.out.println(msg);

        return !msg.contains("Must be valid email");
    }


    public static boolean validateForm(WebDriver driver, List<ItemDemo> list, By error) {

        boolean isPass = true;

        for (int i = 0; i < list.size(); i++) {

            String msg = ElementValidate.validate(
                    driver,
                    list.get(i).getElement(),
                    list.get(i).getName(),
                    error
            );

            System.out.println(msg);
            // ❌ chỉ cần 1 field fail là cả form fail
            if (msg.contains("This field is required.")
                    || msg.startsWith("❌ Error") || msg.contains("Must be valid email")) {
                isPass = false;
            }
        }

        return isPass;
    }
    public static List<ItemDemo> fillRemainingFields(WebDriver driver, User user) {

        List<ItemDemo> list = new ArrayList<>();

        WebElement inputFirstName = WaitElement.visible(driver, By.id("FirstName"), 10);
        ElementValidate.clearAndType(inputFirstName, user.getFirstName());
        list.add(new ItemDemo("FirstName", inputFirstName, ""));

        WebElement inputLastName = WaitElement.visible(driver, By.id("LastName"), 10);
        ElementValidate.clearAndType(inputLastName, user.getLastName());
        list.add(new ItemDemo("LastName", inputLastName, ""));

        WebElement inputPhone = WaitElement.visible(driver, By.id("Phone"), 10);
        ElementValidate.clearAndType(inputPhone, user.getPhone());
        list.add(new ItemDemo("Phone", inputPhone, ""));

        WebElement selectCountry = WaitElement.visible(driver, By.id("Country"), 10);
        ElementValidate.selectByVisibleText(selectCountry, user.getCountry());
        list.add(new ItemDemo("Country", selectCountry, ""));

        WebElement inputCompany = WaitElement.visible(driver, By.id("Company"), 10);
        ElementValidate.clearAndType(inputCompany, user.getCompany());
        list.add(new ItemDemo("Company", inputCompany, ""));

        WebElement selectInterest = WaitElement.visible(driver,
                By.id("Solution_Interest__c"), 10);
        ElementValidate.selectByVisibleText(selectInterest, user.getInterest());
        list.add(new ItemDemo("Interest", selectInterest, ""));

        WebElement areaComment = WaitElement.visible(driver,
                By.id("Sales_Contact_Comments__c"), 10);
        ElementValidate.clearAndType(areaComment, user.getComment());
        list.add(new ItemDemo("Comment", areaComment, ""));

        return list;
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
    public static void clearAndClickCheckbox(WebElement checkbox) {
        if (checkbox == null) {
            throw new IllegalArgumentException("Checkbox is null");
        }

        // Nếu checkbox đã được check → bỏ check
        if (checkbox.isSelected()) {
            checkbox.click();
        }

        // Click lại để đảm bảo được check
        checkbox.click();
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
