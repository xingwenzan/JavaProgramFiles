package Algorithm.AlgorithmTemplate.Test.DoublePointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TargetOfArrayElementsAnd {
    // 双指针实验 数组元素的目标和 https://www.acwing.com/problem/content/802/
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);
        int x = Integer.parseInt(strings[2]);
        String[] a = bufferedReader.readLine().split(" ");
        String[] b = bufferedReader.readLine().split(" ");
        for (int i = 0, j = m - 1; i < n; i++) {
            while (Integer.parseInt(a[i]) + Integer.parseInt(b[j]) > x) {
                j--;
            }
            if (Integer.parseInt(a[i]) + Integer.parseInt(b[j]) == x && j >= 0) {
                System.out.printf("%d %d", i, j);
                break;
            }
        }
    }
}
