package Algorithm.Other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SoFreeShipping {
    // 题目：如此包邮；链接：https://www.acwing.com/problem/content/4703/
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int x = Integer.parseInt(s[1]);
        int[] a = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            String s1 = bufferedReader.readLine();
            a[i] = Integer.parseInt(s1);
            sum += a[i];
        }
        int m = sum - x;
        int[] f = new int[m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = m; j >= a[i]; j--) {
                f[j] = Math.max(f[j], f[j - a[i]] + a[i]);
            }
        }
        System.out.println(sum - f[m]);
    }
}
