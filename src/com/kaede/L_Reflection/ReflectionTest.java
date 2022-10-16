package com.kaede.L_Reflection;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author kaede
 * @create 2022-10-14
 */

public class ReflectionTest {

    @Test
    public void test() throws Exception {
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
    public void test1() throws ClassNotFoundException {
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
        System.out.println("(clazz == clazz1) = " + (clazz == clazz1));
    }

    @Test
    public void test2() {
        Class<Person> clazz = Person.class;
        System.out.println("clazz = " + clazz);
        Class<interfaceA> clazz1 = interfaceA.class;
        System.out.println("clazz1 = " + clazz1);
        Class<int[][]> clazz2 = int[][].class;
        System.out.println("clazz2 = " + clazz2);
        Class<enumC> clazz3 = enumC.class;
        System.out.println("clazz3 = " + clazz3);
        Class<annotationB> clazz4 = annotationB.class;
        System.out.println("clazz4 = " + clazz4);
        Class<Boolean> clazz5 = boolean.class;
        System.out.println("clazz5 = " + clazz5);
        Class<Void> clazz6 = void.class;
        System.out.println("clazz6 = " + clazz6);
    }

    @Test
    public void test3() {
        //对于自定义类，使用系统类加载器进行加载
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        System.out.println("classLoader = " + classLoader);
        //调用系统类加载器的getParent()，获取扩展类加载器
        ClassLoader classLoader1 = classLoader.getParent();
        System.out.println("classLoader1 = " + classLoader1);
        //调用扩展类加载器的getParent()，获取引导类加载器
        //引导类加载器主要用于加载Java的核心类库，无法加载自定义类
        ClassLoader classLoader2 = classLoader1.getParent();
        System.out.println("classLoader2 = " + classLoader2);
        ClassLoader classLoader3 = String.class.getClassLoader();
        System.out.println("classLoader3 = " + classLoader3);
    }

    @Test
    public void test4() throws Exception {
        Properties props = new Properties();
        //读取配置文件方式一，此时路径为module下
        /*FileInputStream fis = new FileInputStream("src\\jdbc.properties");
        props.load(fis);
        fis.close();*/
        //读取配置文件方式二，此时路径默认为类路径，module的src下(以后还有resources下)
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("jdbc.properties");
        props.load(is);

        String user = props.getProperty("user");
        String password = props.getProperty("password");
        System.out.println("user = " + user);
        System.out.println("password = " + password);
    }

    @Test
    public void test5() throws Exception {
        Class<Person> clazz = Person.class;
        //调用默认的无参构造器
        //调用此方法创建运行时类的对象，需满足：
        //1.该运行时类必须提供无参构造器
        //2.该无参构造器的访问权限为public
        Person person = clazz.newInstance();
        System.out.println("person = " + person);
        //获取有参构造器并调用
        Constructor<Person> constructor = clazz.getConstructor(String.class, int.class);
        Person person1 = constructor.newInstance("luna", 16);
        System.out.println("person1 = " + person1);
        //报错，private权限无法直接获取，需使用getDeclaredConstructor()方法获取
        //Constructor<Person> constructor1 = clazz.getConstructor(String.class);
        Constructor<Person> constructor1 = clazz.getDeclaredConstructor(String.class);
        //报错，private权限无法直接通过反射得到实例对象，需要修改访问权限
        //Person person2 = constructor1.newInstance("sola");
        constructor1.setAccessible(true);
        Person person2 = constructor1.newInstance("sola");
        System.out.println("person2 = " + person2);
    }

    public static <T> void iter(T[] t) {
        for (Object o : t) {
            System.out.println(o);
        }
    }

    @Test
    public void testField() throws Exception {
        Class<Person> clazz = Person.class;
        Field[] fields = clazz.getFields();
        System.out.println("------------ fields ------------");
        iter(fields);
        Field[] declaredFields = clazz.getDeclaredFields();
        System.out.println("------------ declaredFields ------------");
        iter(declaredFields);
        //Field field = clazz.getField("age"); 报错
        Field field = clazz.getField("info");
        Field field1 = clazz.getField("name");
        System.out.println("------------------------");
        System.out.println("field = " + field);
        System.out.println("field1 = " + field1);
        Field declaredField = clazz.getDeclaredField("age");
        System.out.println("declaredField = " + declaredField);
        System.out.println("------------ modifiers ------------");
        for (Field f : declaredFields) {
            System.out.println(f.getName() + " " + f.getType() + " : " + Modifier.toString(f.getModifiers()));
            System.out.print("------> ");
            for (Annotation a : f.getAnnotations()) {
                System.out.print(a + "  ");
            }
            System.out.println();
        }
    }

    @Test
    public void testMethod() throws Exception {
        Class<Person> clazz = Person.class;
        Method[] methods = clazz.getMethods();
        System.out.println("------------ methods ------------");
        iter(methods);
        Method[] declaredMethods = clazz.getDeclaredMethods();
        System.out.println("------------ declaredMethods ------------");
        iter(declaredMethods);
        //Method method = clazz.getMethod("show", String.class);  报错
        Method method = clazz.getMethod("list", String.class);
        System.out.println("------------------------");
        System.out.println("method = " + method);
        Method declaredMethod = clazz.getDeclaredMethod("show", String.class);
        System.out.println("declaredMethod = " + declaredMethod);
        System.out.println("------------ modifiers ------------");
        for (Method m : declaredMethods) {
            System.out.println(m.getName() + " " + m.getReturnType() + " : " + Modifier.toString(m.getModifiers()));
            System.out.print("------> ");
            for (Annotation a : m.getAnnotations()) {
                System.out.print(a + "  ");
            }
            System.out.println();
            System.out.print("------> ");
            Class<?>[] params = m.getParameterTypes();
            if (params != null) {
                for (Class<?> p : params) {
                    System.out.print(p + "  ");
                }
            }
            System.out.println();
            System.out.print("------> ");
            Class<?>[] exs = m.getExceptionTypes();
            if (exs != null) {
                for (Class<?> e : exs) {
                    System.out.print(e + "  ");
                }
            }
            System.out.println();
        }
    }

    @Test
    public void testConstructor() throws Exception {
        Class<Person> clazz = Person.class;
        Constructor<?>[] constructors = clazz.getConstructors();
        System.out.println("------------ constructors ------------");
        iter(constructors);
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
        System.out.println("------------ declaredConstructors ------------");
        iter(declaredConstructors);
        //Constructor<Person> constructor = clazz.getConstructor(String.class); 报错
        Constructor<Person> constructor = clazz.getConstructor(String.class, int.class);
        System.out.println("constructor = " + constructor);
        Constructor<Person> declaredConstructor = clazz.getDeclaredConstructor(String.class);
        System.out.println("declaredConstructor = " + declaredConstructor);
    }

    @Test
    public void testParent() {
        Class<Person> clazz = Person.class;
        Class<? super Person> superclass = clazz.getSuperclass();
        System.out.println("superclass = " + superclass);
        Type gs = clazz.getGenericSuperclass();
        System.out.println("gs = " + gs);
        ParameterizedType parameterizedType = (ParameterizedType) gs;
        //获取泛型参数
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        for (Type type : actualTypeArguments) {
            System.out.println(type);
            System.out.println(type.getTypeName());
            System.out.println(((Class) type).getName());
        }
    }

    @Test
    public void testInterface() {
        Class<Person> clazz = Person.class;
        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class<?> i : interfaces) {
            System.out.println(i);
        }
        System.out.println(Arrays.toString(clazz.getSuperclass().getInterfaces()));
    }

