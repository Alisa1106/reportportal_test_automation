package com.epam.reportportal.business.ui.modals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Set;
import java.util.stream.Collectors;

public class CompareLaunchesModal extends AbstractModal {

    private static final String TITLE = "Compare launches";
    private final By LAUNCH_NUMBER = By.xpath("//div[contains(@class,'launchesComparisonChart')]" +
            "//*[@class='tick']//*[contains(@style,'text-anchor: middle')]");

    public CompareLaunchesModal(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isModalOpened() {
        return customActions.isTitleDisplayed(TITLE);
    }

    public Set<String> getLaunchesNamesText() {
        return driver.findElements(LAUNCH_NUMBER).stream()
                .map(WebElement::getText)
                .filter(text -> !text.isEmpty())
                .collect(Collectors.toSet());
    }
}
