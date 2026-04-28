package com.palle;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class LinkedInAutomation {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void setup() {
    	System.setProperty("webdriver.chrome.driver","C:\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void loginTest() throws InterruptedException {

        driver.get("https://www.linkedin.com/login");

        WebElement username = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("username"))
        );

        username.sendKeys("thotibhuna@gmail.com");

        driver.findElement(By.id("password")).sendKeys("Bhuvana@123");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(5000);
        System.out.println(driver.getCurrentUrl());

        Assert.assertTrue(driver.getCurrentUrl().contains("feed"));
        System.out.println("Login Successful");

        List<WebElement> posts = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//div[contains(@class,'feed-shared-update-v2')]")
                )
        );

        for (int i = 0; i < 5 && i < posts.size(); i++) {
            System.out.println("Post " + (i + 1));
            System.out.println(posts.get(i).getText());
            System.out.println("----------------------");
        }
    }

    @AfterTest
    public void close() throws InterruptedException {
        Thread.sleep(10000);
        driver.quit();
    }
}