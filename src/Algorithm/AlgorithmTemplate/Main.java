package Algorithm.AlgorithmTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
/*  // 快排、归并
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

/*  // 快选
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

    // 高精度算法
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String a = new StringBuffer(bufferedReader.readLine()).reverse().toString();
        String b = new StringBuffer(bufferedReader.readLine()).reverse().toString();
        String c = BasicAlgorithms.highPrecisionAddition(a, b);
        System.out.println(c);
    }
}
