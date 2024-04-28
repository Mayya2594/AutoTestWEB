package Seminar_2_HW;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GBStandTest extends GBStandAbstractTest{

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

    @Test
    public void testLoginWithEmptyFields() {
        loginPage.clickLoginButton();
        assertEquals("401 Invalid credentials.", loginPage.getErrorBlockText());
    }

    @Test
    public void testAddingGroupOnMainPage() {
        checkLogin();
        String groupTestName = "New Test Group " + System.currentTimeMillis();
        mainPage.createGroup(groupTestName);

        assertTrue(mainPage.waitAndGetGroupTitleByText(groupTestName).isDisplayed());
    }

    @Test
    void testArchiveGroupOnMainPage() {
        checkLogin();
        String groupTestName = "New Test Group " + System.currentTimeMillis();
        mainPage.createGroup(groupTestName);
        mainPage.closeCreateGroupModalWindow();

        assertEquals("active", mainPage.getStatusOfGroupWithTitle(groupTestName));
        mainPage.clickTrashIconOnGroupWithTitle(groupTestName);
        assertEquals("inactive", mainPage.getStatusOfGroupWithTitle(groupTestName));
        mainPage.clickRestoreFromTrashIconOnGroupWithTitle(groupTestName);
        assertEquals("active", mainPage.getStatusOfGroupWithTitle(groupTestName));
    }

    @Test
    void testBlockingStudentInTableOnMainPage() {
        checkLogin();
        String groupTestName = "New Test Group " + System.currentTimeMillis();

        mainPage.createGroup(groupTestName);
        mainPage.closeCreateGroupModalWindow();
        int studentsCount = 3;
        mainPage.clickAddStudentsIconOnGroupWithTitle(groupTestName);
        mainPage.typeAmountOfStudentsInCreateStudentsForm(studentsCount);
        mainPage.clickSaveButtonOnCreateStudentsForm();
        mainPage.closeCreateStudentsModalWindow();
        mainPage.waitStudentsCount(groupTestName, studentsCount);
        mainPage.clickZoomInIconOnGroupWithTitle(groupTestName);

        String firstGeneratedStudentName = mainPage.getStudentNameByIndex(0);
        assertEquals("active", mainPage.getStatusOfStudentWithName(firstGeneratedStudentName));
        mainPage.clickTrashIconOnStudentWithName(firstGeneratedStudentName);
        assertEquals("block", mainPage.getStatusOfStudentWithName(firstGeneratedStudentName));
        mainPage.clickRestoreFromTrashIconOnStudentWithName(firstGeneratedStudentName);
        assertEquals("active", mainPage.getStatusOfStudentWithName(firstGeneratedStudentName));
    }

}
