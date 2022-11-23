package com.cybertek.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpartanGetRequest {

    String baseURL = "http://44.195.19.167:8000/";

    // Given Accept type application/json
    // When user send GET request to api/spartans end point
    // Then status code must be 200
    // And response Content Type must be application/json
    // And response body should include spartan result
    @Test
    public void test2() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseURL + "api/spartans");

        System.out.println("response.statusCode= " + response.statusCode());
        System.out.println("response.contentType= " + response.contentType());

        Assertions.assertEquals(response.statusCode(),200);
        Assertions.assertEquals(response.contentType(), "application/json");
    }

    @Test
    public void getOneSpartan(){
        Response response = RestAssured.given().accept(ContentType.JSON).
                when().get(baseURL + "api/spartans");

        // verify status code is 200
        Assertions.assertEquals(200, response.statusCode());

        //verify content type is application/json
        Assertions.assertEquals("application/json",response.contentType());

        // verify response body  includes spartan result
        Assertions.assertTrue(response.body().asString().contains("Severus"));
    }


    @Test
    public void dest3(){
        Response response = RestAssured.when().get(baseURL + "api/hello");

        //verify status code is 200
        Assertions.assertEquals(200, response.statusCode());

        //verify content type
        Assertions.assertEquals("text/plain;charset=UTF-8", response.contentType());

        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));
        System.out.println( "response.header(\"Content-Length\")= " + response.header("Content-Length"));
        System.out.println(response.header("Date"));
        Assertions.assertEquals("17", response.header("Content-Length"));
        Assertions.assertEquals("Hello from Sparta", response.body().asString());
    }
}
