package day1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Exercise1 {
    static WebDriver driver = null;

    public static void main(String[] args) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://serenity-bdd.github.io/");
        driver.quit();
    }
}
