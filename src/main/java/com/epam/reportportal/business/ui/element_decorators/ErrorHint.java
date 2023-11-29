package com.epam.reportportal.business.ui.element_decorators;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.epam.reportportal.business.ui.constants.TimeoutCons.SHORT_TIMEOUT_IN_SECONDS;

@Log4j2
public class ErrorHint extends AbstractElement {

    private static final String ERROR_HINT_XPATH = "//div[contains(@class,'fieldErrorHint')]";

    public ErrorHint(WebDriver driver) {
        super(driver);
    }

    public void click() {
        log.info("Click on the hint.");
        By hint = By.xpath(ERROR_HINT_XPATH);
        waiters.waitForElementLocated(hint, Duration.ofSeconds(SHORT_TIMEOUT_IN_SECONDS));
        driver.findElement(hint).click();
    }
}
