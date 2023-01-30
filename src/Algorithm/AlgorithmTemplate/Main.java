package Algorithm.AlgorithmTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    // 离散化
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);
        int[] xs = new int[n];
        int[] cs = new int[n];
        int[] ls = new int[m];
        int[] rs = new int[m];
        List<Integer> tmp = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            strings = bufferedReader.readLine().split(" ");
            xs[i] = Integer.parseInt(strings[0]);
            cs[i] = Integer.parseInt(strings[1]);
            tmp.add(Integer.parseInt(strings[0]));
        }
        for (int i = 0; i < m; i++) {
            strings = bufferedReader.readLine().split(" ");
            ls[i] = Integer.parseInt(strings[0]);
            rs[i] = Integer.parseInt(strings[1]);
            tmp.add(Integer.parseInt(strings[0]));
            tmp.add(Integer.parseInt(strings[1]));
        }
        int[] points = tmp.stream().mapToInt(Integer::valueOf).toArray();
        points = BasicAlgorithms.discretization(points);
        int[] lst = new int[n + 2 * m];
        Arrays.fill(lst, 0);
        for (int i = 0; i < n; i++) {
            int x = BasicAlgorithms.dichotomizeLeft(xs[i], points);
            lst[x] += cs[i];
        }
        lst = BasicAlgorithms.prefixSum(lst);
        for (int i = 0; i < m; i++) {
            int l = BasicAlgorithms.dichotomizeLeft(ls[i], points) + 1;
            int r = BasicAlgorithms.dichotomizeLeft(rs[i], points) + 1;
            System.out.println(lst[r] - lst[l - 1]);
        }
    }

    /*
    // 小数二分
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        double n = Double.parseDouble(bufferedReader.readLine());
        String out = BasicAlgorithms.dichotomizeFloat(n,3,6);
        System.out.println(out);
    }

     */

    /*
    // 整数二分
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
            int l = BasicAlgorithms.dichotomizeRight(num,lst);
            int r = BasicAlgorithms.dichotomizeLeft(num,lst);
            if (lst[l] != num || lst[r] != num) {
                System.out.println("-1 -1");
            } else {
                System.out.printf("%d %d%n", l, r);
            }
        }
    }

    */

    /*
    // 位运算
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        String[] strings = bufferedReader.readLine().split(" ");
        int[] ans = new int[n];
        Arrays.fill(ans, 0);
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(strings[i]);
            while (x != 0) {
//                if ((x&1)==1){ // 暴力版
//                    ans[i]++;
//                }
//                x = x>>1; // 暴力版
                x = x - BasicAlgorithms.lowbit(x); // lowbit 版
                ans[i]++;
            }
            System.out.printf("%d ", ans[i]);
        }
    }

    */

    /*
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

    */

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
