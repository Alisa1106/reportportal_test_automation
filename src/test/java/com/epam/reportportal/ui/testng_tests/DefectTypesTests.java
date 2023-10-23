package com.epam.reportportal.ui.testng_tests;

import com.epam.reportportal.TestNGBaseTest;
import com.epam.reportportal.business.factories.UserFactory;
import com.epam.reportportal.business.models.User;
import com.epam.reportportal.business.ui.steps.CommonSteps;
import com.epam.reportportal.business.ui.steps.DashboardSteps;
import com.epam.reportportal.business.ui.steps.LaunchesSteps;
import com.epam.reportportal.business.ui.steps.LoginSteps;
import com.epam.reportportal.core.common.utils.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.epam.reportportal.core.common.utils.testng_utils.TestData;

import static com.epam.reportportal.business.ui.constants.CommonPropertiesConst.LAUNCHES_PAGE_NAME_LABEL;

@Test(testName = "User is able to see different defect types in different colors")
public class DefectTypesTests extends TestNGBaseTest {

    private User defUser = UserFactory.defaultUser();
    private static final String COMMON_PROPS_FILE_NAME = "common";

    @Test(dataProvider = "Defect types", dataProviderClass = TestData.class,
            description = "Verify the Donut Charts colors correspond related defect type")
    public void defectTypesHaveProperColorTest(String color, String defectType) {
        new LoginSteps(driver).signIn(defUser);
        new DashboardSteps(driver).selectCurrentProject();
        new CommonSteps(driver).openPage(PropertyReader.getProperties(COMMON_PROPS_FILE_NAME, LAUNCHES_PAGE_NAME_LABEL));
        Assert.assertEquals(new LaunchesSteps(driver).getCurrentDefectType(color), defectType,
                String.format("Donut chart with color '%s' is not correspond to type '%s'!", color, defectType));
    }
}
