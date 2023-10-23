package com.epam.reportportal.business.ui.steps;

import com.epam.reportportal.business.ui.modals.*;
import com.epam.reportportal.business.ui.pages.LaunchesPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class LaunchesSteps {

    private LaunchesPage launchesPage;
    private CompareLaunchesModal compareLaunchesModal;
    private DeleteLaunchesModal deleteLaunchesModal;
    private EditLaunchesModal editLaunchesModal;
    private MergeLaunchesModal mergeLaunchesModal;
    private MoveToDebugModal moveToDebugModal;

    public LaunchesSteps(WebDriver driver) {
        launchesPage = new LaunchesPage(driver);
        compareLaunchesModal = new CompareLaunchesModal(driver);
        deleteLaunchesModal = new DeleteLaunchesModal(driver);
        editLaunchesModal = new EditLaunchesModal(driver);
        mergeLaunchesModal = new MergeLaunchesModal(driver);
        moveToDebugModal = new MoveToDebugModal(driver);
    }

    @Step("Get defect type for Donut Chart with color '{color}'")
    public String getCurrentDefectType(String color) {
        return launchesPage.waitForPageLoaded()
                .hoverToDonutChart(color)
                .getDefectTypeText();
    }

    @Step("Select launches with index '{firstCheckboxIndex}' and '{secondCheckboxIndex}' and go to action '{actionName}'")
    public Object selectLaunchesAndGoToAction(String actionName, int firstCheckboxIndex, int secondCheckboxIndex) {
        return launchesPage.waitForPageLoaded()
                .selectLaunch(firstCheckboxIndex)
                .selectLaunch(secondCheckboxIndex)
                .openActionsMenu()
                .selectAction(actionName);
    }

    @Step("Verify that '{actionName}' modal is opened")
    public boolean isCurrentModalOpened(String actionName) {
        boolean isModalOpened = false;
        switch (actionName) {
            case "Edit" -> isModalOpened = editLaunchesModal.isModalOpened();
            case "Merge" -> isModalOpened = mergeLaunchesModal.isModalOpened();
            case "Compare" -> isModalOpened = compareLaunchesModal.isModalOpened();
            case "Move to debug" -> isModalOpened = moveToDebugModal.isModalOpened();
            case "Delete" -> isModalOpened = deleteLaunchesModal.isModalOpened();
        }
        return isModalOpened;
    }

    @Step("Get names of all launches on the modal")
    public Set<String> getLaunchesNames() {
        return compareLaunchesModal.getLaunchesNamesText();
    }
}