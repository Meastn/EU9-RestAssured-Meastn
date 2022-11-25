package com.cybertek.day5;

import com.cybertek.utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class JsonToJavaTest extends SpartanTestBase {

    @DisplayName("GEt one Spartan and deserialize to Map")
    @Test
    public  void oneSpartanToMap(){
        Response response = given().pathParam("id", 156)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).extract().response();

        // get the json and convert it to map (deserialize)
        Map <String, Object> jsonMap = response.as(Map.class);
        // after we got the map we can use hamcrest or junit assertions
        System.out.println(jsonMap.toString());
        String actualName = (String) jsonMap.get("name");
        assertThat(actualName, is("John"));
    }

    @DisplayName("GET all spartans to JAVA data structure")
    @Test
    public void allSpantansToMap (){
        Response response = get("/api/spartans").then().statusCode(200).extract().response();
//we need to convert json to java and deserialize

        List <Map<String,Object>> jsonList = response.as(List.class);

        System.out.println("jsonList.get(1).get(\"name\") = " + jsonList.get(0).get("name"));

        Map <String, Object> spartan3 = jsonList.get(2);
        System.out.println(spartan3);
    }
}
