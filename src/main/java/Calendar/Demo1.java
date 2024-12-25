package Calendar;

import java.util.Scanner;

public class Demo1 {
    // 创建程序入口
    public static void main(String[] args) {
        // 准备阶段
        Scanner input  = new Scanner(System.in);
        System.out.print("请输入4位的年份: ");
        int year = input.nextInt();
        System.out.print("请输入月份: ");
        int month = input.nextInt();
        System.out.println(year + "年" + month + "月\t\t\t\t\t<  >");
        // 根据输入的月份获得该月份对应的天数
        int monthdays = 0;
        // 代表大月
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            // System.out.println("有31天");
            monthdays = 31;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            //System.out.println("有30天");
            monthdays = 30;
        } else {
            if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                // System.out.println("有29天");
                monthdays = 29;
        } else {

                // System.out.println("有28天");
                monthdays = 28;
            }
    }
        // 求输入的月份的1号是星期几
        // 求输入年份 距离 1900年的 整年天数差是多少天？2024.7.
        int sumYear = 0;
        for (int i = 1900; i < year; i++) {
            if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
               sumYear = sumYear + 366;
            } else {
                sumYear = sumYear + 365;
            }
        }
        // 求输入月份 距离  输入年份的1月的整月天数差
        int sumMonth = 0;
        for (int i = 1; i < month; i++) {
            if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12) {
                sumMonth = sumMonth + 31;
            } else if (i == 4 || i == 6 || i == 9 || i == 11) {
                sumMonth = sumMonth + 30;
            } else {
                if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                    sumMonth = sumMonth + 29;
                } else {
                    sumMonth = sumMonth + 28;
                }
            }
        }
        // 求 输入年 输入月的1号是星期几？
        int week = (sumYear + sumMonth) % 7;
        //System.out.println(year+"年"+month+"月1号是星期"+week);

        // 打印日历
        System.out.println("一\t二\t三\t四\t五\t六\t日");
        //打印空格:
        for (int i = 0; i < week; i++) {
            System.out.print("\t");
        }

        // 打印日历
        for (int i = 1; i <= monthdays; i++) {
            System.out.print(i + "\t");
            if ((sumYear + sumMonth + i) % 7 == 0){
                System.out.println();
            }
        }
    }
}
