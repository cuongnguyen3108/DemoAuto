package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import vn.devpro.assignment67.utils.ElementValidate;
import vn.devpro.assignment67.utils.WaitElement;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String getExpectedPath() {
        return "/";
    }

    public void login(String username, String password) {
        String pageUrlLogin = driver.getCurrentUrl();
        System.out.println("Url Page: " + pageUrlLogin);

        System.out.println("\n\nLogin");

        WebElement inputUsername = WaitElement.visible(driver, By.xpath("//input[@id='user-name']"), 10);
        inputUsername.sendKeys(username);

        WebElement inputPassword = WaitElement.visible(driver, By.xpath("//input[@id='password']"), 10);
        inputPassword.sendKeys(password);
        WebElement btnLogin = WaitElement.visible(driver, By.xpath("//input[@id=\"login-button\"]"), 10);
        btnLogin.click();

        String msg = ElementValidate.validate(driver, null, "Login", By.cssSelector(".error-message-container h3"));
        System.out.println(msg);
    }

}
