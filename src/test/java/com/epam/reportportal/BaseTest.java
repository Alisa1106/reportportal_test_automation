package com.epam.reportportal;

import com.epam.reportportal.core.common.utils.TestListener;
import com.epam.reportportal.core.ui.drivers.DriverContainer;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Log4j2
@Listeners(TestListener.class)
public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public void initTest(ITestContext context) {
        driver = DriverContainer.getDriver();
        driver.manage().window().setSize(new Dimension(1920, 1080));
        String variable = "driver";
        log.debug(String.format("Setting driver into context with variable name: %s", variable));
        context.setAttribute(variable, DriverContainer.getDriver());
    }

    @AfterClass(alwaysRun = true)
    public void endTest() {
        log.debug("Close browser");
        DriverContainer.getDriver().quit();
        DriverContainer.removeDriver();
    }
}
