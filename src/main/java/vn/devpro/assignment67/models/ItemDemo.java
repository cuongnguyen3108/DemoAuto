package vn.devpro.assignment67.models;

import org.openqa.selenium.*;
import vn.devpro.assignment67.utils.ElementValidate;
import vn.devpro.assignment67.utils.WaitElement;
import vn.devpro.assignment67.utils.helpers.LocatorHelper;

import java.util.List;
import java.util.Map;

import static vn.devpro.assignment67.utils.ElementValidate.fillRemainingFields;
import static vn.devpro.assignment67.utils.ElementValidate.submitEmailAndValidate;

public class ItemDemo {
    String name;
    WebElement element;
    String mgs;

    public ItemDemo() {
    }

    public ItemDemo(String name, WebElement element, String mgs) {
        this.name = name;
        this.element = element;
        this.mgs = mgs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WebElement getElement() {
        return element;
    }

    public void setElement(WebElement element) {
        this.element = element;
    }

    public String getMgs() {
        return mgs;
    }

    public void setMgs(String mgs) {
        this.mgs = mgs;
    }

    public static List<ItemDemo> fillForm(WebDriver driver, User user, By error, Map<String, String> listElement) {

        boolean isEmailValid = submitEmailAndValidate(driver, user.getEmail(), error, listElement);

        if (!isEmailValid) {
            System.out.println("❌ Email không hợp lệ → dừng form");
            return null;
        }

        List<ItemDemo> list = fillRemainingFields(driver, user, listElement);

        WebElement checkbox = WaitElement.present(driver,
                LocatorHelper.getBy(listElement.get("checkbox")), 10);
        ElementValidate.clearAndClickCheckbox(checkbox);

        WaitElement.visible(driver, LocatorHelper.getBy(listElement.get("btnSubmit")), 10).click();

        return list;
    }
}

