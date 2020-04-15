package org.Systems;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SimpleTests {

    WebDriver driver;

    @BeforeClass
    public void setup() {

        System.setProperty("webdriver.chrome.driver",
                "./src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();

    }

    @BeforeMethod
    public void navigate() {

        driver.get("https://www.t-systems.com/ru/ru/");
    }

    @Test
    public void classGetAttributeTest() {

        WebElement searchBox = driver.findElement(By.className("glyphicon-search"));

        System.out.println("Class of the search box is: "
                + searchBox.getAttribute("class"));
    }

    @Test
    public void sendKeysTest() {

        WebElement searchBox = driver.findElement(By.xpath("/html/body/section[1]/div/div/div[1]/div/div[2]/ul/li[4]/a/span"));
        searchBox.click();
        searchBox = driver.findElement(By.xpath("/html/body/section[1]/div/div/div[1]/div/div[2]/ul/li[4]/ul/li/form/input"));
        searchBox.clear();
        searchBox.sendKeys("интеллект");
        searchBox.submit();

        assertThat(driver.getPageSource())
                .isEqualTo("по вашему запросу ничего не найдено");
    }


    @Test
    public void cssValueTest() {

        WebElement searchBox = driver.findElement(By.linkText("Облака"));
        searchBox.click();
        WebElement searchBox2 = driver.findElement(By.cssSelector(".t4-col-md-offset-r2 > h1:nth-child(2)"));
        System.out.println("Font of the text is: "
                + searchBox2.getCssValue("font-family"));
    }

    @Test
    public void elementLocationAndSizeTest() {

        WebElement searchBox = driver.findElement(By.className("layoutOption-twitter-icon"));

        System.out.println("Location of the box is: "
                + searchBox.getLocation());

        System.out.println("Size of the box is: "
                + searchBox.getSize());
    }

    @Test
    public void byTagNameLocatorTest() {

        // get all links from the Home page
        List<WebElement> links = driver.findElements(By.tagName("strong"));

        System.out.println("Found strong tags:" + links.size());

        // print links which have text using Java 8 Streams API
        links.stream()
                .filter(elem -> elem.getText().length() > 0)
                .forEach(elem -> System.out.println(elem.getText()));
    }

    @Test
    public void elementGetTextTest() {

        WebElement searchBox = driver.findElement(By.linkText("English"));
        searchBox.click();

        WebElement siteNotice = driver.findElement(By.xpath("/html/body/section[4]/div/div/div/div[2]/div/div[2]/div/div/div[1]"));

        System.out.println("Complete text is: "
                + siteNotice.getText());
    }

    @Test
    public void elementGetTagNameTest() {

        WebElement webElement = driver.findElement(By.className("countryList"));

        System.out.println("Html tag of this element is: "
                + webElement.getTagName());
    }

    @Test
    public void elementStateTest() {

        WebElement searchLink = driver.findElement(By.partialLinkText("Лицензии"));

        System.out.println("Licenses are displayed: "
                + searchLink.isDisplayed());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}