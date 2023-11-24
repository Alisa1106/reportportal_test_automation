package com.epam.reportportal.business.ui.element_decorators;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

@Log4j2
public class DonutChart extends AbstractElement {

    private String label;
    private static final String DONUT_CHART_XPATH = "//div[contains(@class,'donutChart__chart-container')]//*//*[@stroke='%s']";

    public DonutChart(WebDriver driver, String label) {
        super(driver);
        this.label = label;
    }

    public void hover() {
        Actions actions = new Actions(driver);
        log.info(String.format("Hover to the donut chart with color: %s", label));
        actions.moveToElement(driver.findElement(By.xpath(String.format(DONUT_CHART_XPATH, label)))).build().perform();
    }
}
