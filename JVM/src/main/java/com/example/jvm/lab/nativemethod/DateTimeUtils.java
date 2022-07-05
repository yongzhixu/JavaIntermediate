package com.example.jvm.lab.nativemethod;

/**
 * call c++ native Methods from java
 */
public class DateTimeUtils {
    public native String getSystemTime();

    static {
        System.loadLibrary("nativedatetimeutils");
    }
}
