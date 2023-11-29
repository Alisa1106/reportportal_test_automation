package com.epam.reportportal.business.ui.element_decorators;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.epam.reportportal.business.ui.constants.TimeoutCons.LONG_TIMEOUT_IN_SECONDS;

public class Title extends AbstractElement {

    private static final String TITLE_XPATH = "(//span[@title='%s']) | (//*[text()='%s'])";

    public Title(WebDriver driver) {
        super(driver);
    }

    public By getBy(String label) {
        return By.xpath(String.format(TITLE_XPATH, label, label));
    }

    public void waitForVisible(String label) {
        waiters.waitForElementLocated(getBy(label), Duration.ofSeconds(LONG_TIMEOUT_IN_SECONDS));
    }

    public void waitForInvisible(String label) {
        waiters.waitForElementIsDisappeared(getBy(label), Duration.ofSeconds(LONG_TIMEOUT_IN_SECONDS));
    }

    public boolean isDisplayed(String label) {
        waitForVisible(label);
        return driver.findElement(getBy(label)).isDisplayed();
    }

    public boolean isNotDisplayed(String label) {
        try {
            waitForInvisible(label);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
