package Seminar_3_HW;

import Seminar_3_HW.elements.GroupTableRow;
import Seminar_3_HW.elements.StudentTableRow;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private SelenideElement usernameLinkInNavBar = $("nav li.mdc-menu-surface--anchor a");

    private SelenideElement createGroupButton = $(By.id("create-btn"));
    private SelenideElement groupNameField = $x("//form//span[contains(text(), 'Group name')]/following-sibling::input");
    private SelenideElement submitButtonOnModalWindow = $("form div.submit button");
    private SelenideElement closeCreateGroupIcon = $x("//span[text()='Creating Study Group']" +
            "//ancestor::div[contains(@class, 'form-modal-header')]//button");

    private SelenideElement createStudentsFormInput = $("div#generateStudentsForm-content input");
    private SelenideElement saveCreateStudentsForm = $("div#generateStudentsForm-content div.submit button");
    private SelenideElement closeCreateStudentsFormIcon = $x("//h2[@id='generateStudentsForm-title']/../button");

//    @FindBy(xpath = "//table[@aria-label='Tutors list']/tbody/tr")
    private ElementsCollection rowsInGroupTable = $$x("//table[@aria-label='Tutors list']/tbody/tr");
    @FindBy(xpath = "//table[@aria-label='User list']/tbody/tr")
    private ElementsCollection rowsInStudentTable;

    private SelenideElement profileButton = $x("//span[text()='Profile']");

//    public MainPage(WebDriver driver, WebDriverWait wait) {
//        PageFactory.initElements(driver, this);
//        this.wait = wait;
//    }

    public void clickProfileButton() {
        usernameLinkInNavBar.should(Condition.visible).click();
        profileButton.should(Condition.visible).click();
    }

    public SelenideElement waitAndGetGroupTitleByText(String title) {
        String xpath = String.format("//table[@aria-label='Tutors list']/tbody//td[text()='%s']", title);
        return $x(xpath).should(Condition.visible);
    }

    public void createGroup(String groupName) {
        createGroupButton.should(Condition.visible).click();
        groupNameField.should(Condition.visible).sendKeys(groupName);
        submitButtonOnModalWindow.click();
        waitAndGetGroupTitleByText(groupName);
    }

    public void closeCreateGroupModalWindow() {
        closeCreateGroupIcon.click();
        closeCreateGroupIcon.should(Condition.disappear);
    }

    public void typeAmountOfStudentsInCreateStudentsForm(int amount) {
        createStudentsFormInput.should(Condition.visible)
                .sendKeys(String.valueOf(amount));
    }

    public void clickSaveButtonOnCreateStudentsForm() {
        saveCreateStudentsForm.should(Condition.visible).click();
    }

    public void closeCreateStudentsModalWindow() {
        closeCreateStudentsFormIcon.click();
        closeCreateStudentsFormIcon.should(Condition.disappear);
    }

    public String getUsernameLabelText() {
        return usernameLinkInNavBar.should(Condition.visible)
                .getText().replace("\n", " ");
    }

    public void clickTrashIconOnGroupWithTitle(String title) {
        getGroupRowByTitle(title).clickTrashIcon();
    }

    public void clickRestoreFromTrashIconOnGroupWithTitle(String title) {
        getGroupRowByTitle(title).clickRestoreFromTrashIcon();
    }

    public void clickAddStudentsIconOnGroupWithTitle(String title) {
        getGroupRowByTitle(title).clickAddStudentIcon();
    }

    public void clickZoomInIconOnGroupWithTitle(String title) {
        getGroupRowByTitle(title).clickZoomInIcon();
    }

    public String getStatusOfGroupWithTitle(String title) {
        return getGroupRowByTitle(title).getStatus();
    }

    public void waitStudentsCount(String groupTestName, int studentsCount) {
        getGroupRowByTitle(groupTestName).waitStudentsCount(studentsCount);
    }

    private GroupTableRow getGroupRowByTitle(String title) {
        return rowsInGroupTable.asDynamicIterable()
                .stream()
                .map(GroupTableRow::new)
                .filter(row -> row.getTitle().equals(title))
                .findFirst().orElseThrow();
    }

    public void clickTrashIconOnStudentWithName(String name) {
        getStudentRowByName(name).clickTrashIcon();
    }

    public void clickRestoreFromTrashIconOnStudentWithName(String name) {
        getStudentRowByName(name).clickRestoreFromTrashIcon();
    }

    public String getStatusOfStudentWithName(String name) {
        return getStudentRowByName(name).getStatus();
    }

    public String getStudentNameByIndex(int index) {
//        wait.until(ExpectedConditions.visibilityOfAllElements(rowsInStudentTable));
        return rowsInStudentTable.should(CollectionCondition.sizeGreaterThan(0))
                .asDynamicIterable()
                .stream()
                .map(StudentTableRow::new)
                .toList().get(index).getName();
    }

    private StudentTableRow getStudentRowByName(String name) {
//        wait.until(ExpectedConditions.visibilityOfAllElements(rowsInStudentTable));
        return rowsInStudentTable.should(CollectionCondition.sizeGreaterThan(0))
                .asDynamicIterable()
                .stream()
                .map(StudentTableRow::new)
                .filter(row -> row.getName().equals(name))
                .findFirst().orElseThrow();
    }
}
