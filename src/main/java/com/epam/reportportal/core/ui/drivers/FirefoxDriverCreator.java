package com.epam.reportportal.core.ui.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@Log4j2
public class FirefoxDriverCreator implements WebDriverCreator {

    @Override
    public WebDriver createDriver() {
        WebDriverManager.firefoxdriver().clearDriverCache().setup();
        try {
            return new FirefoxDriver();
        } catch (SessionNotCreatedException e) {
            log.fatal(String.format("ERROR: EdgeDriver is not started: %s", e.getMessage()));
        }
        return null;
    }
}
