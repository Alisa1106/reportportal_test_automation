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
}
