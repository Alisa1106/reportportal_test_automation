package com.epam.reportportal.business.ui.modals;

import org.openqa.selenium.WebDriver;

public class DeleteLaunchesModal extends AbstractModal {

    private static final String TITLE = "Delete launches";

    public DeleteLaunchesModal(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isModalOpened() {
        return customActions.isTitleDisplayed(TITLE);
    }

    @Override
    public boolean isModalClosed() {
        return customActions.isTitleNotDisplayed(TITLE);
    }
}
