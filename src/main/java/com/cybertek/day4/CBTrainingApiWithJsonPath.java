package com.cybertek.day4;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class CBTrainingApiWithJsonPath {

    @BeforeAll
    public static void init(){

        baseURI = "https://api.training.cydeo.com";

    }

    @DisplayName("Get  Request to individual student")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("admissionNo", 123)
                .when().get("/students/{admissionNo}");
        //send a get request to a student id 23401 as path parameter and accept header application/json
        // verify status code 200 /content type/Content-Encoding= gzip
        //verify Date header exists
        //assert that
        /*
        firstname Vera
        batch 14
        section 12
        emailAddress aaa@gmail.com
        companyName Cybertek
        state IL
        zipCode 60606

        using JsonPath
         */

    }

}
