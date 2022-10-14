package com.kaede.L_Collection;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author kaede
 * @create 2022-10-11
 */

public class SetTest {

    @Test
    public void testSet() {
        HashSet set = new HashSet();
        set.add(123);
        set.add("BB");
        set.add("CC");
        set.add(new A("aa"));
        boolean add = set.add(new A("aa"));
        System.out.println("add = " + add);
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private class A {
        private String name;

        public A(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "A{" +
                "name='" + name + '\'' +
                '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            A a = (A) o;
            return Objects.equals(name, a.name);
        }
    }

    @Test
    public void test() {
        HashSet set = new HashSet();
        set.add(new B());
        boolean add = set.add(new B());
        System.out.println("add = " + add);
        for (Object o : set) {
            System.out.println(o);
        }
    }

    static class B {
        public static int i = 0;

        public B() {
            //i++;
            System.out.println(i);
        }

        @Override
        public boolean equals(Object obj) {
            //return true;
            return false;
        }

        @Override
        public int hashCode() {
            return i;
        }
    }

    @Test
    public void testTreeSet() {
        TreeSet treeSet = new TreeSet();
        treeSet.add(new C("aa",12));
        treeSet.add(new C("aa",22));
        treeSet.add(new C("bb",8));
        treeSet.add(new C("cc",16));
        for (Object o : treeSet) {
            System.out.println(o);
        }
        System.out.println("----------------------");
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof C && o2 instanceof C) {
                    C c1 = (C) o1;
                    C c2 = (C) o2;
                    int compare = c1.name.compareTo(c2.name);
                    if (compare != 0) {
                        return compare;
                    } else {
                        return c1.age.compareTo(c2.age);
                    }
                } else {
                    throw new ClassCastException();
                }
            }
        };
        TreeSet treeSet1 = new TreeSet(comparator);
        treeSet1.addAll(treeSet);
        for (Object o : treeSet1) {
            System.out.println(o);
        }
        System.out.println("----------------------");
        TreeSet treeSet2 = new TreeSet(comparator);
        treeSet2.add(new C("aa",12));
        treeSet2.add(new C("aa",22));
        treeSet2.add(new C("bb",8));
        treeSet2.add(new C("cc",16));
        treeSet1.addAll(treeSet);
        for (Object o : treeSet2) {
            System.out.println(o);
        }
    }

}

class C implements Comparable{
    public String name;
    public Integer age;

    public C(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof C) {
            C c = (C) o;
            return this.name.compareTo(c.name);
        } else {
            throw new ClassCastException();
        }
    }

    @Override
    public String toString() {
        return "C{" +
            "name='" + name + '\'' +
            ", age=" + age +
            '}';
    }
}
