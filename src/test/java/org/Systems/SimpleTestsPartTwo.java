package org.Systems;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SimpleTestsPartTwo {

    WebDriver driver;

    @BeforeMethod
    public void setup() throws IOException {

        System.setProperty("webdriver.chrome.driver",
                "./src/test/resources/drivers/chromedriver.exe");

        driver = new ChromeDriver();
        driver.navigate().to("https://www.t-systems.com/ru/ru/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("./target/screenshot.png"));
    }

    @Test
    public void screenshootAndInteractTest() {

        WebElement searchBox = driver.findElement(By.linkText("Контакты"));

        searchBox.click();

        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}