package com.epam.reportportal.business.ui.element_decorators;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.epam.reportportal.business.ui.constants.TimeoutCons.SHORT_TIMEOUT_IN_SECONDS;

@Log4j2
public class TextHeader extends AbstractElement {

    private String label;
    private static final String TEXT_HEADER_XPATH = "//span[text()='%s']";

    public TextHeader(WebDriver driver, String label) {
        super(driver);
        this.label = label;
    }

    public void click() {
        log.info(String.format("Click on the text '%s'.", label));
        By textHeader = By.xpath(String.format(TEXT_HEADER_XPATH, label));
        waiters.waitForElementLocated(textHeader, Duration.ofSeconds(SHORT_TIMEOUT_IN_SECONDS));
        driver.findElement(textHeader).click();
    }
}
