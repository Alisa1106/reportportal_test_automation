package com.epam.reportportal.core.ui.drivers;

public class DriverCreator {

    public static WebDriverCreator create(String browser) {
        return switch (browser) {
            case "edge" -> new FirefoxDriverCreator();
            case "remote" -> new RemoteDriverCreator();
            default -> new ChromeDriverCreator();
        };
    }
}
