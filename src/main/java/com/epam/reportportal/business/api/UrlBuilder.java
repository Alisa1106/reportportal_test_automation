package com.epam.reportportal.business.api;

import com.epam.reportportal.core.common.utils.PropertyReader;

import static com.epam.reportportal.business.ui.constants.CommonPropertiesConst.CURRENT_PROJECT_NAME_LABEL;

public class UrlBuilder {

    private String url = "";
    private static final String PROJECT_NAME = PropertyReader.getProperties("common", CURRENT_PROJECT_NAME_LABEL).toUpperCase();

    public String demoDataUri() {
        return url + String.format("demo/%s/generate", PROJECT_NAME);
    }

    public String launchUri() {
        return url + String.format("%s/launch", PROJECT_NAME);
    }

    public String launchByFilterUri(String filterName, String filterValue) {
        return url + String.format("%s/launch?filter.eq.%s=%s", PROJECT_NAME, filterName, filterValue);
    }

    public String launchToCompareUri(long firstLaunchId, long secondLaunchId) {
        return url + String.format("%s/launch/compare?ids=%s&ids=%s", PROJECT_NAME, firstLaunchId, secondLaunchId);
    }

    public String launchAnalysisUri() {
        return url + String.format("%s/launch/analyze", PROJECT_NAME);
    }

    public String filtersUri() {
        return url + String.format("%s/filter", PROJECT_NAME);
    }

    public String filtersByIdUri(long filterId) {
        return url + String.format("%s/filter/%s", PROJECT_NAME, filterId);
    }

    public String launchByIdToUpdateUri(long launchId) {
        return url + String.format("%s/launch/%s/update", PROJECT_NAME, launchId);
    }
}
