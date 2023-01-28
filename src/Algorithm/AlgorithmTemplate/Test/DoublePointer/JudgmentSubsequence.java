package Algorithm.AlgorithmTemplate.Test.DoublePointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JudgmentSubsequence {
    // 双指针实验 判断子序列 https://www.acwing.com/problem/content/2818/
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);
        String[] a = bufferedReader.readLine().split(" ");
        String[] b = bufferedReader.readLine().split(" ");
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (a[i].equals(b[j])) {
                i++;
            }
            j++;
        }
        if (i == n) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
