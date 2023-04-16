package Algorithm.AlgorithmBasicCourse.BasicAlgorithms.DoublePointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LongestNonRepeatingSubsequence {
    // 双指针实验 最长连续不重复子序列 https://www.acwing.com/problem/content/801/
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        String[] strings = bufferedReader.readLine().split(" ");
        int[] lst = new int[n];
        for (int i = 0; i < n; i++) {
            lst[i] = Integer.parseInt(strings[i]);
        }
        int[] repeat = new int[(int) Math.pow(10, 5) + 1];
        int res = 0;
        Arrays.fill(repeat, 0);
        for (int l = 0, r = 0; r < n; r++) {
            repeat[lst[r]]++;
            while (repeat[lst[r]] > 1) {
                repeat[lst[l]]--;
                l++;
            }
            res = Math.max(res, r - l + 1);
        }
        System.out.println(res);
    }
}
