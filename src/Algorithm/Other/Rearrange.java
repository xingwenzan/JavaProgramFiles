package Algorithm.Other;

import java.util.Arrays;
import java.util.Scanner;

public class Rearrange {
    // 题目: 重新排序; 链接: https://www.acwing.com/problem/content/4658/
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String delimeter = " "; // 分隔符设为空格（使用空格分割）
        String a1 = scanner.nextLine();
        String[] a2 = a1.split(delimeter);
        int[] a = Arrays.stream(a2)
                .mapToInt(Integer::parseInt)
                .toArray(); // 字符串数组转数字数组
        int m = scanner.nextInt();
        scanner.nextLine();
        int[] w = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            w[i] = 0;
        }
        for (int i = 0; i < m; i++) {
            String s1 = scanner.nextLine();
            String[] s2 = s1.split(delimeter);
            int[] s = Arrays.stream(s2)
                    .mapToInt(Integer::parseInt)
                    .toArray(); // 字符串数组转数字数组
            w[s[0] - 1] += 1;
            w[s[1]] -= 1;
        }
        long oldNum = 0L;
        for (int i = 0; i < n; i++) {
            w[i + 1] += w[i];
            oldNum += (long) w[i] * a[i];
        }
        w = Arrays.copyOf(w, w.length - 1);
        Arrays.sort(w);
        Arrays.sort(a);
        long newNum = 0L;
        for (int i = 0; i < n; i++) {
            newNum += (long) w[i] * a[i];
        }
        System.out.println(newNum - oldNum);

    }
}
