package com.example.jvm.lab.nativemethod;

public class NativeDemo {
    public native String encryptData (String inputdata);
    static
    {
        System.loadLibrary ("nativedemo");   /* lowercase of classname! */
    }
    public static void main (String[] args)
    {
        NativeDemo demo = new NativeDemo ();
        System.out.println("Encrypted data is " + demo.encryptData ("This is Edubca"));
    }
}
