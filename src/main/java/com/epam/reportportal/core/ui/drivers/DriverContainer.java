package com.epam.reportportal.core.ui.drivers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

@Log4j2
public class DriverContainer {
    static ThreadLocal<WebDriver> local = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (local.get() == null) {
            log.debug("New driver created!");
            local.set(DriverCreator.create(System.getenv().getOrDefault("browser", "chrome")).createDriver());
        }
        return local.get();
    }

    public static void removeDriver() {
        if (local.get() != null) {
            log.debug("Driver removed.");
            local.remove();
        }
    }
}
