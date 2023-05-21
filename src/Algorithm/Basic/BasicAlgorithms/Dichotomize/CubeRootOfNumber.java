package Algorithm.Basic.BasicAlgorithms.Dichotomize;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CubeRootOfNumber {
    // 二分实验 数的三次方根 https://www.acwing.com/problem/content/792/
    public static void main(String[] args) throws IOException {
        double l = -100, r = 100;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        double n = Double.parseDouble(bufferedReader.readLine());
        while (r - l > 1e-8) {
            double mid = (l + r) / 2;
            if (mid * mid * mid <= n) {
                l = mid;
            } else {
                r = mid;
            }
        }
        System.out.printf("%,5f\n", l);
    }
}
