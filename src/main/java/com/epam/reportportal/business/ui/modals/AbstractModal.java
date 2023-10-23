package com.epam.reportportal.business.ui.modals;

import com.epam.reportportal.business.ui.pages.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class AbstractModal extends AbstractPage {

    public AbstractModal(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isModalOpened();
}
