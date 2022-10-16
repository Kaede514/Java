package com.kaede.L_Reflection;

import java.io.Serializable;

/**
 * @author kaede
 * @create 2022-10-15
 */

public class Creature<T> implements Serializable {
    private char gender;
    public double weight;

    private void breath() {
        System.out.println("breath...");
    }

    public void eat() {
        System.out.println("eat...");
    }
}