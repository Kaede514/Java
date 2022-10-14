package com.kaede.L_Common.L_Date;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author kaede
 * @create 2022-10-14
 */

public class DateTimeTest {

    @Test
    public void test() throws ParseException {
        //使用默认的构造器
        SimpleDateFormat sdf = new SimpleDateFormat();
        Date date = new Date();
        System.out.println("date = " + date);
        //格式化
        String format = sdf.format(date);
        System.out.println("format = " + format);
        //解析
        //String str = "2020-10-9";  解析报错
        String str = "22-10-14 下午4:22";
        Date parse = sdf.parse(str);
        System.out.println("parse = " + parse);
        System.out.println("--------------------------");
        //使用有参构造器
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E Z");
        Date date1 = new Date();
        System.out.println("date1 = " + date1);
        //格式化
        String format1 = sdf1.format(date1);
        System.out.println("format1 = " + format1);
        //解析
        String str1 = "2022-10-14 08:00:00 星期五 +0800";
        Date parse1 = sdf1.parse(str1);
        System.out.println("parse1 = " + parse1);
    }

    @Test
    public void test1() {
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getClass());
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("dayOfMonth = " + dayOfMonth);
        //月份从0开启计数，今天是10月14日
        int month = calendar.get(Calendar.MONTH);
        System.out.println("month = " + month);
        calendar.set(Calendar.DAY_OF_MONTH, 15);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("dayOfMonth = " + dayOfMonth);
        calendar.add(Calendar.DAY_OF_MONTH, -2);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("dayOfMonth = " + dayOfMonth);
        Date date = calendar.getTime();
        System.out.println("date = " + date);
        Date date1 = new Date();
        calendar.setTime(date1);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("dayOfMonth = " + dayOfMonth);
    }

}
