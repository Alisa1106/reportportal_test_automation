package com.epam.reportportal.business.ui.modals;

import com.epam.reportportal.business.ui.pages.AbstractPage;
import com.epam.reportportal.business.ui.pages.LaunchesPage;
import org.openqa.selenium.WebDriver;

public abstract class AbstractModal extends AbstractPage {

    public AbstractModal(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isModalOpened();

    public abstract boolean isModalClosed();

    public LaunchesPage closeModalByCancelButton() {
        customActions.clickButton(getProperties(CANCEL_BUTTON_LABEL));
        return new LaunchesPage(driver);
    }
}
