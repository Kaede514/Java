package com.kaede.L_Enum;

/**
 * @author kaede
 * @create 2022-10-10
 */

public class SeasonTest2 {
    public static void main(String[] args) {
        Season2 summer = Season2.SUMMER;
        System.out.println(Season2.class.getSuperclass());
        //toString()
        System.out.println(summer);
        System.out.println("---------------------");
        //values()
        Season2[] values = Season2.values();
        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i]);
        }
        System.out.println("---------------------");
        Thread.State[] values1 = Thread.State.values();
        for (int i = 0; i < values1.length; i++) {
            System.out.println(values1[i]);
        }
        System.out.println("---------------------");
        //valueOf(String objName)
        //如果没有objName的枚举类对象，则抛java.lang.IllegalArgumentException
        Season2 winter = Season2.valueOf("WINTER");
        //Season2 winter2 = Season2.valueOf("WINTER2");
        System.out.println("winter = " + winter);

        summer.show();
    }
}

interface Info {
    void show();
}

//使用enum关键字定义枚举类
enum Season2 implements Info{
    //1.提供当前枚举类的对象，不能使用 public static final 修饰（默认就是）
    //多个对象之间用逗号分隔，最后一个对象用分号结束
    SPRING("春天","春暖花开") {
        @Override
        public void show() {
            System.out.println("spring");
        }
    },
    SUMMER("夏天","夏日炎炎") {
        @Override
        public void show() {
            System.out.println("summer");
        }
    },
    AUTUMN("秋天","秋高气爽") {
        @Override
        public void show() {
            System.out.println("autumn");
        }
    },
    WINTER("冬天","冰天雪地") {
        @Override
        public void show() {
            System.out.println("winter");
        }
    };

    //2.声明Season2对象的属性
    private final String name;
    private final String desc;

    //3.私有化类的构造器，并给对象属性赋值
    private Season2(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }


    //4.其他诉求，如获取枚举类对象的属性(get方法)等
    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    /*@Override
    public void show() {
        System.out.println("这是一个季节");
    }*/
}

