package com.kaede.L_Annotation;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.util.ArrayList;

/**
 * @author kaede
 * @create 2022-10-10
 */

public class AnnotationTest {

    public static void main(String[] args) {
        
    }

    @Test
    public void testGetAnnotation() {
        Class<Person> personClass = Person.class;
        Annotation[] annotations = personClass.getAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            System.out.println(annotations[i]);
        }
        System.out.println("---------------------");
        Class<Student> studentClass = Student.class;
        Annotation[] annotations2 = studentClass.getAnnotations();
        for (int i = 0; i < annotations2.length; i++) {
            System.out.println(annotations2[i]);
        }
    }

}

@MyAnnotation("hello")
@MyAnnotation
//@MyAnnotations({@MyAnnotation("hello"),@MyAnnotation("hi")})
class Person {
    private String name;
    private Integer age;

    @MyAnnotation
    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
            "name='" + name + '\'' +
            ", age=" + age +
            '}';
    }
}

class Student extends Person {
    public Student(String name, Integer age) {
        super(name, age);
    }
}

class Generic<@MyAnnotation T> {

    public void show() throws @MyAnnotation RuntimeException {
        ArrayList<@MyAnnotation String> list = new ArrayList<>();
        int num = (@MyAnnotation int) 10L;
    }

}
