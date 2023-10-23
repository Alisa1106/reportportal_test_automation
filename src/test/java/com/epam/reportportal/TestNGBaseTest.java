package com.epam.reportportal;

import com.epam.reportportal.business.api.Client;
import com.epam.reportportal.business.factories.DashboardFactory;
import com.epam.reportportal.business.models.Launches;
import com.epam.reportportal.business.models.ResponseData;
import com.epam.reportportal.business.models.response_data.Content;
import com.epam.reportportal.core.common.utils.TestNGListener;
import com.epam.reportportal.core.ui.drivers.DriverContainer;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2
@Listeners(TestNGListener.class)
public class TestNGBaseTest {

    protected WebDriver driver;
    protected Client client;
    private List<Long> idList = new ArrayList<>();

    @BeforeMethod
    public void initTest(ITestContext context) {
        driver = DriverContainer.getDriver();
        driver.manage().window().maximize();
        String variable = "driver";
        log.debug(String.format("Setting driver into context with variable name: %s", variable));
        context.setAttribute(variable, DriverContainer.getDriver());
    }

    @BeforeMethod
    public void createTestData() {
        client = new Client();
        log.debug("Create demo data");
        client.createDemoData(DashboardFactory.demoDashboard());
        String jsonResponse = client.getLaunches().asString();
        Gson gson = new Gson();
        ResponseData response = gson.fromJson(jsonResponse, ResponseData.class);
        Arrays.stream(response.getContent())
                .map(Content::getId)
                .forEach(idList::add);
    }

    @AfterMethod(alwaysRun = true)
    public void endTest() {
        log.debug("Close browser");
        DriverContainer.getDriver().quit();
        DriverContainer.removeDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void deleteTestData() {
        Launches launches = new Launches();
        launches.setIdList(idList);
        log.debug("Delete demo data");
        client.deleteLaunches(launches);
    }
}
