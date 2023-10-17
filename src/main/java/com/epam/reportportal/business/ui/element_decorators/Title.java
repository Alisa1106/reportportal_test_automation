package com.epam.reportportal.business.ui.element_decorators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.epam.reportportal.business.ui.constants.TimeoutCons.LONG_TIMEOUT_IN_SECONDS;

public class Title extends AbstractElement {

    private static final String TITLE_XPATH = "(//span[@title='%s']) | (//div[text()='%s'])";

    public Title(WebDriver driver) {
        super(driver);
    }

    public void wait(String label) {
        By title = By.xpath(String.format(TITLE_XPATH, label, label));
        waiters.waitForElementLocated(title, Duration.ofSeconds(LONG_TIMEOUT_IN_SECONDS));
    }
}
