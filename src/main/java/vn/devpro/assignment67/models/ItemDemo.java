package vn.devpro.assignment67.models;

import org.openqa.selenium.*;
import vn.devpro.assignment67.utils.ElementValidate;
import vn.devpro.assignment67.utils.WaitElement;

import java.util.ArrayList;
import java.util.List;

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

    public static List<ItemDemo> fillForm(WebDriver driver, User user, By error) {
        List<ItemDemo> list = new ArrayList<ItemDemo>();
        WebElement inputEmail = WaitElement.visible(driver, By.xpath("//input[@id=\"Email\"]"), 10);
        ElementValidate.clearAndType(inputEmail, user.getEmail());
        WebElement btnSubmit = WaitElement.visible(driver, By.xpath("//button[@class=\"mktoButton\"]"), 10);
        btnSubmit.click();
        String msgEmail = ElementValidate.validate(driver, inputEmail, "Email", error);
        if (msgEmail.contains("Must be valid email")) {
            System.out.println(msgEmail);
            return null;
        } else {
            System.out.println(msgEmail);
        }

        list.add(new ItemDemo("Email", inputEmail, msgEmail));

        WebElement inputFirstName = WaitElement.visible(driver, By.xpath("//input[@id=\"FirstName\"]"), 15);
        ElementValidate.clearAndType(inputFirstName, user.getFirstName());
        list.add(new ItemDemo("FirstName", inputFirstName, ""));

        WebElement inputLastName = WaitElement.visible(driver, By.xpath("//input[@id=\"LastName\"]"), 10);
        ElementValidate.clearAndType(inputLastName, user.getLastName());
        list.add(new ItemDemo("LastName", inputLastName, ""));

        WebElement inputPhone = WaitElement.visible(driver, By.xpath("//input[@id=\"Phone\"]"), 10);
        ElementValidate.clearAndType(inputPhone, String.valueOf(user.getPhone()));
        list.add(new ItemDemo("Phone", inputPhone, ""));

        WebElement selectCountry = WaitElement.visible(driver, By.xpath("//select[@id='Country']"), 15);
        ElementValidate.selectByVisibleText(selectCountry, user.getCountry());
        list.add(new ItemDemo("Country", selectCountry, ""));

        WebElement inputCompany = WaitElement.visible(driver, By.xpath("//input[@id=\"Company\"]"), 10);
        ElementValidate.clearAndType(inputCompany, user.getCompany());
        list.add(new ItemDemo("Company", inputCompany, ""));

        WebElement selectInterest = WaitElement.visible(driver, By.xpath("//select[@id=\"Solution_Interest__c\"]"), 10);
        ElementValidate.selectByVisibleText(selectInterest, user.getInterest());
        list.add(new ItemDemo("Interest", selectInterest, ""));

        WebElement areaComment = WaitElement.visible(driver, By.xpath("//textarea[@id=\"Sales_Contact_Comments__c\"]"), 10);
        ElementValidate.clearAndType(areaComment, user.getComment());
        list.add(new ItemDemo("Comment", areaComment, ""));

        WebElement checkbox = WaitElement.present(driver, By.xpath("//div[contains(@class,\"mktoCheckboxList\")]/input"), 20);
        checkbox.click();
        btnSubmit.click();

        return list;
    }
}

