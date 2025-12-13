package day3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class Demo2 {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://saucelabs.com/request-demo");

        String link1=driver.getWindowHandle();
        System.out.println("tab 1: "+driver.getTitle());
        // Mở tab mới để truy cập Google
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.open('https://google.com', '_blank');");
        Set<String> alltap=driver.getWindowHandles();
        for (String tap:alltap){
            if (!tap.equals(link1)){
                driver.switchTo().window(tap);
                break;
            }
        }

        System.out.println("tab 2: "+driver.getTitle());
        WebElement box= driver.findElement(By.name("q"));
        box.sendKeys("facebook");
        box.submit();
        Thread.sleep(3000);
        driver.quit();
    }
}
