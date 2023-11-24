package com.epam.reportportal.business.ui.pages;

import org.openqa.selenium.WebDriver;

public class SideBar extends AbstractPage {

    public SideBar(WebDriver driver) {
        super(driver);
    }

    public void selectCurrentProject() {
        customActions.selectValue(getProperties(CURRENT_PROJECT_NAME_LABEL));
    }

    public void openPage(String pageName) {
        customActions.clickLink(getProperties(CURRENT_PROJECT_NAME_LABEL), pageName);
    }
}

