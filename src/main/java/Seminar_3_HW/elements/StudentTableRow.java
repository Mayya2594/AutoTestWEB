package Seminar_3_HW.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class StudentTableRow {

    private final SelenideElement root;

    public StudentTableRow(SelenideElement root) {
        this.root = root;
    }

    public String getName() {
        return root.$x("./td[2]").text();
    }

    public String getStatus() {
        return root.$x("./td[4]").text();
    }

    public void clickTrashIcon() {
        root.findElement(By.xpath("./td/button[text()='delete']")).click();
        root.$x("./td/button[text()='restore_from_trash']").should(Condition.visible);
//        waitUntil(root -> root.findElement(By.xpath("./td/button[text()='restore_from_trash']")));
    }

    public void clickRestoreFromTrashIcon() {
        root.findElement(By.xpath("./td/button[text()='restore_from_trash']")).click();
        root.$x("./td/button[text()='delete']").should(Condition.visible);
//        waitUntil(root -> root.findElement(By.xpath("./td/button[text()='delete']")));
    }

//    private void waitUntil(Function<WebElement, WebElement> until) {
//        new FluentWait<>(root)
//                .withTimeout(Duration.ofSeconds(10))
//                .pollingEvery(Duration.ofSeconds(1))
//                .ignoring(NoSuchElementException.class)
//                .until(until);
//    }
}
