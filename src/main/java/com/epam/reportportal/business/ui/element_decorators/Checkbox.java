package com.epam.reportportal.business.ui.element_decorators;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import static com.epam.reportportal.business.ui.constants.TimeoutCons.SHORT_TIMEOUT_IN_SECONDS;

@Log4j2
public class Checkbox extends AbstractElement {

    private static final String UNCHECKED_CHECKBOX_XPATH = "//div[contains(@class,'checkIcon__square')]";

    public Checkbox(WebDriver driver) {
        super(driver);
    }

    public void mark(int index) {
        log.info(String.format("Click on checkbox at index: %s", index));
        By checkbox = By.xpath(UNCHECKED_CHECKBOX_XPATH);
        waiters.waitForElementLocated(checkbox, Duration.ofSeconds(SHORT_TIMEOUT_IN_SECONDS));
        List<WebElement> checkboxes = driver.findElements(checkbox);
        Stream.of(index)
                .filter(i -> index >= 0 && index < checkboxes.size())
                .forEach(i -> {
                    checkboxes.get(index).click();
                });
    }
}
