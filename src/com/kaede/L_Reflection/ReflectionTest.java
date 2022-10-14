package com.kaede.L_Reflection;

import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * @author kaede
 * @create 2022-10-14
 */

public class ReflectionTest {

    @Test
    public void test() throws ClassNotFoundException {
        Person person = new Person("luna", 16);
        Class<Person> clazz = Person.class;
        System.out.println("clazz = " + clazz);
        Class<? extends Person> clazz1 = person.getClass();
        System.out.println("clazz1 = " + clazz1);
        Class<?> clazz2 = Class.forName("com.kaede.L_Reflection.Person");
        System.out.println("clazz2 = " + clazz2);
        ClassLoader classLoader = this.getClass().getClassLoader();
        Class<?> clazz3 = classLoader.loadClass("com.kaede.L_Reflection.Person");
        System.out.println("clazz3 = " + clazz3);
    }

    @Test
    public void test1() throws Exception {
        Person person = new Person("luna", 16);
        //Class<Person> clazz = (Class<Person>) Class.forName("com.kaede.L_Reflection.Person");
        //Person person1 = clazz.newInstance();
        Class<?> clazz = Class.forName("com.kaede.L_Reflection.Person");
        String name = clazz.getName();
        System.out.println("name = " + name);
        Class<?> superclass = clazz.getSuperclass();
        System.out.println("superclass = " + superclass);
        Person person1 = (Person) clazz.newInstance();
        System.out.println("person1 = " + person1);
        Class<?>[] interfaces = clazz.getInterfaces();
        System.out.println("interfaces = " + Arrays.toString(interfaces));
        ClassLoader classLoader = clazz.getClassLoader();
        System.out.println("classLoader = " + classLoader);
    }

    @Test
    public void test2() throws Exception {
        Class<Person> clazz = Person.class;
        Constructor<?>[] constructors = clazz.getConstructors();
        System.out.println("constructors = " + Arrays.toString(constructors));
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
        System.out.println("declaredConstructors = " + Arrays.toString(declaredConstructors));
        //Constructor<Person> constructor = clazz.getConstructor(String.class); 报错
        Constructor<Person> constructor = clazz.getConstructor(String.class, int.class);
        System.out.println("constructor = " + constructor);
        Constructor<Person> declaredConstructor = clazz.getDeclaredConstructor(String.class);
        System.out.println("declaredConstructor = " + declaredConstructor);
        System.out.println("------------------------");
        Field[] fields = clazz.getFields();
        System.out.println("fields = " + Arrays.toString(fields));
        Field[] declaredFields = clazz.getDeclaredFields();
        System.out.println("declaredFields = " + Arrays.toString(declaredFields));
        //Field field = clazz.getField("name"); 报错
        Field field = clazz.getField("info");
        Field field1 = clazz.getField("id");
        System.out.println("field = " + field);
        System.out.println("field1 = " + field1);
        Field declaredField = clazz.getDeclaredField("name");
        System.out.println("declaredField = " + declaredField);
        System.out.println("------------------------");
        Method[] methods = clazz.getMethods();
        System.out.println("methods = " + Arrays.toString(methods));
        Method[] declaredMethods = clazz.getDeclaredMethods();
        System.out.println("declaredMethods = " + Arrays.toString(declaredMethods));
        Method method = clazz.getMethod("list", String.class);
        System.out.println("method = " + method);
        Method declaredMethod = clazz.getDeclaredMethod("show", String.class);
        System.out.println("declaredMethod = " + declaredMethod);
    }

}

class Person implements Serializable {
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
