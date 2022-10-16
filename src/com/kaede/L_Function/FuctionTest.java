package com.kaede.L_Function;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author kaede
 * @create 2022-10-15
 */

public class FuctionTest {

    @Test
    public void test() {
        Comparator<Integer> comparator1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        int compare1 = comparator1.compare(12,21);
        System.out.println("compare1 = " + compare1);
        System.out.println("------------------------");
        //Lambda表达式的写法
        Comparator<Integer> comparator2 = (o1, o2) -> Integer.compare(o1, o2);
        int compare2 = comparator2.compare(12, 21);
        System.out.println("compare2 = " + compare2);
        System.out.println("------------------------");
        //方法引用的写法
        Comparator<Integer> comparator3 = Integer::compare;
        int compare3 = comparator3.compare(12, 21);
        System.out.println("compare3 = " + compare3);
    }

    @Test
    public void test1() {
        Runnable r1 = () -> {
            System.out.println("run...");
        };
        Runnable r2 = () -> System.out.println("run...");
        Consumer c = t -> System.out.println(t);
    }

    @Test
    public void test2() {
        Consumer c1 = str -> System.out.println(str);
        //对象::实例方法名
        Consumer c2 = System.out::println;
        c1.accept("beijing");
        c2.accept("tianjing");
        Comparator<String> comparator1 = (s1, s2) -> s1.compareTo(s2);
        //类::实例方法名
        Comparator<String> comparator2 = String::compareTo;
        System.out.println(comparator1.compare("luna", "sola"));
        System.out.println(comparator2.compare("luna", "sola"));
    }

    public static class User {
        private String name;
        private int age;

        public User() {
        }

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
        }
    }

    @Test
    public void test3() {
        Supplier<User> sup1 = () -> new User();
        Supplier<User> sup2 = User::new;
        System.out.println(sup1.get());
        System.out.println(sup2.get());
        BiFunction<String,Integer,User> sup3 = (name, age) -> new User(name, age);
        BiFunction<String,Integer,User> sup4 = User::new;
        System.out.println(sup3.apply("alice", 18));
        System.out.println(sup4.apply("kotory", 16));
    }

    @Test
    public void test4() {
        Function<Integer, String[]> func1 = length -> new String[length];
        Function<Integer, String[]> func2 = String[]::new;
        System.out.println(Arrays.toString(func1.apply(5)));
        System.out.println(Arrays.toString(func2.apply(5)));
    }

}
