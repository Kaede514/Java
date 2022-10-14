package com.kaede.L_Reflection;

import com.kaede.L_Annotation.MyAnnotation;

import java.io.Serializable;

/**
 * @author kaede
 * @create 2022-10-14
 */

@SuppressWarnings("all")
public class Person implements Serializable {
    public Integer id;
    private String name;
    private int age;
    public static String info = "Person";

    public Person() {
    }

    private Person(String name) {
        this.name = name;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private void show(String str) {
        System.out.println("show... " + str);
    }

    public static void list(String str) {
        System.out.println("list... " + str);
    }

    @Override
    public String toString() {
        return "Person{" +
            "name='" + name + '\'' +
            ", age=" + age +
            '}';
    }
}

interface interfaceA {

}

@interface annotationB {

}

enum enumC {

}
