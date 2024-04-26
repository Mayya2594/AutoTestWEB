package Seminar_2_HW;

import Seminar_2_HW.elements.GroupTableRow;
import Seminar_2_HW.elements.StudentTableRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPage {

    private final WebDriverWait wait;

    @FindBy(css = "nav li.mdc-menu-surface--anchor a")
    private WebElement usernameLinkInNavBar;

    @FindBy(id = "create-btn")
    private WebElement createGroupButton;
    @FindBy(xpath = "//form//span[contains(text(), 'Group name')]/following-sibling::input")
    private WebElement groupNameField;
    @FindBy(css = "form div.submit button")
    private WebElement submitButtonOnModalWindow;
    @FindBy(xpath = "//span[text()='Creating Study Group']" +
            "//ancestor::div[contains(@class, 'form-modal-header')]//button")
    private WebElement closeCreateGroupIcon;

    @FindBy(css = "div#generateStudentsForm-content input")
    private WebElement createStudentsFormInput;
    @FindBy(css = "div#generateStudentsForm-content div.submit button")
    private WebElement saveCreateStudentsForm;
    @FindBy(xpath = "//h2[@id='generateStudentsForm-title']/../button")
    private WebElement closeCreateStudentsFormIcon;

    @FindBy(xpath = "//table[@aria-label='Tutors list']/tbody/tr")
    private List<WebElement> rowsInGroupTable;
    @FindBy(xpath = "//table[@aria-label='User list']/tbody/tr")
    private List<WebElement> rowsInStudentTable;

    public MainPage(WebDriver driver, WebDriverWait wait) {
        PageFactory.initElements(driver, this);
        this.wait = wait;
    }

    public WebElement waitAndGetGroupTitleByText(String title) {
        String xpath = String.format("//table[@aria-label='Tutors list']/tbody//td[text()='%s']", title);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public void createGroup(String groupName) {
        wait.until(ExpectedConditions.visibilityOf(createGroupButton)).click();
        wait.until(ExpectedConditions.visibilityOf(groupNameField)).sendKeys(groupName);
        submitButtonOnModalWindow.click();
        waitAndGetGroupTitleByText(groupName);
    }

    public void closeCreateGroupModalWindow() {
        closeCreateGroupIcon.click();
        wait.until(ExpectedConditions.invisibilityOf(closeCreateGroupIcon));
    }

    public void typeAmountOfStudentsInCreateStudentsForm(int amount) {
        wait.until(ExpectedConditions.visibilityOf(createStudentsFormInput))
                .sendKeys(String.valueOf(amount));
    }

    public void clickSaveButtonOnCreateStudentsForm() {
        wait.until(ExpectedConditions.visibilityOf(saveCreateStudentsForm)).click();
    }

    public void closeCreateStudentsModalWindow() {
        closeCreateStudentsFormIcon.click();
        wait.until(ExpectedConditions.invisibilityOf(closeCreateStudentsFormIcon));
    }

    public String getUsernameLabelText() {
        return wait.until(ExpectedConditions.visibilityOf(usernameLinkInNavBar))
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
        return rowsInGroupTable.stream()
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
        wait.until(ExpectedConditions.visibilityOfAllElements(rowsInStudentTable));
        return rowsInStudentTable.stream()
                .map(StudentTableRow::new)
                .toList().get(index).getName();
    }

    private StudentTableRow getStudentRowByName(String name) {
        wait.until(ExpectedConditions.visibilityOfAllElements(rowsInStudentTable));
        return rowsInStudentTable.stream()
                .map(StudentTableRow::new)
                .filter(row -> row.getName().equals(name))
                .findFirst().orElseThrow();
    }
}
