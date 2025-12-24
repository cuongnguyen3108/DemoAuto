package day3.exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import vn.devpro.assignment67.models.ItemDemo;
import vn.devpro.assignment67.models.User;
import vn.devpro.assignment67.utils.ExelUtils;
import vn.devpro.assignment67.utils.helpers.FormElementData;

import java.util.List;
import java.util.Map;

public class UnHappyTestCase {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://saucelabs.com/request-demo");

        By error = By.xpath("./ancestor::div[contains(@class,'mktoFieldWrap')]/div[contains(@class,'mktoError')]");
        String filePath = "data-test-book-your-femo-here.xlsx";
        String sheetName = "data";
        List<Map<String, String>> list = ExelUtils.readFIleExcelData(filePath, sheetName);

        for (Map<String, String> data : list) {

            if (ExelUtils.hasRequiredData(data)) {
                continue;
            }
            List<ItemDemo> listForm = FormElementData.submitFormWithMissingFields(driver, new User(data.get("email"), data.get("firstName"), data.get("lastName"), data.get("country"), data.get("company"), data.get("interest"), data.get("comment"), data.get("phone")), error);
            if (listForm == null || list.isEmpty()) {
                System.out.println();
                continue;
            }
            System.out.println();
        }

        driver.quit();
    }
}
