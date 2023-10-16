package com.epam.reportportal.core.ui.drivers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

@Log4j2
public class RemoteDriverCreator implements WebDriverCreator {
    @Override
    public WebDriver createDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("118.0.5993.71");
        capabilities.setPlatform(Platform.WINDOWS);
        try {
            return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            log.fatal(String.format("ERROR: RemoteDriver is not started: %s", e.getMessage()));
        }
        return null;
    }
}
