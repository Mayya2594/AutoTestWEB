package Seminar_4_HW;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Date;

public class ProfilePage {

    private final WebDriverWait wait;

    @FindBy(xpath = "//*[@class='profile-info svelte-vyyzan']/div[1]/div[@class='content svelte-vyyzan']")
    private WebElement additionalInfoName;

    @FindBy(xpath = "//*[@class='mdc-typography--headline6 svelte-vyyzan']")
    private WebElement avatarName;

    @FindBy(xpath = "//*[@title='More options']")
    private WebElement moreOptionsButton;

    @FindBy(id = "update-item")
    private WebElement editProfileForm;

    @FindBy(xpath = "//input[@type='date']")
    private WebElement birthDateField;

    @FindBy(xpath = "//*[@data-mdc-dialog-action='close']")
    private WebElement closeEditProfileButton;

    @FindBy(xpath = "//*[@type='submit']")
    private WebElement saveButton;

    @FindBy(xpath = "//*[@class='profile-info svelte-vyyzan']/div[2]/div[@class='content svelte-vyyzan']")
    private WebElement dateOfBirthField;

    public ProfilePage(WebDriver driver, WebDriverWait wait) {
        PageFactory.initElements(driver, this);
        this.wait = wait;
    }

    public String getAdditionalInfoNameText() {
        return wait.until(ExpectedConditions.visibilityOf(additionalInfoName)).getText();
    }

    public String getAvatarNameText() {
        return wait.until(ExpectedConditions.visibilityOf(avatarName)).getText();
    }

    public void clickMoreOptionsButton() {
        wait.until(ExpectedConditions.visibilityOf(moreOptionsButton)).click();
    }

    public void setNewBirthDate(String date) {
        wait.until(ExpectedConditions.visibilityOf(birthDateField)).sendKeys(date);
    }

    public void clickSaveButton() {
        wait.until(ExpectedConditions.visibilityOf(saveButton)).click();
    }

    public void clickCloseEditProfileButton() {
        wait.until(ExpectedConditions.visibilityOf(closeEditProfileButton)).click();
    }

    public WebElement getCloseEditProfileButton() {
        return closeEditProfileButton;
    }

    public String getDateOfBirt() {
        return wait.until(ExpectedConditions.visibilityOf(dateOfBirthField)).getText();
    }

}
