package com.epam.reportportal.business.ui.pages;

import org.openqa.selenium.WebDriver;

public class DashboardsPage extends SideBar {

    private static final String TITLE = "All Dashboards";
    public DashboardsPage(WebDriver driver) {
        super(driver);
    }

    public DashboardsPage waitForPageLoaded() {
        customActions.watTitle(TITLE);
        return this;
    }
}
