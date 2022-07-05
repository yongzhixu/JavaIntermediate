package com.example.jvm.lab.nativemethod;

public class NumberListProxy {

    static {
        System.loadLibrary("NumberList");
    }
    NumberListProxy()  {
        initCppSide();
    }
    public native void addNumber(int n);
    public native int size();
    public native int getNumber(int i);
    private native void initCppSide();
    private int numberListPtr_;
    // NumberList*
}
