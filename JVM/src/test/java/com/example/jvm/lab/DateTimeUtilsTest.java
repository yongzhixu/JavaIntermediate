package com.example.jvm.lab;

import com.example.jvm.lab.nativemethod.DateTimeUtils;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import static org.junit.jupiter.api.Assertions.*;


class DateTimeUtilsTest {

    @BeforeTestClass
    public static void setUpClass() {
        // .. load other dependent libraries
        System.loadLibrary("nativedatetimeutils");
    }

    @Test
    public void givenNativeLibsLoaded_thenNativeMethodIsAccessible() {
        DateTimeUtils dateTimeUtils = new DateTimeUtils();
        System.out.println("System time is : " + dateTimeUtils.getSystemTime());
        assertNotNull(dateTimeUtils.getSystemTime());
    }
}