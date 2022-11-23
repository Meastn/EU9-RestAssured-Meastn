package com.cybertek.day5;

import org.codehaus.groovy.transform.BaseScriptASTTransformation;
import org.hamcrest.MatcherAssert.*;
import org.hamcrest.Matchers;
import org.hamcrest.Matchers.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class HamCrestMatchersIntro {

    @Test
    public void test1(){

        assertThat(5+6, is(11));
        assertThat(5+5, equalTo(10));
        assertThat(5+5, is(equalTo(10))); // just for readability

        assertThat(5+5, not(11));

        assertThat(5+5, greaterThan(9));

        String text = "B22 is learning Hamcrest";
        // check if the text starts with B22
        assertThat(text, startsWith("B22"));
        // now do it in case insensitive manner
        assertThat(text, startsWithIgnoringCase("B22"));

        // text ends with
        assertThat(text, endsWith("rest"));

        // check if text contains learning
        assertThat(text, containsString("learning"));
        assertThat(text, containsStringIgnoringCase("LEARNING"));
    }

    @DisplayName("HamCrest for Collectior")
    @Test
    public void test2(){
        List<Integer> numList = Arrays.asList(1,4,5,6,32,54,66,77,45,23);

        // check size of Array
        assertThat(numList, hasSize(10));
        //check if this list has item with a value 77
        assertThat(numList, hasItem(77));
        // check if this list has items 77, 54, 23
        assertThat(numList, hasItems(77,54,23));
        // check if all numbers are greater than 5
        assertThat (numList, everyItem(greaterThan(300)));
    }
}
