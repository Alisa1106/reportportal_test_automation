package com.epam.reportportal.business.ui.modals;

import org.openqa.selenium.WebDriver;

public class EditLaunchesModal extends AbstractModal {

    private static final String TITLE = "Edit launches";

    public EditLaunchesModal(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isModalOpened() {
        return customActions.isTitleDisplayed(TITLE);
    }
}
