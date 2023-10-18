package com.epam.reportportal.business.ui.steps;

import com.epam.reportportal.business.ui.pages.SideBar;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class CommonSteps {

    private SideBar sideBar;

    public CommonSteps(WebDriver driver) {
        sideBar = new SideBar(driver);
    }

    @Step("Open the page '{pageName}'")
    public CommonSteps openPage(String pageName) {
        sideBar.openPage(pageName);
        return this;
    }
}
//lll123