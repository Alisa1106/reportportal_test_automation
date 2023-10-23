package com.epam.reportportal;

import com.epam.reportportal.business.api.Client;
import com.epam.reportportal.business.factories.DashboardFactory;
import com.epam.reportportal.business.models.Launches;
import com.epam.reportportal.business.models.ResponseData;
import com.epam.reportportal.business.models.response_data.Content;
import com.epam.reportportal.core.common.utils.JUnitListener;
import com.epam.reportportal.core.ui.drivers.DriverContainer;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2
@ExtendWith({SpringExtension.class, JUnitListener.class})
public class JUnitBaseTest {

    protected WebDriver driver;
    protected Client client;
    protected List<Long> idList = new ArrayList<>();

    @Before
    public void createTestData() {
        client = new Client();
        log.debug("Create demo data");
        client.createDemoData(DashboardFactory.demoDashboard());
        String jsonResponse = client.getLaunches().asString();
        Gson gson = new Gson();
        ResponseData responseData = gson.fromJson(jsonResponse, ResponseData.class);
        Arrays.stream(responseData.getContent())
                .map(Content::getId)
                .forEach(idList::add);
    }

    @Before
    public void initTest() {
        driver = DriverContainer.getDriver();
        driver.manage().window().maximize();
        String variable = "driver";
        log.debug(String.format("Setting driver into context with variable name: %s", variable));
    }

    @After
    public void endTest() {
        log.debug("Close browser");
        DriverContainer.getDriver().quit();
        DriverContainer.removeDriver();
    }

    @After
    public void deleteTestData() {
        Launches launches = new Launches();
        launches.setIdList(idList);
        log.debug("Delete demo data");
        client.deleteLaunches(launches);
    }
}
