package com.epam.reportportal.business.ui.pages;

import com.epam.reportportal.business.ui.modals.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.List;
import java.util.stream.IntStream;

public class LaunchesPage extends SideBar {

    private static final String TITLE = "All launches";
    private final By LAUNCH_INDEX = By.xpath("//td[contains(@class,'launchSuiteGrid__name-col--')]" +
            "//span[contains(@class,'itemInfo__number')]");
    private final By DEFECT_TYPE_TOOLTIP = By.xpath("//div[contains(@class,'defectTypeTooltip__name')]");
    private final By ACTIONS_BUTTON = By.xpath("//div[contains(@class,'actionPanel__action-button')]" +
            "//div[contains(@class,'ghostMenuButton__ghost-menu')]");

    public LaunchesPage(WebDriver driver) {
        super(driver);
    }

    public LaunchesPage waitForPageLoaded() {
        customActions.waitTitle(TITLE);
        waitForSpinnerIsDisappeared();
        return this;
    }

    public LaunchesPage sortLaunches(String sortingTypeName) {
        customActions.clickTextHeader(sortingTypeName);
        waitForSpinnerIsDisappeared();
        return this;
    }

    public boolean isLaunchesSortedInDescendingOrder() {
        List<Integer> launchIndexes = driver.findElements(LAUNCH_INDEX).stream()
                .map(webElement -> Integer.valueOf(webElement.getText().substring(1)))
                .toList();
        int size = launchIndexes.size();
        return IntStream.range(0, size)
                .allMatch(i -> IntStream.range(i + 1, size)
                        .allMatch(j -> launchIndexes.get(i) > launchIndexes.get(j)));
    }

    public LaunchesPage hoverToDonutChart(String color) {
        customActions.hoverToDonutChart(color);
        return this;
    }

    public String getDefectTypeText() {
        waiters.waitForElementLocated(DEFECT_TYPE_TOOLTIP, Duration.ofSeconds(5));
        return driver.findElement(DEFECT_TYPE_TOOLTIP).getText();
    }

    public LaunchesPage selectLaunch(int index) {
        customActions.markCheckbox(index);
        return this;
    }

    public LaunchesPage openActionsMenu() {
        driver.findElement(ACTIONS_BUTTON).click();
        return this;
    }

    public Object selectAction(String actionName) {
        customActions.clickButton(actionName);
        Object pageObject = new Object();
        switch (actionName) {
            case "Edit" -> pageObject = new EditLaunchesModal(driver);
            case "Merge" -> pageObject = new MergeLaunchesModal(driver);
            case "Compare" -> pageObject = new CompareLaunchesModal(driver);
            case "Move to debug" -> pageObject = new MoveToDebugModal(driver);
            case "Delete" -> pageObject = new DeleteLaunchesModal(driver);
        }
        return pageObject;
    }
}
