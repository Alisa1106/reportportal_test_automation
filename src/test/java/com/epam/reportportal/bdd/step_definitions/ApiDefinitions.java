package com.epam.reportportal.bdd.step_definitions;

import com.epam.reportportal.business.api.Client;
import com.epam.reportportal.business.models.ResponseData;
import com.epam.reportportal.business.models.response_data.Content;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApiDefinitions {

    Response allLaunchesResponse;
    ResponseData responseData;
    Client client;

    @When("^I send GET request to get Launches list by filtering by \"([^\"]*)\": \"([^\"]*)\"$")
    public void sendGetRequestToGetLaunchesListByFilteringBy(String filterName, String filterValue) {
        client = new Client();
        allLaunchesResponse = client.getLaunchesByFilter(filterName, filterValue);
        responseData = allLaunchesResponse.getBody().as(ResponseData.class);
    }


    @When("^I get launches IDs from response$")
    public List<Long> getLaunchesIDsFromResponse() {
        List<Long> launchesIds = new ArrayList<>();
        Arrays.stream(responseData.getContent())
                .map(Content::getId)
                .forEach(launchesIds::add);
        return launchesIds;
    }

    @Then("^I see request status code is (\\d+)$")
    public void checkRequestStatusCode(int statusCode) {
        Assertions.assertEquals(statusCode, allLaunchesResponse.getStatusCode(), "Response status code is NOT 200!");
    }

    @Then("^I get list of Launches when every Launch contains ID$")
    public void getListOfLaunchesWhenEveryLaunchContainsID() {
        List<Long> launchesIdsList = getLaunchesIDsFromResponse();
        if (launchesIdsList.isEmpty()) {
            throw new IllegalArgumentException("There are no Launches in the list!");
        } else {
            boolean allMatchPattern = getLaunchesIDsFromResponse().stream()
                    .map(String::valueOf)
                    .allMatch(id -> id.matches("^[0-9]{7}$"));
            Assertions.assertTrue(allMatchPattern, "Object from response has no ID or ID is incorrect!");
        }
    }
}
