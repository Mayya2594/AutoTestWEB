package Seminar_1_HW;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class CreateDummyTest extends GBAbstractTest {

    @Test
    void testCreateDummy() throws InterruptedException {
        WebElement login = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("form#login input[type='text']")));
        WebElement password = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("form#login input[type='password']")));
        WebElement loginButton = driver.findElement(
                By.xpath("//*[@class='button mdc-button mdc-button--raised mdc-ripple-upgraded']"));
        login.sendKeys(USERNAME);
        password.sendKeys(PASSWORD);
        loginButton.click();
        WebElement createDummyButton = driver.findElement(By.xpath("//*[@id='create-btn']"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
                "#app > main > div > div > div.mdc-data-table > div.mdc-data-table__table-container > table > tbody > tr:nth-child(10)")));
        createDummyButton.click();
        WebElement firstName = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("#upsert-item > div:nth-child(1) > label > input")));
        WebElement loginDummy = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("#upsert-item > div:nth-child(5) > label > input")));
        long currentTimeMillis = System.currentTimeMillis();
        firstName.sendKeys("Dummy_" + currentTimeMillis);
        loginDummy.sendKeys("Dummy_" + currentTimeMillis);
        WebElement saveDummyButton = driver.findElement(
                By.xpath("//*[@class='button mdc-button mdc-button--raised mdc-ripple-upgraded']"));
        saveDummyButton.click();
        Thread.sleep(5000l);

        WebElement resultList = driver.findElement(By.xpath("//*[@class='mdc-data-table__cell']"));
        Assertions.assertEquals("Dummy_" + currentTimeMillis, resultList.getText());

        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFile(screenshot, new File("src/test/resources/screenshot_" + currentTimeMillis + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
