package com.epam.reportportal.core.ui.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@Log4j2
public class ChromeDriverCreator implements WebDriverCreator {

    @Override
    public WebDriver createDriver() {
        WebDriverManager.chromedriver().clearDriverCache().setup();
        try {
            return new ChromeDriver();
        } catch (SessionNotCreatedException e) {
            log.fatal(String.format("ERROR: ChromeDriver is not started: %s", e.getMessage()));
        }
        return null;
    }
}
