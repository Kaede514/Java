package com.kaede.L_Enum;

/**
 * @author kaede
 * @create 2022-10-10
 */

public class SeasonTest {
    public static void main(String[] args) {
        Season spring = Season.SPRING;
        System.out.println(spring);
    }
}

//自定义枚举类
class Season {
    //1.声明Season对象的属性
    private final String name;
    private final String desc;

    //2.私有化类的构造器，并给对象属性赋值
    private Season(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    //3.提供当前枚举类的多个对象，声明为 public static final
    public static final Season SPRING = new Season("春天","春暖花开");
    public static final Season SUMMER = new Season("夏天","夏日炎炎");
    public static final Season AUTUMN = new Season("秋天","秋高气爽");
    public static final Season WINTER = new Season("冬天","冰天雪地");

    //4.其他诉求，如获取枚举类对象的属性(get方法)等
    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "Season{" +
            "name='" + name + '\'' +
            ", desc='" + desc + '\'' +
            '}';
    }
}
