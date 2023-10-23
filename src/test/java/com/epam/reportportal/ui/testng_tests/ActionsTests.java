package com.epam.reportportal.ui.testng_tests;

import com.epam.reportportal.TestNGBaseTest;
import com.epam.reportportal.business.factories.UserFactory;
import com.epam.reportportal.business.models.User;
import com.epam.reportportal.business.ui.steps.CommonSteps;
import com.epam.reportportal.business.ui.steps.DashboardSteps;
import com.epam.reportportal.business.ui.steps.LaunchesSteps;
import com.epam.reportportal.business.ui.steps.LoginSteps;
import com.epam.reportportal.core.common.utils.PropertyReader;
import com.epam.reportportal.core.common.utils.testng_utils.TestData;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.epam.reportportal.business.ui.constants.CommonPropertiesConst.LAUNCHES_PAGE_NAME_LABEL;

@Test(testName = "User is able to select launches and perform different actions with them")
public class ActionsTests extends TestNGBaseTest {

    private User defUser = UserFactory.defaultUser();
    private static final String COMMON_PROPS_FILE_NAME = "common";

    @Test(dataProvider = "Actions", dataProviderClass = TestData.class,
            description = "Verify clicking the option after selecting several launches lead to corresponding modals")
    public void actionModalTest(String actionName) {
        new LoginSteps(driver).signIn(defUser);
        new DashboardSteps(driver).selectCurrentProject();
        new CommonSteps(driver).openPage(PropertyReader.getProperties(COMMON_PROPS_FILE_NAME, LAUNCHES_PAGE_NAME_LABEL));
        LaunchesSteps launchesSteps = new LaunchesSteps(driver);
        launchesSteps.selectLaunchesAndGoToAction(actionName, 2, 3);
        Assert.assertTrue(launchesSteps.isCurrentModalOpened(actionName), String.format("'%s' modal is not opened!", actionName));
    }
}
