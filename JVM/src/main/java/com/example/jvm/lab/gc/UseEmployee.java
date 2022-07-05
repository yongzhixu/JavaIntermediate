package com.example.jvm.lab.gc;

public class UseEmployee {

    // Java Program to count number
// of employees working
// in a company

    static class Employee {

        private int ID;
        private String name;
        private int age;
        private static int nextId = 1;
        // it is made static because it
        // is keep common among all and
        // shared by all objects

        public Employee(String name, int age) {
            this.name = name;
            this.age = age;
            this.ID = nextId++;
        }

        public void show() {
            System.out.println("Id=" + ID + "\nName=" + name
                    + "\nAge=" + age);
        }

        public void showNextId() {
            System.out.println("Next employee id will be="
                    + nextId);
        }
    }

    public static void main(String[] args) {
        Employee E = new Employee("GFG1", 56);
        Employee F = new Employee("GFG2", 45);
        Employee G = new Employee("GFG3", 25);
        E.show();
        F.show();
        G.show();
        E.showNextId();
        F.showNextId();
        G.showNextId();

        { // It is sub block to keep
            // all those interns.
            Employee X = new Employee("GFG4", 23);
            Employee Y = new Employee("GFG5", 21);
            X.show();
            Y.show();
            X.showNextId();
            Y.showNextId();
        }
        // After countering this brace, X and Y
        // will be removed.Therefore,
        // now it should show nextId as 4.

        // Output of this line
        E.showNextId();
        // should be 4 but it will give 6 as output.
    }
}
