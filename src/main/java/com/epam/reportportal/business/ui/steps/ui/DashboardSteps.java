package com.epam.reportportal.business.ui.steps.ui;

import com.epam.reportportal.business.ui.pages.DashboardsPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class DashboardSteps {

    private DashboardsPage dashboardsPage;

    public DashboardSteps(WebDriver driver) {
        dashboardsPage = new DashboardsPage(driver);
    }

    @Step("Select the current project")
    public DashboardSteps selectCurrentProject() {
        dashboardsPage.waitForPageLoaded()
                .selectCurrentProject();
        return this;
    }
}
