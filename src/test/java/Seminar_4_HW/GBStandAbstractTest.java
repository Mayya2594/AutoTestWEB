package Seminar_4_HW;

import Seminar_4_HW.LoginPage;
import Seminar_4_HW.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class GBStandAbstractTest {

    RemoteWebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;
    MainPage mainPage;

    static String USERNAME = "MaximDav";
    static String PASSWORD = "a188da4213";

    static String USERNAME_2 = "GB202311d63244";
    static String PASSWORD_2 = "3c81458174";

    @BeforeEach
    public void setupTest() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.setCapability("selenoid:options", new HashMap<String, Object>() {{
            put("enableVNC", true);
            put("enableLog", true);
        }});
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--start-maximized"); //открыть браузер на весь экран
//        options.addArguments("--incognito"); //открыть браузер в режиме инкогнито
//        driver = new ChromeDriver(options);
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

    public void checkLoginOtherUser() {
        loginPage.login(USERNAME_2, PASSWORD_2);
        mainPage = new MainPage(driver, wait);
        assertTrue(mainPage.getUsernameLabelText().contains(USERNAME_2));
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }
}
