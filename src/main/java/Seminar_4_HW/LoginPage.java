package Seminar_4_HW;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private final WebDriverWait wait;

    @FindBy(css = "form#login input[type='text']")
    private WebElement usernameField;

    @FindBy(css = "form#login input[type='password']")
    private WebElement passwordField;

    @FindBy(css = "form#login button")
    private WebElement loginButton;

    @FindBy(css = "div.error-block")
    private WebElement errorBlock;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        PageFactory.initElements(driver, this);
        this.wait = wait;
    }

    public void login(String USERNAME, String PASSWORD) {
        typeUsernameInField(USERNAME);
        typePasswordInField(PASSWORD);
        clickLoginButton();
    }

    public void typeUsernameInField(String USERNAME) {
        wait.until(ExpectedConditions.visibilityOf(usernameField)).sendKeys(USERNAME);
    }

    public void typePasswordInField(String PASSWORD) {
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(PASSWORD);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.visibilityOf(loginButton)).click();
    }

    public String getErrorBlockText() {
        return wait.until(ExpectedConditions.visibilityOf(errorBlock))
                .getText().replace("\n", " ");
    }
}
