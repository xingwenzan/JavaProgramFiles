package Algorithm.AlgorithmTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

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


//    public static void main(String[] args) throws IOException {// 快排、归并
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        String s = bufferedReader.readLine();
//        int n = Integer.parseInt(s + ""); // 读单个字符并转数字
//        //System.out.println(i+1);
//        String[] strings = bufferedReader.readLine().split(" ");
//        int[] lst = new int[n];
//        for (int i = 0; i < n; i++) {
//            lst[i] = Integer.parseInt(strings[i]);
//        }
//        //BasicAlgorithms.quickSort(lst, 0, n - 1); // 快速排序
////        BasicAlgorithms.mergeSort(lst, 0, n - 1); // 归并排序
////        for (int i = 0; i < n; i++) {
////            System.out.print(lst[i] + " ");
////        }
//        long res = BasicAlgorithms.numberOfReversedPairs(lst, 0, n - 1); // 逆序对计数
//        System.out.println(res);
//    }


//    public static void main(String[] args) throws IOException {// 快选
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        String[] s = bufferedReader.readLine().split(" ");
//        int[] input = new int[2];
//        for (int i = 0; i < 2; i++) {
//            input[i] = Integer.parseInt(s[i]);
//        }
//        int n = input[0];
//        int k = input[1];
//        String[] strings = bufferedReader.readLine().split(" ");
//        int[] lst = new int[n];
//        for (int i = 0; i < n; i++) {
//            lst[i] = Integer.parseInt(strings[i]);
//        }
//        int ans = BasicAlgorithms.quickChoose(lst, 0, n - 1, k); // 快速排序
//        System.out.println(ans);
//    }

}
