package org.swordess.toy.jdk8.model;

public class Person {

    private String name;
    private int age;
    private String emailAddress;

    public Person(String name, int age, String emailAddress) {
        this.name = name;
        this.age = age;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String toString() {
        return "Person [ name=" + name + ", age=" + age + ", emailAddress=" + emailAddress + " ]";
    }

    public static int compareByAge(Person a, Person b) {
        return a.age - b.age;
    }

}
