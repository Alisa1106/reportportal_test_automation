package com.epam.reportportal.bdd.step_definitions;

import com.epam.reportportal.business.api.Client;
import com.epam.reportportal.business.api.Response;
import com.epam.reportportal.business.factories.FiltersFactory;
import com.epam.reportportal.business.factories.LaunchAnalysisFactory;
import com.epam.reportportal.business.factories.LaunchFactory;
import com.epam.reportportal.business.models.*;
import com.epam.reportportal.business.models.Reponse_filters_data.FiltersContent;
import com.epam.reportportal.business.models.response_launches_data.LaunchesContent;
import com.google.gson.Gson;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ApiDefinitions {

    Response response;
    ResponseLaunchesData responseLaunchesData;
    Client client;
    ResponseMessage responseMessage;
    ResponseFiltersData responseFiltersData;
    ResponseDeletedLaunchData responseDeletedLaunchData;

    private <T> T parseResponse(String responseBody, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(responseBody, clazz);
    }

    @When("^I send GET request to get Launches list by filtering by \"([^\"]*)\": \"([^\"]*)\"$")
    public void sendGetRequestToGetLaunchesListByFilteringBy(String filterName, String filterValue) {
        client = new Client();
        response = client.getLaunchesByFilter(filterName, filterValue);
        responseLaunchesData = parseResponse(response.getBody(), ResponseLaunchesData.class);
    }

    @When("^I get launches IDs from response$")
    public List<Long> getLaunchesIDsFromResponse() {
        List<Long> launchesIds = new ArrayList<>();
        Arrays.stream(responseLaunchesData.getContent())
                .map(LaunchesContent::getId)
                .forEach(launchesIds::add);
        return launchesIds;
    }

    @Then("^I see request status code is (\\d+)$")
    public void checkRequestStatusCode(int statusCode) {
        Assertions.assertEquals(statusCode, response.getStatusCode(), String.format("Response status code is NOT %s", statusCode));
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

    @When("^I send GET request to get Launches list$")
    public void sendGetRequestToGetLaunchesList() {
        client = new Client();
        response = client.getLaunches();
        responseLaunchesData = parseResponse(response.getBody(), ResponseLaunchesData.class);
    }

    @When("^I send GET request to compare launches (\\d+) and (\\d+)$")
    public void sendGetRequestToCompareLaunches(int firstLaunchIndex, int secondLaunchIndex) {
        List<Long> launchesIdsList = getLaunchesIDsFromResponse();
        if (firstLaunchIndex > 0 && firstLaunchIndex
                <= launchesIdsList.size() && secondLaunchIndex > 0 && secondLaunchIndex <= launchesIdsList.size()) {
            long launchId1 = launchesIdsList.get(firstLaunchIndex - 1);
            long launchId2 = launchesIdsList.get(secondLaunchIndex - 1);
            response = client.getLaunchesToCompare(launchId1, launchId2);
        } else {
            throw new IllegalArgumentException("One or both indexes go out of the length of the list!");
        }
    }

    @When("^I get launch ID from response$")
    public Long getLaunchIDFromResponse(int launchIndex) {
        List<Long> launchesIdsList = getLaunchesIDsFromResponse();
        Long launchId;
        if (launchIndex > 0 && launchIndex <= launchesIdsList.size()) {
            launchId = launchesIdsList.get(launchIndex - 1);
        } else {
            throw new IllegalArgumentException("Index goes out of the length of the list!");
        }
        return launchId;
    }

    @When("^I send POST request with (valid|invalid) body to analyze launch (\\d+)$")
    public void iSendPostRequestToAnalyzeLaunchLaunchIndex(String requestBodyType, int launchIndex) {
        long launchId = getLaunchIDFromResponse(launchIndex);
        AnalyzeLaunchRQ analyzeLaunchRQ = new AnalyzeLaunchRQ();
        switch (requestBodyType) {
            case "valid" -> analyzeLaunchRQ = LaunchAnalysisFactory.analyzeLaunchToInvestigate(launchId);
            case "invalid" -> analyzeLaunchRQ = LaunchAnalysisFactory.invalidAnalyzeLaunch(launchId);
        }
        response = client.createLaunchAnalysis(analyzeLaunchRQ);
    }

    @Then("^I see response message is '(.*)'$")
    public void seeResponseMessageIs(String message) {
        responseMessage = parseResponse(response.getBody(), ResponseMessage.class);
        Assertions.assertTrue(responseMessage.getMessage().replaceAll("[?\\[\\]\"'\n;,$:]", "")
                .trim().matches(message.replaceAll("[?\\[\\]\"'\n;,$:]", "")));
    }

    @When("^I send POST request to analyze launch by ID (\\d+)$")
    public void sendPostRequestToAnalyzeLaunchByID(long launchId) {
        response = client.createLaunchAnalysis(LaunchAnalysisFactory.analyzeLaunchToInvestigate(launchId));
    }

    @Given("^I send GET request to get all filters for project$")
    public void sendGETRequestToGetAllFiltersForProject() {
        client = new Client();
        response = client.getFilters();
        responseFiltersData = parseResponse(response.getBody(), ResponseFiltersData.class);
    }

    @When("^I get filters IDs from response$")
    public List<Long> getFiltersIDsFromResponse() {
        List<Long> filtersIds = new ArrayList<>();
        Arrays.stream(responseFiltersData.getContent())
                .map(FiltersContent::getId)
                .forEach(filtersIds::add);
        return filtersIds;
    }

    @When("^I send PUT request to update filter with index (\\d+)$")
    public void sendPUTRequestToUpdateFilterWithIndex(int filterIndex) {
        List<Long> filtersIdsIdsList = getFiltersIDsFromResponse();
        if (filterIndex > 0 && filterIndex <= filtersIdsIdsList.size()) {
            long filterId = filtersIdsIdsList.get(filterIndex - 1);
            response = client.updateFilter(FiltersFactory.filterByNumber(filterId), filterId);
        } else {
            throw new IllegalArgumentException("Index goes out of the length of the list!");
        }
    }

    @When("^I send PUT request to update launch with index (\\d+)$")
    public void iSendPUTRequestToUpdateLaunchWithIndex(int launchIndex) {
        long launchId = getLaunchIDFromResponse(launchIndex);
        LaunchesContent originalContent = getLaunchWithIndexFromResponse(launchIndex);
        LaunchesContent updatedContent = LaunchFactory.validLaunchWithUpdatedDescription(originalContent);
        response = client.updateLaunch(updatedContent, launchId);
    }

    @When("^I get launch with index (\\d+) from response$")
    public LaunchesContent getLaunchWithIndexFromResponse(int launchIndex) {
        List<LaunchesContent> launches;
        launches = List.of(parseResponse(response.getBody(), ResponseLaunchesData.class).getContent());
        return launches.get(launchIndex);
    }

    @When("^I delete launch with index (\\d+)$")
    public void iDeleteLaunchWithIndex(int launchIndex) {
        long launchId = getLaunchIDFromResponse(launchIndex);
        Launches launches = new Launches();
        launches.setIdList(Collections.singletonList(launchId));
        response = client.deleteLaunches(launches);
    }

    @When("^I send PUT request to update launch with index (\\d+) with invalid data$")
    public void sendPUTRequestToUpdateLaunchWithIndexWithInvalidData(int launchIndex) {
        long launchId = getLaunchIDFromResponse(launchIndex);
        LaunchesContent originalContent = getLaunchWithIndexFromResponse(launchIndex);
        LaunchesContent updatedContent = LaunchFactory.launchWithInvalidMode(originalContent);
        response = client.updateLaunch(updatedContent, launchId);
    }

    @Then("^I see ID of deleted launch with index (\\d+) in the response in successfully deleted section$")
    public void seeDeletedLaunchIDInTheResponseSuccessfullySection(int launchIndex) {
        List<Long> deletedLaunchId = new ArrayList<>();
        deletedLaunchId.add(getLaunchIDFromResponse(launchIndex));
        responseDeletedLaunchData = parseResponse(response.getBody(), ResponseDeletedLaunchData.class);
        Assertions.assertEquals(deletedLaunchId, List.of(responseDeletedLaunchData.getSuccessfullyDeleted()));
    }

    @When("^I delete launch with ID (\\d+)$")
    public void deleteLaunchWithID(long launchId) {
        Launches launches = new Launches();
        List<Long> launchIds = new ArrayList<>();
        launchIds.add(launchId);
        launches.setIdList(launchIds);
        response = client.deleteLaunches(launches);
    }

    @Then("^I see ID of deleted launch with ID (\\d+) in the response in not found section$")
    public void seeIdOfDeletedLaunchWithIDInTheResponseNotFoundSection(long launchId) {
        List<Long> deletedLaunchId = new ArrayList<>();
        deletedLaunchId.add(launchId);
        responseDeletedLaunchData = parseResponse(response.getBody(), ResponseDeletedLaunchData.class);
        Assertions.assertEquals(deletedLaunchId, List.of(responseDeletedLaunchData.getNotFound()));
    }
}
