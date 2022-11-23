package com.cybertek.day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrdsApiTestsWithParameters {

    @BeforeAll
    public static void init(){
        baseURI = "http://44.195.19.167:1000";
    }

    /*
    Given accept type is Json
    And  parameters : q = {"region_id:":2}
    When user sends GET request to /countries
        Then response status code should be 200
        And response content-type: application/json
        And "United States of America" should be in response payload
     */
    @DisplayName("Get Request to /countries with Query parameters")
    @Test
    public void test5(){
        Response response= given().accept(ContentType.JSON)
                .and().queryParam("q","{\"region_id:\":2}")
                .log().all()
                .when()
                .get("/countries");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("United States of America"));
    }
}
