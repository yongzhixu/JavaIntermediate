package com.example.jvm.lab.JVM_Architecture;
// A Java program to demonstrate working
// of a Class type object created by JVM
// to represent .class file in memory.

import java.lang.reflect.Field;
import java.lang.reflect.Method;

// Java code to demonstrate use
// of Class object created by JVM
public class Loading {

    /**
     * Loading: The Class loader reads the “.class” file, generate the corresponding binary data and save it in the method area.
     * For each “.class” file, JVM stores the following information in the method area.
     *
     * - The fully qualified name of the loaded class and its immediate parent class.
     * - Whether the “.class” file is related to Class or Interface or Enum.
     * - Modifier, Variables and Method information etc.
     *
     * After loading the “.class” file, JVM creates an object of type Class to represent this file in the heap memory.
     * Please note that this object is of type Class predefined in java.lang package.
     * These Class object can be used by the programmer for getting class level information like the name of the class,
     * parent name, methods and variable information etc.
     * To get this object reference we can use getClass() method of Object class.
     */
    // A sample class whose information
// is fetched above using its Class object.
    static class Student {
        private String name;
        private int roll_No;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getRoll_no() {
            return roll_No;
        }

        public void setRoll_no(int roll_no) {
            this.roll_No = roll_no;
        }
    }

    public static void main(String[] args) {
        Student s1 = new Student();

        // Getting hold of Class
        // object created by JVM.
        Class c1 = s1.getClass();

        // Printing type of object using c1.
        System.out.println(c1.getName());

        // getting all methods in an array
        Method m[] = c1.getDeclaredMethods();
        for (Method method : m)
            System.out.println(method.getName());

        // getting all fields in an array
        Field f[] = c1.getDeclaredFields();
        for (Field field : f)
            System.out.println(field.getName());


        /**
         * Note: For every loaded “.class” file, only one object of the class is created.
         */
        Student s2 = new Student();
// c2 will point to same object where
// c1 is pointing
        Class c2 = s2.getClass();
        System.out.println(c1 == c2); // true
    }
}
