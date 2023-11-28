package com.epam.reportportal.business.api;

import com.epam.reportportal.core.common.utils.PropertyReader;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static com.epam.reportportal.business.ui.constants.CommonPropertiesConst.CURRENT_PROJECT_NAME_LABEL;

public class UrlBuilder {

    private String baseUrl = PropertyReader.getProperties("api", "base.url");

    private static final String PROJECT_NAME = PropertyReader.getProperties("common", CURRENT_PROJECT_NAME_LABEL).toUpperCase();

    public String demoDataUri() {
        return baseUrl + String.format("demo/%s/generate", PROJECT_NAME);
    }

    public String launchUri() {
        return baseUrl + String.format("%s/launch", PROJECT_NAME);
    }

    public String launchByFilterUri(String filterName, String filterValue) {
        String encodedParameter = URLEncoder.encode(filterValue, StandardCharsets.UTF_8);
        return baseUrl + String.format("%s/launch?filter.eq.%s=%s", PROJECT_NAME, filterName, encodedParameter).trim();
    }

    public String launchToCompareUri(long firstLaunchId, long secondLaunchId) {
        return baseUrl + String.format("%s/launch/compare?ids=%s&ids=%s", PROJECT_NAME, firstLaunchId, secondLaunchId);
    }

    public String launchAnalysisUri() {
        return baseUrl + String.format("%s/launch/analyze", PROJECT_NAME);
    }

    public String filtersUri() {
        return baseUrl + String.format("%s/filter", PROJECT_NAME);
    }

    public String filtersByIdUri(long filterId) {
        return baseUrl + String.format("%s/filter/%s", PROJECT_NAME, filterId);
    }

    public String launchByIdToUpdateUri(long launchId) {
        return baseUrl + String.format("%s/launch/%s/update", PROJECT_NAME, launchId);
    }
}
