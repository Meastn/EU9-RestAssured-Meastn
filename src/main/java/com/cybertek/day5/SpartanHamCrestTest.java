package com.cybertek.day5;

import com.cybertek.utilities.HRTestBase;
import com.cybertek.utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SpartanHamCrestTest extends SpartanTestBase {

    @DisplayName("GET spartan/search and chaining together")
    @Test
    public void test1() {
      List <String> names=  given().accept(ContentType.JSON)
                .and()
                .queryParams("nameContains", "j",
                                    "gender","Male")
                .when()
                    .get("/api/spartans/search")
                .then()
                    .statusCode(200)
                    .and()
                    .body("totalElement", is(3))
                    .extract().response().jsonPath().getList("content.name");

        System.out.println(names);


    }

    @DisplayName("GET spartan/search and chaining together with status code")
    @Test
    public void test2() {
        List <String> names=  given().accept(ContentType.JSON)
                .and()
                .queryParams("nameContains", "j",
                        "gender","Male")
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .and()
                .body("totalElement", greaterThanOrEqualTo(3))
                .extract().response().jsonPath().getList("content.name");

        System.out.println(names);


    }
}
