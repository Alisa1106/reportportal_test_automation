package com.epam.reportportal.business.ui.modals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MoveToDebugModal extends AbstractModal {

    private By TITLE = By.xpath("//span[contains(@class,'modal-title') and text()='Move to debug']");

    public MoveToDebugModal(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isModalOpened() {
        return driver.findElement(TITLE).isDisplayed();
    }
}
