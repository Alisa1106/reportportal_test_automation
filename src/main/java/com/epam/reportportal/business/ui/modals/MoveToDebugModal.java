package com.epam.reportportal.business.ui.modals;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.epam.reportportal.business.ui.constants.TimeoutCons.SHORT_TIMEOUT_IN_SECONDS;

public class MoveToDebugModal extends AbstractModal {

    private final By TITLE = By.xpath("//span[contains(@class,'modal-title') and text()='Move to debug']");

    public MoveToDebugModal(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isModalOpened() {
        return driver.findElement(TITLE).isDisplayed();
    }

    @Override
    public boolean isModalClosed() {
        try {
            waiters.waitForElementIsDisappeared(TITLE, Duration.ofSeconds(SHORT_TIMEOUT_IN_SECONDS));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
