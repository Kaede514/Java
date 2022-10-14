package com.kaede.L_Generic;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author kaede
 * @create 2022-10-12
 */

public class GenericTest {

    public static void main(String[] args) {

    }

    public static void test(HashMap<String, Object> map) {

    }

    @Test
    public void testGeneric() {
        HashMap map = new HashMap();
        HashMap<String, Object> map1 = new HashMap<>();
        HashMap<Object, Object> map2 = new HashMap<>();
        test(map);  //泛型擦除，编译不会进行类型检查
        test(map1);
        //test(map2); //编译报错
    }
    
    @Test
    public void test() {
        B<Integer, String> b = new B<Integer, String>("type", "key", 123);
        System.out.println("b.t = " + b.t);
        System.out.println("b.k = " + b.k);
        System.out.println("b.v = " + b.v);
    }

    public static <E> void show(E e) {
        System.out.println(e);
    }

    public static <E> E get(E e) {
        return e;
    }

    @Test
    public void testMethod() {
        show("hello");
        System.out.println(get("security"));
    }

    @Test
    public void test1() {
        ArrayList<Object> list = new ArrayList<>();
        list.add("AA");
        list.add("123");
        ArrayList<?> list1 = list;
        //list1.add("AA");  编译时报错
        System.out.println("list1.get(0) = " + list1.get(0));
    }

    @Test
    public void test2() {
        List<? extends Number> list = null;
        List<Integer> list1 = Arrays.asList(123,456);
        List<String> list2 = Arrays.asList("AA", "BB");
        //list = list2; 编译时报错
        list = list1;
        //list.add(123); 因为不清楚指向的集合的元素类型，故无法添加
        Number number = list.get(0);
    }

    @Test
    public void test3() {
        List<? super D> list = null;
        List<C> list1 = new ArrayList<>();
        list1.add(new C("CC"));
        List<E> list2 = new ArrayList<>();
        list2.add(new E("EE"));
        //list = list2; 编译时报错
        list = list1;
        //list.add(new C("CC"));    编译时报错
        list.add(new D("DD"));
        list.add(new E("KK"));
        Object object = list.get(0);
    }

    @Test
    public void test4() {
        F<Integer> f = new F<Integer>(123);
        f.show();
    }

}

/*class A<K, V> {
    public K k;
    public V v;
}*/
class A<K, V> {
    public K k;
    public V v;
    public A(K k, V v) {
        this.k = k;
        this.v = v;
    }
}

/*class B extends A {

}*/
/*class B extends A<String, Object> {

}*/
/*class B<K, V> extends A<K, V> {

}*/
/*class B<V> extends A<String, V> {

}*/
class B<V, T> extends A<String, V> {
    public T t;

    public B(T t, String k, V v) {
        super(k, v);
        this.t = t;
    }
}

class C {
    public String name;

    public C(String name) {
        this.name = name;
    }
}

class D extends C {
    public D(String name) {
        super(name);
    }
}

class E extends D {
    public E(String name) {
        super(name);
    }
}

class F<T extends Number> {
    public T t;

    public F(T t) {
        this.t = t;
    }

    public void show() {
        System.out.println(t);
    }
}
