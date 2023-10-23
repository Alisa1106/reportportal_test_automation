package com.epam.reportportal.business.ui.element_decorators;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.epam.reportportal.business.ui.constants.TimeoutCons.SHORT_TIMEOUT_IN_SECONDS;

@Log4j2
public class Link extends AbstractElement {

    private String projectName;
    private String label;
    private static final String LINK_XPATH = "//a[@href='#%s/%s']";

    public Link(WebDriver driver, String projectName, String label) {
        super(driver);
        this.projectName = projectName;
        this.label = label;
    }

    public void click() {
        log.info(String.format("Click on the link '%s/%s'.", projectName, label));
        By link = By.xpath(String.format(LINK_XPATH, projectName, label));
        waiters.waitForElementLocated(link, Duration.ofSeconds(SHORT_TIMEOUT_IN_SECONDS));
        driver.findElement(link).click();
    }
}
