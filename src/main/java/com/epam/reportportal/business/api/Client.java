package com.epam.reportportal.business.api;

import com.epam.reportportal.business.models.AnalyzeLaunchRQ;
import com.epam.reportportal.business.models.Dashboards;
import com.epam.reportportal.business.models.Launches;
import com.epam.reportportal.business.models.Reponse_filters_data.FiltersContent;
import com.epam.reportportal.business.models.response_launches_data.LaunchesContent;
import com.epam.reportportal.core.common.utils.PropertyReader;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Client {

    private final String CONTENT_TYPE = "Content-Type";
    private final String AUTHORIZATION = "Authorization";
    private final String BEARER = "Bearer ";
    private final String ACCEPT = "*/*";
    private UrlBuilder urlBuilder;
    private String token = System.getProperty("token");

    private final RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri(PropertyReader.getProperties("api", "base.url"))
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    @Step("Send POST request to create demo data")
    public Response createDemoData(Dashboards dashboards) {
        urlBuilder = new UrlBuilder();
        return given()
                .spec(requestSpecification)
                .accept(ACCEPT)
                .header(CONTENT_TYPE, ContentType.JSON)
                .header(AUTHORIZATION, BEARER + token)
                .body(dashboards)
                .post(urlBuilder.demoDataUri());
    }

    @Step("Send DELETE request to delete launch")
    public Response deleteLaunches(Launches launches) {
        urlBuilder = new UrlBuilder();
        return given()
                .spec(requestSpecification)
                .accept(ACCEPT)
                .header(CONTENT_TYPE, ContentType.JSON)
                .header(AUTHORIZATION, BEARER + token)
                .body(launches)
                .delete(urlBuilder.launchUri());

    }

    @Step("Send GET request to get launches")
    public Response getLaunches() {
        urlBuilder = new UrlBuilder();
        return given()
                .spec(requestSpecification)
                .accept(ACCEPT)
                .header(CONTENT_TYPE, ContentType.JSON)
                .header(AUTHORIZATION, BEARER + token)
                .get(urlBuilder.launchUri());
    }

    @Step("Send GET request to get launches by filter '{filterName}' with value '{filterValue}'")
    public Response getLaunchesByFilter(String filterName, String filterValue) {
        urlBuilder = new UrlBuilder();
        return given()
                .spec(requestSpecification)
                .accept(ACCEPT)
                .header(CONTENT_TYPE, ContentType.JSON)
                .header(AUTHORIZATION, BEARER + token)
                .get(urlBuilder.launchByFilterUri(filterName, filterValue));
    }

    @Step("Send GET request to compare launches by ID '{firstLaunchId}' with value '{secondLaunchId}'")
    public Response getLaunchesToCompare(long firstLaunchId, long secondLaunchId) {
        urlBuilder = new UrlBuilder();
        return given()
                .spec(requestSpecification)
                .accept(ACCEPT)
                .header(CONTENT_TYPE, ContentType.JSON)
                .header(AUTHORIZATION, BEARER + token)
                .get(urlBuilder.launchToCompareUri(firstLaunchId, secondLaunchId));
    }

    @Step("Send POST request to start launch analysis")
    public Response createLaunchAnalysis(AnalyzeLaunchRQ analyzeLaunchRQ) {
        urlBuilder = new UrlBuilder();
        return given()
                .spec(requestSpecification)
                .accept(ACCEPT)
                .header(CONTENT_TYPE, ContentType.JSON)
                .header(AUTHORIZATION, BEARER + token)
                .body(analyzeLaunchRQ)
                .post(urlBuilder.launchAnalysisUri());
    }

    @Step("Send GET request to get filters")
    public Response getFilters() {
        urlBuilder = new UrlBuilder();
        return given()
                .spec(requestSpecification)
                .accept(ACCEPT)
                .header(CONTENT_TYPE, ContentType.JSON)
                .header(AUTHORIZATION, BEARER + token)
                .get(urlBuilder.filtersUri());
    }

    @Step("Send PUT request to update filter with ID {filterId}")
    public Response updateFilter(FiltersContent filtersContent, long filterId) {
        urlBuilder = new UrlBuilder();
        return given()
                .spec(requestSpecification)
                .accept(ACCEPT)
                .header(CONTENT_TYPE, ContentType.JSON)
                .header(AUTHORIZATION, BEARER + token)
                .body(filtersContent)
                .put(urlBuilder.filtersByIdUri(filterId));
    }

    @Step("Send PUT request to update launch with ID {launchId}")
    public Response updateLaunch(LaunchesContent launchesContent, long launchId) {
        urlBuilder = new UrlBuilder();
        return given()
                .spec(requestSpecification)
                .accept(ACCEPT)
                .header(CONTENT_TYPE, ContentType.JSON)
                .header(AUTHORIZATION, BEARER + token)
                .body(launchesContent)
                .put(urlBuilder.launchByIdToUpdateUri(launchId));
    }
}
