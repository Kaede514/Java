package com.kaede.L_Collection;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author kaede
 * @create 2022-10-10
 */

public class CollectionTest {

    @Test
    public void testColl() {
        Collection coll = new ArrayList();
        // add()
        coll.add("AA");
        coll.add(123);
        coll.add(new Date());
        // size()
        System.out.println(coll.size());
        // addAll()
        Collection coll2 = new ArrayList();
        coll2.addAll(coll);
        System.out.println(coll2);
        // clear()
        coll2.clear();
        // isEmpty()
        System.out.println("coll2.isEmpty() = " + coll2.isEmpty());
    }

    @Test
    public void testColl1() {
        Collection coll = new ArrayList();
        coll.add("AA");
        // String类重写了equals方法
        coll.add(new String("BB"));
        coll.add(123);
        coll.add(new Date());
        // contains()
        boolean contains = coll.contains("AA");
        boolean contains1 = coll.contains(new String("BB"));
        System.out.println("contains = " + contains);
        System.out.println("contains1 = " + contains1);
        // containsAll()
        Collection coll1 = new ArrayList();
        Collection coll2 = Arrays.asList(123, "AA", "BB", "kk");
        coll1.addAll(coll);
        boolean containsAll = coll.containsAll(coll1);
        boolean containsAll1 = coll.containsAll(coll2);
        System.out.println("containsAll = " + containsAll);
        System.out.println("containsAll1 = " + containsAll1);
    }

    @Test
    public void testColl2() {
        Collection coll = new ArrayList();
        coll.add("AA");
        coll.add(new String("BB"));
        coll.add(123);
        coll.add(new Date());
        // remove()
        boolean remove = coll.remove("BB");
        System.out.println("remove = " + remove);
        // removeAll()
        Collection coll1 = new ArrayList();
        coll1.add("AA");
        coll1.add(123);
        coll1.add(456);
        boolean removeAll = coll.removeAll(coll1);
        System.out.println("removeAll = " + removeAll);
        System.out.println("coll = " + coll);
    }

    @Test
    public void testColl3() {
        Collection coll = new ArrayList();
        coll.add("AA");
        coll.add(new String("BB"));
        coll.add(123);
        // retainAll()
        Collection coll1 = Arrays.asList("AA", "CC", 123);
        boolean retainAll = coll.retainAll(coll1);
        System.out.println("retainAll = " + retainAll);
        System.out.println("coll = " + coll);
        // equals()
        //Arrays.asList()方法继承的抽象方式未实现clear(),add()等方法，所以
        //错误为：UnsupportedOperationException
        //coll1.clear();
        boolean equals = coll.equals(coll1);
        System.out.println("equals = " + equals);
        // toArray()
        Object[] objects = coll.toArray();
        for (int i = 0; i < objects.length; i++) {
            System.out.println(objects[i]);
        }
        // hashCode()
        System.out.println("coll.hashCode() = " + coll.hashCode());
        // 扩展
        List ints = Arrays.asList(new int[]{1, 2});
        System.out.println("ints.size() = " + ints.size());
        List integers = Arrays.asList(new Integer[]{1, 2});
        System.out.println("integers.size() = " + integers.size());
    }

    @Test
    public void testColl4() {
        Collection coll = new ArrayList();
        coll.add("AA");
        coll.add(new String("BB"));
        coll.add(123);
        // iterator()
        Iterator iterator = coll.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        while (iterator.hasNext()) {
            Object next = iterator.next();
            if ("AA".equals(next)) {
                iterator.remove();
            }
        }

        System.out.println("coll = " + coll);
    }

    @Test
    public void testIter() {
        String[] strs = new String[]{"A","B","C"};
        for (String str : strs) {
            str = "D";
        }
        System.out.println("strs = " + Arrays.toString(strs));
        for (int i = 0; i < strs.length; i++) {
            strs[i] = "D";
        }
        System.out.println("strs = " + Arrays.toString(strs));
    }

}
