package com.epam.reportportal.business.ui.element_decorators;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.epam.reportportal.business.ui.constants.TimeoutCons.SHORT_TIMEOUT_IN_SECONDS;

@Log4j2
public class Selector extends AbstractElement {

    private static final String MAIN_SELECTOR_LOCATOR = "//aside[contains(@class,'sidebar__sidebar')]" +
            "//div[contains(@class,'projectSelector__current-project-name')]";
    private static final String PROJECT_SELECTOR_LOCATOR = "//a[contains(@class,'projectSelector__project-list-item')]" +
            "/span[@title='%s']";

    public Selector(WebDriver driver) {
        super(driver);
    }

    public void click() {
        log.info("Click on the main project selector.");
        By selector = By.xpath(String.format(MAIN_SELECTOR_LOCATOR));
        waiters.waitForElementLocated(selector, Duration.ofSeconds(SHORT_TIMEOUT_IN_SECONDS));
        driver.findElement(selector).click();
    }

    public void select(String value) {
        log.info(String.format("Click on the project selector for project '%s'", value));
        By selector = By.xpath(String.format(PROJECT_SELECTOR_LOCATOR, value));
        waiters.waitForElementLocated(selector, Duration.ofSeconds(SHORT_TIMEOUT_IN_SECONDS));
        driver.findElement(selector).click();
    }
}
