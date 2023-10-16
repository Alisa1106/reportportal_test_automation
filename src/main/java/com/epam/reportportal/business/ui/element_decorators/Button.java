package com.epam.reportportal.business.ui.element_decorators;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.epam.reportportal.business.ui.constants.TimeoutCons.SHORT_TIMEOUT_IN_SECONDS;

@Log4j2
public class Button extends AbstractElement {

    private String label;
    private static final String BUTTON_XPATH = "(//button[text()='%s']) | (//button//span[text()='%s']) | (//input[@value='%s'])";

    public Button(WebDriver driver, String label) {
        super(driver);
        this.label = label;
    }

    public void click() {
        log.info(String.format("Click on the button '%s'.", label));
        By button = By.xpath(String.format(BUTTON_XPATH, label, label, label));
        waiters.waitForElementLocated(button, Duration.ofSeconds(SHORT_TIMEOUT_IN_SECONDS));
        driver.findElement(button).click();
    }
}
