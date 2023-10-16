package com.epam.reportportal.business.ui.element_decorators;

import com.epam.reportportal.core.ui.waiters.Waiters;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

@Data
@Log4j2
public abstract class AbstractElement {

    protected WebDriver driver;
    protected Waiters waiters;

    public AbstractElement(WebDriver driver) {
        this.driver = driver;
        this.waiters = new Waiters(driver);
    }
}