    @Test
    public void testInfo() {
        Class<Person> clazz = Person.class;
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation a : annotations) {
            System.out.println(a);
        }
        System.out.println("------------------------");
        System.out.println(Modifier.toString(clazz.getModifiers()));
        System.out.println(Modifier.toString(interfaceA.class.getModifiers()));
        System.out.println(Modifier.toString(annotationB.class.getModifiers()));
        System.out.println(Modifier.toString(enumC.class.getModifiers()));
    }

    @Test
    public void test6() throws Exception {
        Person person = new Person("kaede", 18);
        System.out.println("person = " + person);
        Class<Person> clazz = Person.class;
        Field field = clazz.getDeclaredField("name");
        field.setAccessible(true);
        field.set(person, "luna");
        System.out.println("person = " + person);
        Field field1 = clazz.getField("info");
        System.out.println("field1.get(null) = " + field1.get(null));
        field1.set(null, "hello");
        System.out.println("Person.info = " + Person.info);
    }

    @Test
    public void test7() throws Exception {
        Person person = new Person("kaede", 18);
        Class<Person> clazz = Person.class;
        Method method = clazz.getDeclaredMethod("show", String.class);
        method.setAccessible(true);
        Object returnValue = method.invoke(person, "bird");
        System.out.println("returnValue = " + returnValue);
        Method method1 = clazz.getDeclaredMethod("list", String.class);
        //method1.setAccessible(true);
        Object returnValue1 = method1.invoke(null, "cat");
        System.out.println("returnValue1 = " + returnValue1);
    }

}

