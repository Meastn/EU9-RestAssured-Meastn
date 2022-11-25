package com.cybertek.day5;

import com.cybertek.utilities.HRTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class ORDSHamCrestTest extends HRTestBase {

    @DisplayName("GET request to Employees IT_PROG endpoint and chaining")
    @Test
    public void test1() {
    // send a GET request to employees endpoint with querry parameter job_id IT_PROG
    // verify each job_id is IT_PROG
    // verify first names the first 5 names exactly matches (find a proper method to compare two lists)
    // verify email without checking order (provide email in different order, just make sure it has some email(s)

        List<String> names = Arrays.asList("Alexander","Bruce", "David","Valli","Diana");
        given().accept(ContentType.JSON)
                .and()
                .   queryParam("q","{\"job_id\":\"IT_PROG\"}")
                .when()
                    .get("/employees")
                .then()
                    .statusCode(200)
                .body("items.job_id", everyItem(equalTo("IT_PROG")))
                .body("items.first_name", containsInRelativeOrder("Alexander","Bruce", "David","Valli","Diana"))//contains with order
                .body("items.email",containsInAnyOrder("VPATABAL","DAUSTIN","BERNST","AHUNOLD","DLORENTZ")) // contains without order
                .body("items.first_name", equalToObject(names));//equality of the list assertion

    }
}
