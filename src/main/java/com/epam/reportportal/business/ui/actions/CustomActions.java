package com.epam.reportportal.business.ui.actions;

import com.epam.reportportal.business.ui.element_decorators.*;
import io.qameta.allure.Step;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

@Data
@Log4j2
public class CustomActions {
    private WebDriver driver;

    public CustomActions(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click the button '{buttonName}'")
    public void clickButton(String buttonName) {
        new Button(driver, buttonName).click();
    }

    @Step("Fill in input field '{fieldName}' with text '{text}'")
    public void fillInputField(String fieldName, String text) {
        new Input(driver, fieldName).writeText(text);
    }

    @Step("Click on the link '{projectName}/{pageName}'")
    public void clickLink(String projectName, String pageName) {
        new Link(driver, projectName, pageName).click();
    }

    @Step("Click on the text header '{headerText}'")
    public void clickTextHeader(String headerText) {
        new TextHeader(driver, headerText).click();
    }

    @Step("Select the value {value}")
    public void selectValue(String value) {
        Selector selector = new Selector(driver);
        selector.click();
        selector.select(value);
    }

    public void waitTitle(String label) {
        new Title(driver).wait(label);
    }

    @Step("Click on the hint")
    public void clickHint() {
        new ErrorHint(driver).click();
    }

    @Step("Hover to the defects 'Donut chart' with color '{color}'")
    public void hoverToDonutChart(String color) {
        new DonutChart(driver, color).hover();
    }

    @Step("Mark the checkbox number '{index}'")
    public void markCheckbox(int index) {
        new Checkbox(driver).mark(index);
    }

    public boolean isTitleDisplayed(String label) {
        return new Title(driver).isDisplayed(label);
    }
}
