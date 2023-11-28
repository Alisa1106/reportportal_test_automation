package com.epam.reportportal.business.api;

import com.epam.reportportal.business.models.AnalyzeLaunchRQ;
import com.epam.reportportal.business.models.Dashboards;
import com.epam.reportportal.business.models.Launches;
import com.epam.reportportal.business.models.Reponse_filters_data.FiltersContent;
import com.epam.reportportal.business.models.response_launches_data.LaunchesContent;
import com.google.gson.Gson;
import io.qameta.allure.Step;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.remote.http.HttpMethod;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

public class Client {

    private final String CONTENT_TYPE = "Content-Type";
    private final String AUTHORIZATION = "Authorization";
    private final String BEARER = "Bearer ";
    private final String ACCEPT = "*/*";
    private String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MDEyNjg4OTMsInVzZXJfbmFtZSI6ImFoYXIiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiX25STzhqcXNLcU5tblJILVE3VnhrWlJuTFFVIiwiY2xpZW50X2lkIjoidWkiLCJzY29wZSI6WyJ1aSJdfQ.Y9lW5ihQD-KVamCYOrHiW2FnYMiCy_99LWPo6wk7zZU";
    private final HttpClient httpClient = HttpClientBuilder.create().build();

    private Response sendRequest(String url, String json, HttpMethod method) {
        HttpEntityEnclosingRequestBase request;
        try {
            if (method == HttpMethod.POST) {
                request = new HttpPost(new URI(url));
            } else if (method == HttpMethod.DELETE) {
                request = new HttpDeleteWithBody(new URI(url));
            } else if (method == HttpMethod.PUT) {
                request = new HttpPut(new URI(url));
            } else {
                throw new IllegalArgumentException(String.format("Invalid HTTP method: %s", method));
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        request.setHeader(CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
        request.setHeader(AUTHORIZATION, BEARER + token);
        request.setHeader(HttpHeaders.ACCEPT, ACCEPT);
        StringEntity entity = new StringEntity(json, StandardCharsets.UTF_8);
        request.setEntity(entity);
        HttpResponse response;
        try {
            response = httpClient.execute(request);
            HttpEntity responseEntity = response.getEntity();
            String responseBody = EntityUtils.toString(responseEntity);
            return new Response(response.getStatusLine().getStatusCode(), responseBody);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Response sendGetRequest(String url) {
        HttpGet request;
        try {
            request = new HttpGet(new URI(url));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        request.setHeader(CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
        request.setHeader(AUTHORIZATION, BEARER + token);
        request.setHeader(HttpHeaders.ACCEPT, ACCEPT);
        HttpResponse response;
        try {
            response = httpClient.execute(request);
            HttpEntity responseEntity = response.getEntity();
            String responseBody = EntityUtils.toString(responseEntity);
            return new Response(response.getStatusLine().getStatusCode(), responseBody);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Step("Send POST request to create demo data")
    public Response createDemoData(Dashboards dashboards) {
        String url = new UrlBuilder().demoDataUri();
        String json = new Gson().toJson(dashboards);
        return sendRequest(url, json, HttpMethod.POST);
    }

    @Step("Send DELETE request to delete launch")
    public Response deleteLaunches(Launches launches) {
        String url = new UrlBuilder().launchUri();
        String json = new Gson().toJson(launches);
        return sendRequest(url, json, HttpMethod.DELETE);
    }

    @Step("Send GET request to get launches")
    public Response getLaunches() {
        String url = new UrlBuilder().launchUri();
        return sendGetRequest(url);
    }

    @Step("Send GET request to get launches by filter '{filterName}' with value '{filterValue}'")
    public Response getLaunchesByFilter(String filterName, String filterValue) {
        String url = new UrlBuilder().launchByFilterUri(filterName, filterValue);
        return sendGetRequest(url);
    }

    @Step("Send GET request to compare launches by ID '{firstLaunchId}' with value '{secondLaunchId}'")
    public Response getLaunchesToCompare(long firstLaunchId, long secondLaunchId) {
        String url = new UrlBuilder().launchToCompareUri(firstLaunchId, secondLaunchId);
        return sendGetRequest(url);
    }

    @Step("Send POST request to start launch analysis")
    public Response createLaunchAnalysis(AnalyzeLaunchRQ analyzeLaunchRQ) {
        String url = new UrlBuilder().launchAnalysisUri();
        String json = new Gson().toJson(analyzeLaunchRQ);
        return sendRequest(url, json, HttpMethod.POST);
    }

    @Step("Send GET request to get filters")
    public Response getFilters() {
        String url = new UrlBuilder().filtersUri();
        return sendGetRequest(url);
    }

    @Step("Send PUT request to update filter with ID {filterId}")
    public Response updateFilter(FiltersContent filtersContent, long filterId) {
        String url = new UrlBuilder().filtersByIdUri(filterId);
        String json = new Gson().toJson(filtersContent);
        return sendRequest(url, json, HttpMethod.PUT);
    }

    @Step("Send PUT request to update launch with ID {launchId}")
    public Response updateLaunch(LaunchesContent launchesContent, long launchId) {
        String url = new UrlBuilder().launchByIdToUpdateUri(launchId);
        String json = new Gson().toJson(launchesContent);
        return sendRequest(url, json, HttpMethod.PUT);
    }
}
