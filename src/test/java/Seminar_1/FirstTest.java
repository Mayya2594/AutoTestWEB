package Seminar_1;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class FirstTest {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static String USERNAME;
    private static String PASSWORD;

    @BeforeAll
    static void init() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); //открыть браузер на весь экран
        options.addArguments("--incognito"); //открыть браузер в режиме инкогнито
        driver = new ChromeDriver(options);
        driver.get("https://test-stand.gb.ru/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        USERNAME = System.getProperty("geekbrains_username", System.getenv("geekbrains_username"));
        PASSWORD = System.getProperty("geekbrains_password", System.getenv("geekbrains_password"));
    }

    @Test
    @Disabled
    void test() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); //открыть браузер на весь экран
        WebDriver driver = new ChromeDriver(options);
//        options.addArguments("--headless");//выполнить действия в браузере без явного открытия самого браузера
        driver.get("https://test-stand.gb.ru/login");
        Thread.sleep(5000l);
        driver.quit();
    }

    @Test
    void testAuthorization() throws InterruptedException {
//        WebElement login = driver.findElement(By.xpath("//*[@type='text']"));
//        WebElement password = driver.findElement(By.xpath("//*[@type='password']"));
        WebElement login = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("form#login input[type='text']")));
        WebElement password = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("form#login input[type='password']")));
        WebElement loginButton = driver.findElement(By.xpath("//*[@class='button mdc-button mdc-button--raised mdc-ripple-upgraded']"));
        login.sendKeys(USERNAME);
        password.sendKeys(PASSWORD);
        loginButton.click();
        Thread.sleep(5000l);

        List<WebElement> resultList = driver.findElements(By.partialLinkText("Hello, " + USERNAME));

        Assertions.assertEquals(1, resultList.size());

//        WebElement usernameLink = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(USERNAME)));
//        String actualUsername = usernameLink.getText().replace("\n", " ").trim();
//
//        Assertions.assertEquals(String.format("Hello, %s", USERNAME), actualUsername);
    }

    @AfterEach
    void quit() {
        driver.quit();
    }
}
