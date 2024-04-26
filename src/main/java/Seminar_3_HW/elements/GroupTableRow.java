package Seminar_3_HW.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class GroupTableRow {

    private final SelenideElement root;

    public GroupTableRow(SelenideElement root) {
        this.root = root;
    }

    public String getTitle() {
        return root.$x("./td[2]").text();
    }

    public String getStatus() {
        return root.$x("./td[3]").text();
    }

    public void clickTrashIcon() {
        root.$x("./td/button[text()='delete']").click();
        root.$x("./td/button[text()='restore_from_trash']").should(Condition.visible);
    }

    public void clickRestoreFromTrashIcon() {
        root.$x("./td/button[text()='restore_from_trash']").click();
        root.$x("./td/button[text()='delete']").should(Condition.visible);
//        waitUntil(root -> root.findElement(By.xpath("./td/button[text()='delete']")));
    }

    public void clickAddStudentIcon() {
        root.findElement(By.cssSelector("td button i.material-icons")).click();
    }

    public void clickZoomInIcon() {
        root.findElement(By.xpath(".//td/button[contains(., 'zoom_in')]")).click();
    }

    public void waitStudentsCount(int expectedCount) {
        root.$x("./td[4]//span[text()='%s']".formatted(expectedCount)).should(Condition.visible);
//        waitUntil(root ->
//                root.findElement(By.xpath("./td[4]//span[text()='%s']".formatted(expectedCount))));
    }

//    private void waitUntil(Function<SelenideElement, SelenideElement> until) {
//        new FluentWait<>(root)
//                .withTimeout(Duration.ofSeconds(10))
//                .pollingEvery(Duration.ofSeconds(1))
//                .ignoring(NoSuchElementException.class)
//                .until(until);
//    }
}
