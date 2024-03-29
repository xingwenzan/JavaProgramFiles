package Algorithm.Other;

import java.util.Scanner;

public class Normalization {
    //题目：归一化处理；链接：https://www.acwing.com/problem/content/4512/
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        double[] a = new double[n];
        double avg = 0;
        double d = 0;

        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextDouble();
            avg += a[i] / n;
        }
        for (int i = 0; i < n; i++) {
            d += Math.pow(a[i] - avg, 2) / n;
        }
        for (int i = 0; i < n; i++) {
            System.out.println((a[i] - avg) / Math.pow(d, 0.5));
        }
    }
}
