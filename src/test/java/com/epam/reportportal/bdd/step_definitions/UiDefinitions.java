package com.epam.reportportal.bdd.step_definitions;

import com.epam.reportportal.business.factories.UserFactory;
import com.epam.reportportal.business.models.User;
import com.epam.reportportal.business.ui.pages.LaunchesPage;
import com.epam.reportportal.business.ui.steps.ui.CommonSteps;
import com.epam.reportportal.business.ui.steps.ui.DashboardSteps;
import com.epam.reportportal.business.ui.steps.ui.LaunchesSteps;
import com.epam.reportportal.business.ui.steps.ui.LoginSteps;
import com.epam.reportportal.core.ui.drivers.DriverContainer;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class UiDefinitions {
    WebDriver driver;
    private User defUser = UserFactory.defaultUser();
    LaunchesSteps launchesSteps;
    LaunchesPage launchesPage;

    private void init() {
        driver = DriverContainer.getDriver();
    }

    @Given("^I Log in as the Default User$")
    public void logInAsDefaultUser() {
        init();
        new LoginSteps(driver).signIn(defUser);
    }

    @When("^I select current project$")
    public void selectCurrentProject() {
        new DashboardSteps(driver).selectCurrentProject();
    }

    @And("^I open ([^\"]*) page$")
    public void openPage(String pageName) {
        new CommonSteps(driver).openPage(pageName);
    }

    @And("^I select launches checkboxes (\\d+) and (\\d+) and select (Edit|Merge|Compare|Move to debug|Delete)$")
    public void selectLaunchesCheckboxesAndSelectAction(int firstCheckboxIndex, int secondCheckboxIndex, String actionName) {
        launchesSteps = new LaunchesSteps(driver);
        launchesSteps.selectLaunchesAndGoToAction(actionName, firstCheckboxIndex, secondCheckboxIndex);
    }

    @Then("^I see (Edit|Merge|Compare|Move to debug|Delete) modal is opened$")
    public void modalIsOpened(String modalName) {
        Assertions.assertTrue(launchesSteps.isCurrentModalOpened(modalName),
                String.format("'%s' modal is not opened!", modalName));
    }

    @And("^I see Launches names number is equal to number of selected launches: (\\d+)$")
    public void launchesNamesNumberIsEqualToNumberOfSelectedLaunches(int numberOfLaunches) {
        Assertions.assertEquals(launchesSteps.getLaunchesNames().size(), numberOfLaunches,
                "Launches names number is not equal to numbers of selected launches!");
    }

    @Given("^I see color ([^\"]*) corresponds defect type (.*)$")
    public void colorCorrespondsDefectType(String color, String defectType) {
        Assertions.assertEquals(new LaunchesSteps(driver).getCurrentDefectType(color), defectType,
                String.format("Donut chart with color '%s' is not correspond to type '%s'!", color, defectType));
    }

    @Given("^I see launches are sorted by most recent bu default$")
    public void launchesAreSortedByMostRecentBuDefault() {
        launchesPage = new LaunchesPage(driver);
        launchesPage.waitForPageLoaded();
        Assertions.assertTrue(launchesPage.isLaunchesSortedInDescendingOrder(),
                "Launches is not sorted by most recent by default!");
    }

    @Given("^I sort Launches by (.*)$")
    public void sortLaunchesByParameter(String parameter) {
        launchesPage.sortLaunches(parameter);
    }

    @Then("I see Launches are sorted in ascending order")
    public void launchesAreSortedInAscendingOrder() {
        Assertions.assertFalse(launchesPage.isLaunchesSortedInDescendingOrder(),
                "User is NOT able to resort launches!");
    }

    @But("^I am able to close (Edit|Merge|Compare|Move to debug|Delete) modal by Cancel button$")
    public void modalIsClosedAfterClickingCancelButton(String modalName) {
        launchesSteps.closeModalByCancelButton(modalName);
        Assertions.assertTrue(launchesSteps.isCurrentModalClosed(modalName));
    }
}
