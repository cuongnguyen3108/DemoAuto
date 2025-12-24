package vn.devpro.assignment67.utils.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import vn.devpro.assignment67.models.ItemDemo;
import vn.devpro.assignment67.models.User;
import vn.devpro.assignment67.utils.ElementValidate;

import java.util.List;
import java.util.Map;

public class FormElementData {
    public static List<ItemDemo> submitFormWithMissingFields(WebDriver driver, User user, By error, Map<String, String> listElement) {
        List<ItemDemo> list = ItemDemo.fillForm(driver, user, error,listElement);

        if (list == null || list.isEmpty()) {
            return null;
        }

        if (!ElementValidate.validateForm(driver, list, error)) {
            return null;
        }

        return list;
    }
}
