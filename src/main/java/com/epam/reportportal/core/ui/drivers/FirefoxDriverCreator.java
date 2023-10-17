package com.epam.reportportal.core.ui.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

@Log4j2
public class FirefoxDriverCreator implements WebDriverCreator {

    @Override
    public WebDriver createDriver() {
        WebDriverManager.firefoxdriver().clearDriverCache().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--headless", "--window-size=1920,1200", "--disable-dev-shm-usage");
        try {
            return new FirefoxDriver(firefoxOptions);
        } catch (SessionNotCreatedException e) {
            log.fatal(String.format("ERROR: EdgeDriver is not started: %s", e.getMessage()));
        }
        return null;
    }
}
