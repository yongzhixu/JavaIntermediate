package com.example.jvm.lab.gc;

// reference https://www.geeksforgeeks.org/island-of-isolation-in-java/
public class IslandofIsolation {
    IslandofIsolation i;

    // Method 1
    // Main driver method
    public static void main(String[] args)
    {

        // Creating object of class inside main() method
        IslandofIsolation t1 = new IslandofIsolation();
        IslandofIsolation t2 = new IslandofIsolation();

        // Object of t1 gets a copy of t2
        t1.i = t2;

        // Object of t2 gets a copy of t1
        t2.i = t1;

        /**
         * Both the objects have external references t1 and t2.
         * t1 = null : Both the objects can be reached via t2.i and t2 respectively.
         * t2 = null: No way to reach any of the objects.
         *
         */
        // Till now no object eligible
        // for garbage collection
        t1 = null;

        // Now two objects are eligible for
        // garbage collection
        t2 = null;

        // Calling garbage collector
        System.gc();
        System.out.println("System.gc(); method called");
    }

    // Method 2
    // overriding finalize() Method
    @Override protected void finalize() throws Throwable
    {
        // Print statement
        System.out.println("Finalize method called");
    }
}
