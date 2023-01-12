package Algorithm.AlgorithmTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

//    public static void main(String[] args) throws IOException {// 快排
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        String s = bufferedReader.readLine();
//        int n = Integer.parseInt(s.charAt(0) + ""); // 读单个字符并转数字
//        //System.out.println(i+1);
//        String[] strings = bufferedReader.readLine().split(" ");
//        int[] lst = new int[n];
//        for (int i = 0; i < n; i++) {
//            lst[i] = Integer.parseInt(strings[i]);
//        }
//        BasicAlgorithms.quickSort(lst, 0, n - 1); // 快速排序
//        for (int i = 0; i < n; i++) {
//            System.out.print(lst[i] + " ");
//        }
//    }

    public static void main(String[] args) throws IOException {// 快选
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s[] = bufferedReader.readLine().split(" ");
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
}
