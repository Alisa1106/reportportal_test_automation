package com.epam.reportportal.core.common.utils;


import com.epam.reportportal.core.ui.drivers.DriverContainer;
import io.qameta.allure.Attachment;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Log4j2
public class JUnitListener implements TestWatcher {

    @Before
    public void onTestStart(ExtensionContext context) {
        log.info(String.format("======================================== STARTING TEST '%s' ========================================%n", context.getDisplayName()));
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        log.info(String.format("======================================== FINISHED TEST '%s' Duration: %ss ========================================%n", context.getDisplayName(),
                getExecutionTime()));
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        log.info(String.format("======================================== FAILED TEST '%s' Duration: %ss, cause: %s ========================================%n", context.getDisplayName(),
                getExecutionTime(), cause));
        takeScreenshot();
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        log.info(String.format("======================================== SKIPPING TEST '%s', cause: %s ========================================%n", context.getDisplayName(), cause));
        takeScreenshot();
    }

    @Attachment(value = "Last screen state", type = "image/png")
    private byte[] takeScreenshot() {
        try {
            WebDriver driver = DriverContainer.getDriver();
            ;
            if (driver != null) {
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(screenshot, new File("target/screenshots/" + getCurrentTimeAsString() + ".png"));
                log.info("Screenshot has been taken!");
                return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            } else {
                return new byte[]{};
            }
        } catch (NoSuchSessionException | IllegalStateException | IOException ex) {
            return new byte[]{};
        }
    }

    @Before
    public Instant getTestStartTime() {
        return Instant.now();
    }

    @After
    public long getExecutionTime() {
        Duration executionTime = Duration.between(getTestStartTime(), Instant.now());
        return executionTime.getSeconds();
    }

    private String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }
}
