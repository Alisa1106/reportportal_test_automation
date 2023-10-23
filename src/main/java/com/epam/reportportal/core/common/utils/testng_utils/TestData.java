package com.epam.reportportal.core.common.utils.testng_utils;

import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider(name = "Defect types")
    static Object[][] donutCharts() {
        return new Object[][]{
                {"#ec3900", "Product Bug"},
                {"#f7d63e", "Automation Bug"},
                {"#0274d1", "System Issue"},
                {"#ffb743", "To Investigate"}
        };
    }

    @DataProvider(name = "Actions")
    static Object[][] actions() {
        return new Object[][]{
                {"Edit"},
                {"Merge"},
                {"Compare"},
                {"Move to debug"},
                {"Delete"}
        };
    }
}
