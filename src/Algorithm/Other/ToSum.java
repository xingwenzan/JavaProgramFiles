package Algorithm.Other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ToSum {
    // 题目：求和；链接：https://www.acwing.com/problem/content/description/4647/
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        long ans = 0;
        String[] strings = bufferedReader.readLine().split(" ");
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(strings[i]);
            ans += a[i];
        }
        ans *= ans;
        for (int i : a) {
            ans -= (long) i * i;
        }
        System.out.println(ans / 2);
    }
}
