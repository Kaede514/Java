package com.kaede.L_Common.L_Date;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * @author kaede
 * @create 2022-10-14
 */

public class JDK8DateTimeTest {

    @Test
    public void testDate() {
        Date date = new Date(2022, 10, 14);
        System.out.println("date = " + date);
    }

    @Test
    public void test() {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDate = " + localDate);
        System.out.println("localTime = " + localTime);
        System.out.println("localDateTime = " + localDateTime);
        LocalDateTime localDateTime1 = LocalDateTime.of(2022, 10, 14, 17, 32, 43);
        System.out.println("localDateTime1 = " + localDateTime1);
        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
        System.out.println("dayOfWeek = " + dayOfWeek);
        Month month = localDateTime.getMonth();
        System.out.println("month = " + month);
        int monthValue = localDateTime.getMonthValue();
        System.out.println("monthValue = " + monthValue);
        LocalDateTime localDateTime2 = localDateTime.withMonth(12);
        System.out.println("localDateTime = " + localDateTime);
        System.out.println("localDateTime2 = " + localDateTime2);
    }

    @Test
    public void test1() {
        Instant instant = Instant.now();
        //结果比现在时间少了8个小时
        System.out.println("instant = " + instant);
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        //当前时间
        System.out.println("offsetDateTime = " + offsetDateTime);
        long milli = instant.toEpochMilli();
        System.out.println("milli = " + milli);
        Instant instant1 = Instant.ofEpochMilli(milli + 8*60*60*1000);
        System.out.println("instant1 = " + instant1);
    }

    @Test
    public void test2() {
        //预定义的标准格式
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime localDateTime = LocalDateTime.now();
        //格式化
        String format = formatter.format(localDateTime);
        System.out.println("localDateTime = " + localDateTime);
        System.out.println("format = " + format);
        //解析
        TemporalAccessor parse = formatter.parse(format);
        System.out.println("parse = " + parse);
        //本地化相关的格式
        DateTimeFormatter formatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        String format1 = formatter1.format(localDateTime);
        System.out.println("format1 = " + format1);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        String format2 = formatter2.format(localDateTime);
        System.out.println("format2 = " + format2);
        DateTimeFormatter formatter3 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        String format3 = formatter3.format(localDateTime);
        System.out.println("format3 = " + format3);
        //自定义的格式
        DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format4 = formatter4.format(localDateTime);
        System.out.println("format4 = " + format4);
    }

}
