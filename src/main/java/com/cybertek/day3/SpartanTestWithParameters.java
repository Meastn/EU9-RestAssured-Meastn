package com.cybertek.day3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpartanTestWithParameters {
    @BeforeAll
    public static void init() {
        baseURI = "http://44.195.19.167:8000/";

    }

    @DisplayName("Get request to /api/spartans/{id} with ID 156")
    @Test
    public void test1() {
          /*
    Given accept type is Json
    And Id parameter valuse is 156
    When user sends GET request to /api/spartans/{id}
    Then response status code should be 200
    And response content-type: application/json
    And "Blythe" should be in response payload
     */
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 156)
                .when()
                .get("/api/spartans/{id}");
        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("John"));
    }

    @DisplayName("Get Request to /api/spartans/{id} Negative Test")
    @Test
    public void test2() {
        /*
        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json
        And "Not Found" message should be in response payload
         */
        //Given accept type is Json
        Response response = given().accept(ContentType.JSON)
                //And Id parameter value is 500
                .and().pathParam("id",1200)
                .when()
                //When user sends GET request to /api/spartans/{id}
                .get("/api/spartans/{id}");
        //  Then response status code should be 404
        assertEquals(404, response.statusCode());
        //And response content-type: application/json
        assertEquals("application/json", response.contentType());
        //And "Not Found" message should be in response payload
        assertTrue(response.body().asString().contains("Not Found"));

    }

    /*
    Given accept type is Json
    And query parameters are :
    gender | Female
    nameContains | e
    When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */

    @DisplayName("Get Request to /api/spartans/search with Query parameters")
    @Test
    public void test3(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("nameContains","es")
                .and().queryParam("gender","Female")
                .when()
                .get("/api/spartans/search");
        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("jessica"));
    }

    @DisplayName("Get Request to /api/spartans/search with Query parameters and write to a Hashmap")
    @Test
    public void test4(){
        Map <String, Object> queryMap= new HashMap<>();
        queryMap.put("nameContains","e");
        queryMap.put("gender","Female");

        Response response = given().accept(ContentType.JSON)
                .and().queryParams(queryMap)
                //.and().queryParam("gender","Female")
                .when()
                .get("/api/spartans/search");
        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("jessica"));

        System.out.println(queryMap);
    }


}
