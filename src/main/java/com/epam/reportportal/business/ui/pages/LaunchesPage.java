package com.epam.reportportal.business.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.IntStream;

public class LaunchesPage extends SideBar {

    private static final String TITLE = "All launches";
    private static final String LAUNCH_INDEX_XPATH = "//td[contains(@class,'launchSuiteGrid__name-col--')]" +
            "//span[contains(@class,'itemInfo__number')]";

    public LaunchesPage(WebDriver driver) {
        super(driver);
    }

    public LaunchesPage waitForPageLoaded() {
        customActions.watTitle(TITLE);
        waitForSpinnerIsDisappeared();
        return this;
    }

    public LaunchesPage sortLaunches(String sortingTypeName) {
        customActions.clickTextHeader(sortingTypeName);
        waitForSpinnerIsDisappeared();
        return this;
    }

    public boolean isLaunchesSortedInDescendingOrder() {
        List<Integer> launchIndexes = driver.findElements(By.xpath(LAUNCH_INDEX_XPATH)).stream()
                .map(webElement -> Integer.valueOf(webElement.getText().substring(1)))
                .toList();
        int size = launchIndexes.size();
        return IntStream.range(0, size)
                .allMatch(i -> IntStream.range(i + 1, size)
                        .allMatch(j -> launchIndexes.get(i) > launchIndexes.get(j)));
    }
}
