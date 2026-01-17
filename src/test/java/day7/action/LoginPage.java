package day7.action;

import day7.ui.LoginPageUI;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public void enterUseName(String name){
        driver.findElement(LoginPageUI.USERNAME_FIELD).sendKeys(name);

    }
    public void enterPassword(String password){
        driver.findElement(LoginPageUI.PASSWORD_FIELD).sendKeys(password);
    }
    public void clickLogin(){
        driver.findElement(LoginPageUI.LOGIN_BUTTON).click();

    }
}
