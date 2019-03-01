package com.vaznoe.nasa.helper;

import com.vaznoe.nasa.property.WebServiceNasaTestProperty;
import groovy.util.logging.Slf4j;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * @author vaznoe
 * Date: 2/15/19
 */
@Slf4j
@Component
public class NasaTestHelper {

    @Autowired
    private WebServiceNasaTestProperty webServiceNasaTestProperty;

    @Step("Get search: {paramsMap}")
    public Response search(Map<String, Object> paramsMap) {
        return doGet(webServiceNasaTestProperty.getSearch(), paramsMap);
    }

    @Step("Get search endpoint without params")
    public Response search() {
        return doGet(webServiceNasaTestProperty.getSearch());
    }

    private Response doGet(String endpoint, Map<String, Object> paramsMap) {
        Response response = given()
                .contentType(ContentType.JSON)
                .params(paramsMap)
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        return response;
    }

    private Response doGet(String endpoint) {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        return response;
    }
}
