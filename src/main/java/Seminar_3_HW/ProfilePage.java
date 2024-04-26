package Seminar_3_HW;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage {

    private SelenideElement additionalInfoName = $x("//*[@class='profile-info svelte-vyyzan']/div[1]/div[@class='content svelte-vyyzan']");

    private SelenideElement avatarName = $x("//*[@class='mdc-typography--headline6 svelte-vyyzan']");

    public String getAdditionalInfoNameText() {
        return additionalInfoName.should(Condition.visible).text();
    }

    public String getAvatarNameText() {
        return avatarName.should(Condition.visible).text();
    }

}
