package com.kaede.L_Stream;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author kaede
 * @create 2022-10-15
 */

public class StreamTest {

    public List<Employee> list = new ArrayList<>();

    {
        list.add(new Employee(1001, "马化腾", 34, 6000.38));
        list.add(new Employee(1002, "马云", 12, 9876.12));
        list.add(new Employee(1003, "刘强东", 33, 3000.82));
        list.add(new Employee(1004, "雷军", 26, 7657.37));
        list.add(new Employee(1005, "李彦宏", 65, 5555.32));
        list.add(new Employee(1006, "比尔盖茨", 42, 9500.43));
        list.add(new Employee(1007, "任正非", 26, 4333.32));
        list.add(new Employee(1008, "扎克伯格", 35, 2500.32));
    }

    @Test
    public void test() {
        Stream<Employee> stream = list.stream();
        Stream<Employee> parallelStream = list.parallelStream();
    }

    @Test
    public void test1() {
        Stream<Employee> stream = Arrays.stream((Employee[]) list.toArray());
        int[] arr = new int[]{1, 2, 3, 4, 5, 6};
        IntStream stream1 = Arrays.stream(arr);
    }

    @Test
    public void test2() {
        Stream<Employee> stream = Stream.of((Employee[]) list.toArray());
        Stream<Integer> stream1 = Stream.of(1, 2, 3, 4, 5, 6);
    }

    @Test
    public void test3() {
        //迭代，遍历前10个偶数
        Stream.iterate(0, t -> t + 2).limit(10).forEach(System.out::println);
        //生成
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }

    @Test
    public void test4() {
        Stream<Employee> stream = list.stream();
        stream.filter(e -> e.getSalary() > 7000).forEach(System.out::println);
        System.out.println("------------------------");
        //报错，终止操作已执行，被自动关闭
        //stream.limit(3).forEach(System.out::println);
        list.stream().limit(3).forEach(System.out::println);
        System.out.println("------------------------");
        list.stream().skip(6).forEach(System.out::println);
        System.out.println("------------------------");
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 3, 2);
        list1.stream().distinct().forEach(System.out::println);
    }

    @Test
    public void test5() {
        List<String> list1 = Arrays.asList("aa", "bb", "cc");
        list1.stream().map(s -> s.toUpperCase()).forEach(System.out::println);
        System.out.println("------------------------");
        Stream<String> nameStream = list.stream().map(e -> e.getName());
        nameStream.filter(name -> name.length() > 3).forEach(System.out::println);
        System.out.println("------------------------");
        ArrayList list2 = new ArrayList();
        list2.addAll(Arrays.asList(1,2,3));
        ArrayList list3 = new ArrayList();
        list3.addAll(Arrays.asList(3,5,6));
        list2.add(list3);
        //list2.addAll(list3);
        System.out.println(list2);
        System.out.println("------------------------");
        //方式一：嵌套
        /*Stream<Stream<Character>> streamStream = list1.stream().map(StreamTest::fromStringToStream);
        streamStream.forEach(s -> {
            s.forEach(System.out::print);
        });*/
        //方式二：flatMap()
        Stream<Character> characterStream = list1.stream().flatMap(StreamTest::fromStringToStream);
        characterStream.forEach(System.out::println);
    }

    //将字符串中的字符构成的集合转换为对于的Stream的实例
    public static Stream<Character> fromStringToStream(String str) {
        ArrayList<Character> list = new ArrayList<>();
        for (Character c : str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }

    @Test
    public void test6() {
        List<Integer> list1 = Arrays.asList(12, 43, 8, 86, 24);
        list1.stream().sorted().forEach(System.out::println);
        System.out.println("------------------------");
        //list.stream().sorted().forEach(System.out::println);  抛异常
        list.stream().limit(4).sorted((e1, e2) -> {
            int compare = Integer.compare(e1.getAge(), e2.getAge());
            if(compare != 0) {
                return compare;
            } else {
                return Double.compare(e1.getSalary(),e2.getSalary());
            }
        }).forEach(System.out::println);
    }

    @Test
    public void test7() {
        boolean allMatch = list.stream().allMatch(e -> e.getAge() > 30);
        System.out.println("allMatch = " + allMatch);
        boolean anyMatch = list.stream().anyMatch(e -> e.getSalary() > 8000);
        System.out.println("anyMatch = " + anyMatch);
        boolean noneMatch = list.stream().noneMatch(e -> e.getName().startsWith("雷"));
        System.out.println("noneMatch = " + noneMatch);
        Optional<Employee> first = list.stream().findFirst();
        System.out.println("first = " + first);
        Optional<Employee> any = list.stream().findAny();
        System.out.println("any = " + any);
        long count = list.stream().filter(e -> e.getSalary() > 5000).count();
        System.out.println("count = " + count);
        Optional<Double> maxSalary = list.stream().map(Employee::getSalary).max(Double::compare);
        System.out.println("maxSalary = " + maxSalary);
        Optional<Double> minSalary = list.stream().map(Employee::getSalary).min(Double::compare);
        System.out.println("minSalary = " + minSalary);
        list.stream().filter(e -> e.getSalary() > 6000).forEach(System.out::println);
    }

    @Test
    public void test8() {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        Integer sum = list1.stream().reduce(9, Integer::sum);
        System.out.println("sum = " + sum);
        Optional<Double> sum1 = list.stream().map(Employee::getSalary).reduce(Double::sum);
        System.out.println("sum1 = " + sum1);
    }

    @Test
    public void test9() {
        List<Employee> employees = list.stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toList());
        employees.forEach(System.out::println);
        System.out.println("------------------------");
        Set<Employee> employees1 = list.stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toSet());
        employees1.forEach(System.out::println);
    }

}

class Employee {
    private int id;
    private String name;
    private int age;
    private double salary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Employee() {

    }

    public Employee(int id, String name, int age, double salary) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age + ", salary=" + salary + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && age == employee.age && Double.compare(employee.salary, salary) == 0
            && Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, salary);
    }
}
