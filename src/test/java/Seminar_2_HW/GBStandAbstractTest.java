package Seminar_2_HW;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GBStandAbstractTest {

    WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;
    MainPage mainPage;

    static String USERNAME = "MaximDav";
    static String PASSWORD = "a188da4213";

    @BeforeEach
    public void setupTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); //открыть браузер на весь экран
        options.addArguments("--incognito"); //открыть браузер в режиме инкогнито
        driver = new ChromeDriver(options);
        driver.get("https://test-stand.gb.ru/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginPage = new LoginPage(driver, wait);
    }

    public void checkLogin() {
        loginPage.login(USERNAME, PASSWORD);
        mainPage = new MainPage(driver, wait);
        assertTrue(mainPage.getUsernameLabelText().contains(USERNAME));
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }
}
