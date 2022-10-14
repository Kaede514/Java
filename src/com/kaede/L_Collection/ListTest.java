package com.kaede.L_Collection;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author kaede
 * @create 2022-10-11
 */

public class ListTest {

    @Test
    public void testList() {
        List list = new ArrayList();
        // add()
        list.add("A");
        list.add(0, "B");
        // addAll()
        list.addAll(1, Arrays.asList("C", "D", "B"));
        System.out.println(list);
        // get()
        System.out.println("list.get(2) = " + list.get(2));
        // indexOf()
        System.out.println("list.indexOf(\"B\") = " + list.indexOf("B"));
        // lastIndexOf()
        System.out.println("list.lastIndexOf(\"B\") = " + list.lastIndexOf("B"));
        // remove()
        System.out.println("list.remove(2) = " + list.remove(2));
        // set()
        System.out.println("list.set(3, \"E\") = " + list.set(3, "E"));
        // subList()
        System.out.println("list.subList(1, 4) = " + list.subList(1, 4));
        System.out.println("list = " + list);
    }

}
