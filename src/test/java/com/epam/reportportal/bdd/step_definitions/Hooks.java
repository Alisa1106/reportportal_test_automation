package com.epam.reportportal.bdd.step_definitions;

import com.epam.reportportal.TestRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    TestRunner testRunner = new TestRunner();

    @Before(value = "@createTestData")
    public void createTestData() {
        testRunner.createTestData();
    }

    @Before(value = "@createDriver")
    public void createDriver() {
        testRunner.initDriver();
    }

    @After(value = "@removeDriver")
    public void removeDriver() {
        testRunner.removeDriver();
    }

    @After(value = "@deleteTestData")
    public void deleteTestData() {
        testRunner.deleteTestData();
    }
}
