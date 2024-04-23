package Seminar_1_HW;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class GBAbstractTest {

    static WebDriver driver;
    static WebDriverWait wait;
    static String USERNAME = "GB202311d63244";
    static String PASSWORD = "3c81458174";

    @BeforeAll
    static void init() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); //открыть браузер на весь экран
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.get("https://test-stand.gb.ru/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    void quit() {
        driver.quit();
    }
}
