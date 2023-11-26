package com.epam.reportportal.business.factories;

import com.epam.reportportal.business.models.response_launches_data.LaunchesContent;
import org.apache.commons.lang3.RandomStringUtils;

public class LaunchFactory {

    public static LaunchesContent validLaunchWithUpdatedDescription(LaunchesContent launchesContent) {

        launchesContent.setDescription(RandomStringUtils.random(10, true, false));
        return launchesContent;
    }

    public static LaunchesContent launchWithInvalidMode(LaunchesContent launchesContent) {

        launchesContent.setMode("123");
        return launchesContent;
    }
}
