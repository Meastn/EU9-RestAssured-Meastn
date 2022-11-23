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

public class SpartanTestWithPath {

    @BeforeAll
    public static void init (){
        baseURI = "http://44.195.19.167:8000/";
    }

    /*
     Given accept type is Json
     And path parameter id is 10
     When user sends GET request to /api/spartans/{id}
         Then response status code should be 200
         And response content-type: application/json
     And response payload values should be
        id is 156,
        name is "John",
        gender is "Male",
        phone is 12334987347
      */
    @DisplayName("Get one spartan with path method")
    @Test
    public  void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",156)
                .when()
                .get("/api/spartans/{id}");
        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        int spartanID =response.path("id");
        String spartanName = response.path("name");
        String spartanGender = response.path("gender");
        long spartanPhone = response.path("phone");

        System.out.println("spartanID = " + spartanID);
        System.out.println("spartanName = " + spartanName);
        System.out.println("spartanGender = " + spartanGender);
        System.out.println("spartanPhone = " + spartanPhone);

        assertEquals(156, spartanID);
        assertEquals("John", spartanName);
        assertEquals("Male", spartanGender);
        assertEquals(12334987347L,spartanPhone);



    }
}
