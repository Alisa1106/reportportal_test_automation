package com.epam.reportportal.business.ui.element_decorators;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.epam.reportportal.business.ui.constants.TimeoutCons.SHORT_TIMEOUT_IN_SECONDS;

@Log4j2
public class Input extends AbstractElement {

    private String label;
    private static final String INPUT_XPATH = "//input[@placeholder='%s']";

    public Input(WebDriver driver, String label) {
        super(driver);
        this.label = label;
    }

    public void writeText(String text) {
        log.info(String.format("Fill the value '%s' in the '%s' field.", text, label));
        By input = By.xpath(String.format(INPUT_XPATH, label));
        waiters.waitForElementLocated(input, Duration.ofSeconds(SHORT_TIMEOUT_IN_SECONDS));
        driver.findElement(input).sendKeys(text);
    }
}
