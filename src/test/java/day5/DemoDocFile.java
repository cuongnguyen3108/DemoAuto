package day5;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import vn.devpro.assignment67.utils.ExelUtils;
import java.util.List;
import java.util.Map;

public class DemoDocFile {
    public static void main(String[] args) {
        try {
//            FileInputStream file = new FileInputStream("data-test.xltx");
//            Workbook workbook = new XSSFWorkbook(file);
//            Sheet sheet = workbook.getSheetAt(0);
//            List<Login> list = new ArrayList<>();
//
//            DataFormatter dataFormatter = new DataFormatter();
//            for (Row row : sheet) {
//                if (row.getRowNum() == 0) continue;
//                Login login = new Login();
//                login.username = dataFormatter.formatCellValue(row.getCell(0)).trim();
//                login.password = dataFormatter.formatCellValue(row.getCell(1)).trim();
//
//                if (!login.username.isEmpty() || !login.password.isEmpty()) {
//                    System.out.println("username: " + login.username);
//                    System.out.println("password: " + login.password);
//                    list.add(login);
//                }
//            }
            String filePath = "data-test.xltx";
            String sheetName = "Book1";

            List<Map<String, String>> list = ExelUtils.readFIleExcelData(filePath, sheetName);

            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();
            for (Map<String, String> data : list) {
                driver.get("https://www.saucedemo.com/");
                LoginPage loginPage = new LoginPage(driver);
                if (!loginPage.hasRedirectedTo(10)) {
                    driver.quit();
                    return;
                }
                loginPage.login(data.get("username"), data.get("password"));
            }


            driver.quit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
