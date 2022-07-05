package com.example.jvm.lab.gc;

public class gc_concept {

    public static void main(String[] args) {

        /**
         * 1. Unreachable objects: An object is said to be unreachable if it doesn’t contain any reference to it.
         * Also, note that objects which are part of the island of isolation are also unreachable.
         */
        Integer i = new Integer(4);
        // the new Integer object is reachable  via the reference in 'i'
        i = null;
        // the Integer object is no longer reachable.
        /**
         *  Eligibility for garbage collection: An object is said to be eligible for GC(garbage collection) if it is unreachable.
         *  After i = null, integer object 4 in the heap area is suitable for garbage collection in the above image.
         */

    }
}
