package com.cybertek.day5;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
public class HamCrestAPITest {
    /*
           given the accept type is Json
           And path param id is 15
           When user sends  a get request to spartan/{id}
           Then status code is 200
           And content type is Json
           And Json data has following
               "id": 15,
               "name":"Meta",
               "gender": "Female",
               "phone": 1938695106
            */
    @DisplayName( "OneSpartan with Hamcrest and chaining")
    @Test
    public void test1(){

    given()
        .accept(ContentType.JSON)
        .and().pathParam("id", 15)
    .when()
         .get("http://54.172.101.13:8000/api/spartans/{id}")
   .then()
         .statusCode(200)
         .and()
         .contentType("application/json")
         .and()
         .body("id",equalTo(15),
              "name", is("Meta"),
              "gender", is("Female"),
              "phone", is(1938695106));
    }

    @DisplayName("CBTraining teacher request with chaining and matches")
    @Test
    public void teacherData(){
        given()
           .accept(ContentType.JSON)
           .and()
           .pathParam ("id", 10423)
           .when()
           .get("http://api.training.cydeo.com/teacher/{id}")
           .then()
           .statusCode(200)
                .contentType("application/json;charset=UTF-8")
                .and()
                .header("Content-Length", is ("236"))
                .and()
                .header("Date", notNullValue())
                .and()
                .body("teachers[0].firstname", is ("Alexanter"))
                .and()
                .body("teachers[0].lastname", is("Syrup"))
                .and()
                .body("techers[0].gender", is("Male"));
    }

}
