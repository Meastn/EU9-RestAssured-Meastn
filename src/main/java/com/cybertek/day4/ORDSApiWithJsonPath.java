package com.cybertek.day4;

import com.cybertek.utilities.HRTestBase;
import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ObjectOutput;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class ORDSApiWithJsonPath extends HRTestBase {

    @DisplayName("GET request to Countries")
    @Test
    public void test(){
        Response response = get("/countries");

        // get the second country_name

        JsonPath jsonPath= response.jsonPath();

        String secondCountryName = jsonPath.getString("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        // get all country ids
        // items.country_id

        List<Object> allCountryIds = jsonPath.getList("items.country_id");
        System.out.println(allCountryIds);

        // GET all country_names where region id is equal to 2

        List <String> countryNameWithRegionID2 = jsonPath.getList("items.findAll {it.region_id==2}.country_id");
        System.out.println(countryNameWithRegionID2);

    }

@DisplayName("GEÜ request / employees with query params")
    @Test
    public void test2(){
    Response response = given().queryParam("limit", 107)
            .when().get("/employees");

    // get me all email of employees who is working as IT_PROG

    JsonPath jsonPath = response.jsonPath();

    List<Object> employeeITProgs = jsonPath.getList("items.findAll{it.job_id==\"IT_PROG\"}.first_name");

    System.out.println("employeeITProgs = " + employeeITProgs);

    // GET first name of employees who are making more than 1000
    List <String> empNames = jsonPath.getList("items.findAll{it.salary>12000}.first_name");
    System.out.println("empNames making more than 10K= " + empNames);
    
    //GET the maximum salary first name
    String empWithMaxSalary = jsonPath.get("items.max{it.salary}.first_name");
    System.out.println("empWithMaxSalary = " + empWithMaxSalary);

}


}
