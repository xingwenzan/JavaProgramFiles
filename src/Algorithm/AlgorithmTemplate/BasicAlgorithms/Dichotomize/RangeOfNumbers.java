package Algorithm.AlgorithmTemplate.BasicAlgorithms.Dichotomize;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RangeOfNumbers {
    // 二分实验 数的范围 https://www.acwing.com/problem/content/791/
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int q = Integer.parseInt(s[1]);
        int[] lst = new int[n];
        s = bufferedReader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            lst[i] = Integer.parseInt(s[i]);
        }
        for (int i = 0; i < q; i++) {
            int num = Integer.parseInt(bufferedReader.readLine());
            int l = dichotomizeRightIsTrue(lst, 0, n - 1, num);
            int r = dichotomizeLeftIsTrue(lst, 0, n - 1, num);
            if (lst[l] != num || lst[r] != num) {
                System.out.println("-1 -1");
            } else {
                System.out.printf("%d %d%n", l, r);
            }
        }
    }

    public static int dichotomizeLeftIsTrue(int[] lst, int l, int r, int num) {
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (lst[mid] <= num) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }

    public static int dichotomizeRightIsTrue(int[] lst, int l, int r, int num) {
        while (l < r) {
            int mid = l + r >> 1;
            if (lst[mid] >= num) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
