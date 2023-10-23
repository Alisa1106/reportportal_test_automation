package com.epam.reportportal.business.ui.modals;

import org.openqa.selenium.WebDriver;

public class MergeLaunchesModal extends AbstractModal {

    private static final String TITLE = "Merge launches";

    public MergeLaunchesModal(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isModalOpened() {
        return customActions.isTitleDisplayed(TITLE);
    }
}
