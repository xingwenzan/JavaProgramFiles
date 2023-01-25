package Algorithm.AlgorithmTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    // 前缀和、差分(TLE)
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(s[0]); // 差分
        int m = Integer.parseInt(s[1]);
        String[] lst = bufferedReader.readLine().split(" ");
        //int[] ans = BasicAlgorithms.prefixSum(lst); // 前缀和
        int[] add = new int[n + 1];
        Arrays.fill(add, 0);
        for (int i = 0; i < m; i++) {
            s = bufferedReader.readLine().split(" ");
            int l = Integer.parseInt(s[0]);
            int r = Integer.parseInt(s[1]);
            int c = Integer.parseInt(s[2]);
            //System.out.println(ans[r] - ans[l - 1]); // 前缀和
            add = BasicAlgorithms.finiteDifference(add, l - 1, r - 1, c);
        }
        int t = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            t += add[i];
            ans = Integer.parseInt(lst[i]) + t;
            System.out.printf("%d ", ans);
        }

    }

/*
// 快排、归并
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        int n = Integer.parseInt(s + ""); // 读单个字符并转数字
        //System.out.println(i+1);
        String[] strings = bufferedReader.readLine().split(" ");
        int[] lst = new int[n];
        for (int i = 0; i < n; i++) {
            lst[i] = Integer.parseInt(strings[i]);
        }
        //BasicAlgorithms.quickSort(lst, 0, n - 1); // 快速排序
//        BasicAlgorithms.mergeSort(lst, 0, n - 1); // 归并排序
//        for (int i = 0; i < n; i++) {
//            System.out.print(lst[i] + " ");
//        }
        long res = BasicAlgorithms.numberOfReversedPairs(lst, 0, n - 1); // 逆序对计数
        System.out.println(res);
    }
 */

/*
// 快选
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = bufferedReader.readLine().split(" ");
        int[] input = new int[2];
        for (int i = 0; i < 2; i++) {
            input[i] = Integer.parseInt(s[i]);
        }
        int n = input[0];
        int k = input[1];
        String[] strings = bufferedReader.readLine().split(" ");
        int[] lst = new int[n];
        for (int i = 0; i < n; i++) {
            lst[i] = Integer.parseInt(strings[i]);
        }
        int ans = BasicAlgorithms.quickChoose(lst, 0, n - 1, k); // 快速排序
        System.out.println(ans);
    }
 */

/*
// 高精度算法
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String a = bufferedReader.readLine();
        String b = bufferedReader.readLine();
        //String c = BasicAlgorithms.highPrecisionAddition(a, b); // 高精度加法
        //String c = BasicAlgorithms.highPrecisionSubtraction(a, b); // 高精度减法
        //String c = BasicAlgorithms.highPrecisionMultiplication(a, b); // 高精度乘法
        // System.out.println(c);
        String[] c = BasicAlgorithms.highPrecisionDivision(a, b); // 高精度除法
        System.out.println(c[0]);
        System.out.println(c[1]);
    }
 */

}
