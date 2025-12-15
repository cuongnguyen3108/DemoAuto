package day4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Demo3 {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.guru99.com/test/simple_context_menu.html");

//        Actions action = new Actions(driver);

//        WebElement doubleButton = driver.findElement(By.xpath("//button[text()='Double-Click Me To See Alert']"));
//        action.doubleClick(doubleButton).perform();
//        Thread.sleep(5000);
//        String alert = driver.switchTo().alert().getText();
//        System.out.println("Alert: " + alert);
//        driver.switchTo().alert().accept();
//        Thread.sleep(5000);

        WebElement rightButton = driver.findElement(By.xpath("//span[text()='right click me']"));
        Actions action = new Actions(driver);

        action.contextClick(rightButton).perform();
        Thread.sleep(5000);

        WebElement option = driver.findElement(By.xpath("//li/span[text()=\"Delete\"]"));
        option.click();
        Thread.sleep(5000);

        String alert = driver.switchTo().alert().getText();
        System.out.println("Alert: " + alert);
        driver.switchTo().alert().accept();
        Thread.sleep(5000);

        //        driver.quit();
    }
}
