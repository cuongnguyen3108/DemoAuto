package vn.devpro.assignment67.utils.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import vn.devpro.assignment67.models.ItemDemo;
import vn.devpro.assignment67.models.User;
import vn.devpro.assignment67.utils.ElementValidate;

import java.util.List;

public class FormElementData {
    public static List<ItemDemo> submitFormWithMissingFields(WebDriver driver, User user, By error) {
        List<ItemDemo> list = ItemDemo.fillForm(driver, user, error);

        if (list == null || list.isEmpty()) {
            return null;
        }

        if (!ElementValidate.validateForm(driver, list, error)) {
            return null;
        }

        return list;
    }
}
