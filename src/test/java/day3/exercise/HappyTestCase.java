package day3.exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import vn.devpro.assignment67.models.ItemDemo;
import vn.devpro.assignment67.models.User;
import vn.devpro.assignment67.utils.Ads;
import vn.devpro.assignment67.utils.ExelUtils;
import vn.devpro.assignment67.utils.helpers.FormElementData;
import vn.devpro.assignment67.utils.helpers.LocatorHelper;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class HappyTestCase {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://saucelabs.com/request-demo");
        String filePath = "data-test-book-your-femo-here.xlsx";
        String sheetName = "data";
        String sheetElement ="Element";
        List<Map<String, String>> list = ExelUtils.readFileExcelData(filePath, sheetName);
        List<Map<String, String>> listElement = ExelUtils.readFileExcelData(filePath, sheetElement);

        Map<String, String> firstRowElement = listElement.get(0);

        By error = LocatorHelper.getBy(firstRowElement.get("error"));

        for (Map<String, String> data : list) {
            if (!ExelUtils.hasRequiredData(data)) {
                continue;
            }

            List<ItemDemo> listForm = FormElementData.submitFormWithMissingFields(driver, new User(data.get("email"), data.get("firstName"), data.get("lastName"), data.get("country"), data.get("company"), data.get("interest"), data.get("comment"), data.get("phone")), error,firstRowElement);
            if (listForm == null || list.isEmpty()) {
                System.out.println();
                continue;
            }
            System.out.println();
        }

        Ads.close(driver, 15);

        String pageTitle2 = driver.getTitle();
        if (Objects.equals(pageTitle2, "Thank you")) {
            System.out.println("✅ Test case successfully");
        } else {
            System.out.println(pageTitle2);
        }
        driver.quit();
    }
}
