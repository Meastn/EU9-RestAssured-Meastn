package com.cybertek.day4;

import com.cybertek.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class ORDSApiTestWithPath  extends HRTestBase {

    @DisplayName("GET request to countries with Path method")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .queryParam("q", "{\"region_id\":2}")
                .when()
                .get("/countries");

                assertEquals(200, response.statusCode());
                //GET limit parameter result and print
        System.out.println("response.path(limit) = " + response.path("limit"));
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));
        String firstCountryName= response.path("items[0].country_name");
        String firstCountdyID = response.path("items[0].country_id");

        System.out.println("firstCountry = " + firstCountryName);
        System.out.println("firstCountdyID = " + firstCountdyID);

        String seconCountryName = response.path("items[1].country_name");
        String secondCountryID = response.path("items[1].country_id");

        System.out.println("seconCountryName = " + seconCountryName);
        System.out.println("secondCountryID = " + secondCountryID);

        String thirdCountryHref = response.path("items[2].links[0].href");
        System.out.println("thirdCountryHREF = " + thirdCountryHref);


    }
    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON)
                .queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when()
                .get("/employees");
        List<String> allJobIDs = new ArrayList<>(),
                allJobsId = response.path("items.job_id");
        for (String jobId : allJobsId) {
            System.out.println("jobId = " + jobId);
            assertEquals("IT_PROG", jobId);
        }

    }
}
