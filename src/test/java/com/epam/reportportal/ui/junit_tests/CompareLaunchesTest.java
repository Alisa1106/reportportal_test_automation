package com.epam.reportportal.ui.junit_tests;


import com.epam.reportportal.JUnitBaseTest;
import com.epam.reportportal.business.factories.UserFactory;
import com.epam.reportportal.business.models.User;
import com.epam.reportportal.business.ui.steps.CommonSteps;
import com.epam.reportportal.business.ui.steps.DashboardSteps;
import com.epam.reportportal.business.ui.steps.LaunchesSteps;
import com.epam.reportportal.business.ui.steps.LoginSteps;
import com.epam.reportportal.core.common.utils.PropertyReader;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.runner.RunWith;

import static com.epam.reportportal.business.ui.constants.CommonPropertiesConst.LAUNCHES_PAGE_NAME_LABEL;
import static org.junit.jupiter.api.Assertions.assertAll;

@RunWith(JUnitParamsRunner.class)
@Execution(ExecutionMode.CONCURRENT)
@DisplayName("User is able to select several launches and compare them")
public class CompareLaunchesTest extends JUnitBaseTest {

    private User defUser = UserFactory.defaultUser();
    private static final String COMMON_PROPS_FILE_NAME = "common";

    @Parameters({
            "1,2,2",
            "2,3,2",
            "3,4,2",
            "4,5,2",
            "0,1,4"
    })
    @Test
    @DisplayName("Verify possibility to compare launches")
    public void compareLaunchesTest(int firstCheckboxIndex, int secondCheckboxIndex, int size) {
        new LoginSteps(driver).signIn(defUser);
        new DashboardSteps(driver).selectCurrentProject();
        new CommonSteps(driver).openPage(PropertyReader.getProperties(COMMON_PROPS_FILE_NAME, LAUNCHES_PAGE_NAME_LABEL));
        LaunchesSteps launchesSteps = new LaunchesSteps(driver);
        launchesSteps.selectLaunchesAndGoToAction("Compare", firstCheckboxIndex, secondCheckboxIndex);
        assertAll(() -> Assertions.assertTrue(launchesSteps.isCurrentModalOpened("Compare"),
                        "Compare modal is not opened!"),
                () -> Assertions.assertEquals(launchesSteps.getLaunchesNames().size(), size,
                        "Launches names number is not equal to numbers of selected launches!"));

    }
}
