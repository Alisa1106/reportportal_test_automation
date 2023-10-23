package com.epam.reportportal.api.junit_tests;

import com.epam.reportportal.JUnitBaseTest;
import com.epam.reportportal.business.models.ResponseData;
import com.epam.reportportal.business.models.response_data.Content;
import io.restassured.response.Response;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;

@RunWith(JUnitParamsRunner.class)
@Execution(ExecutionMode.CONCURRENT)
@DisplayName("User is able to get all launches by filter via GET request")
public class FilterLaunchesTests extends JUnitBaseTest {

    @Parameters({
            "name,Demo%20Api%20Tests",
            "status,PASSED",
            "user,ahar",
    })
    @Test
    @DisplayName("Verify possibility to get launches by filter GET request")
    public void getLaunchesByFilterTest(String filterName, String filterValue) {
        Response response = client.getLaunchesByFilter(filterName, filterValue);
        ResponseData responseData = response.getBody().as(ResponseData.class);
        List<Long> launchesIds = new ArrayList<>();
        Arrays.stream(responseData.getContent())
                .map(Content::getId)
                .forEach(launchesIds::add);

        boolean allMatchPattern = launchesIds.stream()
                .map(String::valueOf)
                .allMatch(id -> id.matches("^[0-9]{7}$"));

        assertAll(() -> Assertions.assertEquals(SC_OK, response.getStatusCode(), "Response status code is NOT 200!"),
                () -> Assertions.assertTrue(allMatchPattern,
                        "Object from response has no ID or ID is incorrect!"));

    }
}
