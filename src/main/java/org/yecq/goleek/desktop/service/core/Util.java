package org.yecq.goleek.desktop.service.core;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * 一些通用工具函数
 *
 * @author Administrator
 */
public final class Util {

    // 由数值得到百分比字符串，n表示要保留小数点后几位
    public static String getPercentString(double number, int n) {
        DecimalFormat format = (DecimalFormat) NumberFormat.getPercentInstance();
        format.setMinimumFractionDigits(n);

        return format.format(number);
    }

    // 从百分号数中得到数值
    public static double getRealValue(String string) {
        if (!string.endsWith("%")) {
            throw new UnsupportedOperationException("输入的数字格不是百分号");
        }

        int len = string.length();
        double d = Double.parseDouble(string.substring(0, len - 1)) / 100;
        return d;
    }

    // 得到当天的日期
    public static String getTodayStr() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE);
    }

    // 得到当天的年份
    public static String getToyearStr() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR) + "";
    }

    // 得到当天的月份
    public static String getTomonthStr() {
        Calendar c = Calendar.getInstance();
        return (c.get(Calendar.MONTH) + 1) + "";
    }

    // 获得某个数的小数点几位的值，要求n>=0
    public static double getFormattedNumber(double num, int n) {
        if (n == 0) {
            return Double.parseDouble(new DecimalFormat("0.00").format(num));
        }
        String tmp = "0.";
        for (int i = 0; i < n; i++) {
            tmp += "0";
        }
        DecimalFormat tmp1 = new DecimalFormat(tmp);
        return Double.parseDouble(tmp1.format(num));
    }

    // 测试字符串是否是一个合法的yyyy-mm-dd
    public static boolean isDateString(String date) {
        if (date == null || date.trim().equals("")) {
            return false;
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            format.parse(date);
        } catch (ParseException ex) {
            return false;
        }
        return true;
    }

    // 从JTextField中提取数字，数字格式不对则返回null
    public static Double getDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            return null;
        }
    }

    // 得到整数
    public static Integer getInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return null;
        }
    }

    // 验证是否是一个合法的年份字符串
    public static boolean isYearString(String year) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try {
            format.setLenient(false);
            format.parse(year + "/1/1");
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    // 获取是星期几，date为null表示计算今天的
    public static String getWeekDay(String date) {
        try {
            String[] weeks = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance(Locale.CHINA);
            if (date != null) {
                c.setTime(format.parse(date));
            }
            int w = c.get(Calendar.DAY_OF_WEEK);
            return weeks[w - 1];
        } catch (ParseException e) {
            throw new IllegalArgumentException("日期格式错误");
        }
    }

    public static String getWeekDay(Calendar c) {
        String[] weeks = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        int w = c.get(Calendar.DAY_OF_WEEK);
        return weeks[w - 1];
    }

    // 获取几天以后的日期，跳开双休日
    public static String getDateAfter(String from, int k) {
        try {
            Set set = new HashSet();
            set.add("周六");
            set.add("周日");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            if (from != null) {
                c.setTime(format.parse(from));
            }
            int count = 0;
            while (count < k) {
                // 获取下一天
                c.add(Calendar.DATE, 1);
                String week = getWeekDay(c);
                if (!set.contains(week)) {
                    count++;
                }
            }
            return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE);
        } catch (ParseException e) {
            throw new IllegalArgumentException("日期格式错误");
        }
    }

    // 日期比较
    public static int compareDate(String d1, String d2) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c1 = Calendar.getInstance();
            c1.setTime(format.parse(d1));
            Calendar c2 = Calendar.getInstance();
            c2.setTime(format.parse(d2));
            return c1.compareTo(c2);
        } catch (ParseException e) {
            throw new IllegalArgumentException("日期格式错误");
        }
    }
}
