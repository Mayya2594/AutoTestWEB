package Seminar_4_HW;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.io.IOException;
import java.util.TreeMap;

public class ProfilePageTest extends GBStandAbstractTest {

    @Test
    public void testNameInProfile() {
        checkLoginOtherUser();
        mainPage.clickProfileButton();
        ProfilePage profilePage = new ProfilePage(driver, wait);

        String additionalInfoName = profilePage.getAdditionalInfoNameText();
        String avatarName = profilePage.getAvatarNameText();
        Assertions.assertEquals(additionalInfoName, avatarName);
    }

    @Test
    void editBirthdateTest() throws InterruptedException {
        checkLoginOtherUser();
        mainPage.clickProfileButton();

        ProfilePage profilePage = new ProfilePage(driver, wait);
        String newBirthDate = "10.10.1994";
        profilePage.clickMoreOptionsButton();
        profilePage.setNewBirthDate(newBirthDate);
        profilePage.clickSaveButton();
        profilePage.clickCloseEditProfileButton();
        Thread.sleep(2000);
        String dateOfBirth = profilePage.getDateOfBirt();

        Assertions.assertEquals(dateOfBirth, newBirthDate);
    }
}
