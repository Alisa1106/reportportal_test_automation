package com.epam.reportportal.core.ui.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@Log4j2
public class ChromeDriverCreator implements WebDriverCreator {

    @Override
    public WebDriver createDriver() {
        WebDriverManager.chromedriver().clearDriverCache().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless", "--window-size=1920,1200", "--disable-dev-shm-usage");
        try {
            return new ChromeDriver(chromeOptions);
        } catch (SessionNotCreatedException e) {
            log.fatal(String.format("ERROR: ChromeDriver is not started: %s", e.getMessage()));
        }
        return null;
    }
}
