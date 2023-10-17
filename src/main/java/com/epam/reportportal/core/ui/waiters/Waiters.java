package com.epam.reportportal.core.ui.waiters;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Log4j2
public class Waiters {

    WebDriver driver;
    WebDriverWait wait;

    public Waiters(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementLocated(By element, Duration timeout) {
        try {
            wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        } catch (TimeoutException e) {
            log.error(String.format("Element: '%s' isn't found after %s", timeout, e.getLocalizedMessage()));
        }
    }

    public void waitForElementIsDisappeared(By element, Duration timeout) {
        try {
            wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
        } catch (TimeoutException e) {
            log.error(String.format("Element: '%s' is still displayed after %s", timeout, e.getLocalizedMessage()));
        }
    }
}
