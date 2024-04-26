package Seminar_3_HW;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private SelenideElement usernameField = $("form#login input[type='text']");

    private SelenideElement passwordField = $("form#login input[type='password']");

    private SelenideElement loginButton = $("form#login button");

    private SelenideElement errorBlock = $("div.error-block");


    public void login(String USERNAME, String PASSWORD) {
        typeUsernameInField(USERNAME);
        typePasswordInField(PASSWORD);
        clickLoginButton();
    }

    public void typeUsernameInField(String USERNAME) {
        usernameField.should(Condition.visible).setValue(USERNAME);
    }

    public void typePasswordInField(String PASSWORD) {
        passwordField.should(Condition.visible).setValue(PASSWORD);
    }

    public void clickLoginButton() {
        loginButton.should(Condition.visible).click();
    }

    public String getErrorBlockText() {
        return errorBlock.should(Condition.visible)
                .getText().replace("\n", " ");
    }
}
