package Algorithm.Other;

import java.util.Scanner;

public class PaperSize {
    // 题目: 纸张尺寸; 链接: https://www.acwing.com/problem/content/4655/
    static int[] a = new int[11];

    public static void main(String[] args) {
        a[0] = 1189;
        a[1] = 841;
        for (int i = 2; i < 11; i++) {
            a[i] = Math.max(a[i - 2], a[i - 1]) / 2;
        }
        Scanner scanner = new Scanner(System.in);
        String n0 = scanner.nextLine();
        int n = Integer.parseInt(String.valueOf(n0.charAt(1)));
        System.out.println(a[n]);
        System.out.println(a[n + 1]);
    }
}
