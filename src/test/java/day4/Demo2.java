package day4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Demo2 {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.guru99.com/test/radio.html");
//        List<WebElement> checkbooks =new ArrayList<WebElement>();
//        checkbooks.add(driver.findElement(By.id("vfb-6-0")));
//        checkbooks.add(driver.findElement(By.id("vfb-6-1")));
//        for (WebElement checkbook : checkbooks) {
//            checkbook.click();
//            System.out.println("Checkbook value select: "+checkbook.getAttribute("value"));
//            System.out.println("Is selected: "+checkbook.isSelected());
//        }
        WebElement radio1 = driver.findElement(By.id("vfb-7-1"));
        WebElement radio2 = driver.findElement(By.id("vfb-7-2"));
        WebElement radio3 = driver.findElement(By.id("vfb-7-3"));
      radio3.click();

//        driver.quit();
    }
}
