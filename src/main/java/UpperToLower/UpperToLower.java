package UpperToLower;

import java.util.Scanner;

public class UpperToLower {
    public static void main(String[] args) {
        System.out.println("其输入一个小写字母:");
        Scanner scanner = new Scanner(System.in);
        char upper = scanner.next().charAt(0);
        upper = (char) (upper - 32);
        System.out.println("转换成大写字母为:" + upper);

        System.out.println("请输入一个大写字母:");
        Scanner scanner1 = new Scanner(System.in);
        char lower = scanner1.next().charAt(0);
        lower = (char) (lower + 32);
        System.out.println("转换成小写字母为:" + lower);
    }
}
