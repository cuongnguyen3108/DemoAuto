package day4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class UploadFileTest {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.guru99.com/test/upload/");
        String filePath = new File("test.txt").getAbsolutePath();
        WebElement upFile = driver.findElement(By.xpath("//input[@name=\"uploadfile_0\"]"));
        upFile.sendKeys(filePath);
        WebElement checkbook = driver.findElement(By.xpath("//input[@class=\"field_check\"]"));
        checkbook.click();
        WebElement btnSubmit = driver.findElement(By.xpath("//button[@id='submitbutton']"));
        btnSubmit.click();

        WebElement successMsg = driver.findElement(By.xpath("//h3[@id='res']"));

        System.out.println(successMsg.getText());
//        driver.quit();
    }
}
