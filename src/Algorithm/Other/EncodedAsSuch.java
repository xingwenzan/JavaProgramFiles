package Algorithm.Other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EncodedAsSuch {
    //题目: 如此编码; 链接: https://www.acwing.com/problem/content/4702/
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = bufferedReader.readLine().split(" ");
        int[] w = new int[2];
        for (int i = 0; i < 2; i++) {
            w[i] = Integer.parseInt(s[i]);
        }
        int n = w[0];
        int m = w[1];
        String[] strings = bufferedReader.readLine().split(" ");
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(strings[i]);
        }

        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = m % a[i];
            m /= a[i];
        }

        for (int i = 0; i < n; i++) {
            System.out.print(b[i] + " ");
        }
    }
}
