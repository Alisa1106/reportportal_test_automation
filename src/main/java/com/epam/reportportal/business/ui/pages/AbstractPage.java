package com.epam.reportportal.business.ui.pages;

import com.epam.reportportal.business.ui.actions.CustomActions;
import com.epam.reportportal.business.ui.constants.CommonPropertiesConst;
import com.epam.reportportal.core.common.utils.PropertyReader;
import com.epam.reportportal.core.ui.waiters.Waiters;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.epam.reportportal.business.ui.constants.TimeoutCons.MEDIUM_TIMEOUT_IN_SECONDS;

@Data
public abstract class AbstractPage implements CommonPropertiesConst {

    protected WebDriver driver;
    protected CustomActions customActions;
    protected Waiters waiters;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        this.customActions = new CustomActions(driver);
        this.waiters = new Waiters(driver);
    }

    public String getProperties(String propertyName) {
        return PropertyReader.getProperties("common", propertyName);
    }

    public void waitForSpinnerIsDisappeared() {
        waiters.waitForElementIsDisappeared(By.xpath("//div[contains(@class,'grid__spinner-block')]"),
                Duration.ofSeconds(MEDIUM_TIMEOUT_IN_SECONDS));
    }
}
