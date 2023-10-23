package com.epam.reportportal.ui.testng_tests;

import com.epam.reportportal.TestNGBaseTest;
import com.epam.reportportal.business.factories.UserFactory;
import com.epam.reportportal.business.models.User;
import com.epam.reportportal.business.ui.pages.LaunchesPage;
import com.epam.reportportal.business.ui.steps.CommonSteps;
import com.epam.reportportal.business.ui.steps.DashboardSteps;
import com.epam.reportportal.business.ui.steps.LoginSteps;
import com.epam.reportportal.core.common.utils.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.epam.reportportal.business.ui.constants.CommonPropertiesConst.LAUNCHES_PAGE_NAME_LABEL;
import static com.epam.reportportal.business.ui.constants.CommonPropertiesConst.SORTING_LAUNCHES_BY_START_TIME_LABEL;

@Test(testName = "User is able to see launches list sorted by most recent by default, and is able to resort them")
public class SortLaunchesTests extends TestNGBaseTest {

    private User defUser = UserFactory.defaultUser();
    private LaunchesPage launchesPage;
    private static final String COMMON_PROPS_FILE_NAME = "common";

    @BeforeMethod(description = "Go to the 'Launches' page")
    public void goToLaunchesPage() {
        new LoginSteps(driver).signIn(defUser);
        new DashboardSteps(driver).selectCurrentProject();
        new CommonSteps(driver).openPage(PropertyReader.getProperties(COMMON_PROPS_FILE_NAME, LAUNCHES_PAGE_NAME_LABEL));
        launchesPage = new LaunchesPage(driver);
    }

    @Test(description = "Verify launches is sorted by most recent by default")
    public void launchesSortedByMostRecentTest() {
        launchesPage.waitForPageLoaded();
        Assert.assertTrue(launchesPage.isLaunchesSortedInDescendingOrder(),
                "Lunches is not sorted by most recent by default!");
    }

    @Test(description = "Verify user is able to resort launches", dependsOnMethods = "launchesSortedByMostRecentTest")
    public void userIsAbleToResortLaunchesTest() {
        launchesPage.sortLaunches(PropertyReader.getProperties(COMMON_PROPS_FILE_NAME, SORTING_LAUNCHES_BY_START_TIME_LABEL));
        Assert.assertFalse(launchesPage.isLaunchesSortedInDescendingOrder(),
                "User is NOT able to resort launches!");
    }
}
