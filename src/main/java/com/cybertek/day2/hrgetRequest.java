package com.cybertek.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.client.HttpResponseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class hrgetRequest {

    @BeforeAll
    // save baseURI inside a variable
    public static void init(){
        baseURI = "http://44.195.19.167:8000";

    }

    @DisplayName("GET request to /regions")
    @Test
    public void test1(){
        Response response = RestAssured.get("/regions");
        System.out.println(response.statusCode());
    }

    /*
    Given Accept type is application/json
    When user sends request to /regions/2
    Then response status code must be 200
    And content typ equals to application/json
    And response body contains Americas
     */

    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON).when().get("regions/2");

        assertEquals(200, response.statusCode() );
        assertEquals("application/json", response.contentType());

        response.prettyPrint();
        assertEquals(true, response.body().asString().contains("Americas"));
    }

    /*
    Given accept type application/xml
    When user send GET request to api/spartans/10 end point
    Then status code must be 406
    And response content TYPE must be application/xml
     */

    @Test
    public void test4(){
        Response response = given().accept(ContentType.XML).when().get("/api/spartans/");
                assertEquals(406, response.statusCode());

        assertEquals("application/xml", response.contentType());

    }
}
