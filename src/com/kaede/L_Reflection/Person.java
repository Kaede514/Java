package com.kaede.L_Reflection;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * @author kaede
 * @create 2022-10-14
 */

@MyAnnotation("hi")
public class Person extends Creature<String> implements Cloneable,MyInterface {
    @MyAnnotation("field")
    public String name;
    private int age;
    public static String info = "Person";

    public Person() {
    }

    @MyAnnotation("cons")
    private Person(String name) {
        this.name = name;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @MyAnnotation
    private String show(String str) throws RuntimeException {
        System.out.println("show... " + str);
        return str;
    }

    public static void list(String str) {
        System.out.println("list... " + str);
    }

    @Override
    public void info() {
        System.out.println("info...");
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
