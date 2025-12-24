package vn.devpro.assignment67.utils.helpers;

import org.openqa.selenium.WebDriver;
import vn.devpro.assignment67.utils.ElementValidate;
import vn.devpro.assignment67.utils.WaitElement;

import static vn.devpro.assignment67.utils.helpers.LocatorHelper.getBy;

public class ActionHelper {

    public static void click(WebDriver driver, String locator,int second) {
        WaitElement.clickable(driver, getBy(locator), second).click();
    }

    public static void sendKeys(WebDriver driver, String locator, String value,int second) {
        ElementValidate.clearAndType( WaitElement.visible(driver, getBy(locator), second),value);
    }

}
