package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import vn.devpro.assignment67.utils.ElementValidate;
import vn.devpro.assignment67.utils.ExelUtils;
import vn.devpro.assignment67.utils.WaitElement;
import vn.devpro.assignment67.utils.helpers.LocatorHelper;

import java.util.Map;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
        loadExcelData();
    }

    @Override
    protected String getFilePath() {
        return "data-test.xltx";
    }

    @Override
    protected String getSheetName() {
        return "Login";
    }

    @Override
    protected String getExpectedPath() {
        return "/";
    }

    public void login(String username, String password) {
        String pageUrlLogin = driver.getCurrentUrl();
        System.out.println("Url Page: " + pageUrlLogin);
        System.out.println("\n\nLogin");
        Map<String, String> firstRow = excelData.get(0);
        for (Map<String, String> data : excelData) {
            WebElement inputUsername = WaitElement.visible(driver, LocatorHelper.getBy(firstRow.get("element_username")), 10);
            ElementValidate.clearAndType(inputUsername,data.get(username));
            WebElement inputPassword = WaitElement.visible(driver,LocatorHelper.getBy(firstRow.get("element_password")), 10);
            ElementValidate.clearAndType(inputPassword,data.get(password));

            WebElement btnLogin = WaitElement.visible(driver,LocatorHelper.getBy(firstRow.get("element_btnLogin")), 10);
            btnLogin.click();

            String msg = ElementValidate.validate(driver, null, "Login", LocatorHelper.getBy(firstRow.get("element_msg")));
            System.out.println(msg);
        }


    }

}
