package Algorithm;

import java.util.Arrays;
import java.util.Scanner;

public class CreditGPA {
    // 题目: 学分绩点; 链接: https://www.acwing.com/problem/content/description/3446/
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt(); // 读取数字
        scanner.nextLine();

        String delimeter = " "; // 分隔符设为空格（使用空格分割）

        String credits = scanner.nextLine(); // 读取字符串
        String[] creditsByStr = credits.split(delimeter); // 字符串分割
        int[] creditsByInt = Arrays.stream(creditsByStr)
                .mapToInt(Integer::parseInt)
                .toArray(); // 字符串数组转数字数组

        String scores = scanner.nextLine(); // 读取字符串
        String[] scoresByStr = scores.split(delimeter); // 字符串分割
        int[] scoresByInt = Arrays.stream(scoresByStr)
                .mapToInt(Integer::parseInt)
                .toArray(); // 字符串数组转数字数组

        double creditGPA = 0.0;
        for (int x = 0; x < num; x++) {
            creditGPA = creditGPA + creditsByInt[x] * scoreToGPa(scoresByInt[x]);
        }
        creditGPA = creditGPA / Arrays.stream(creditsByInt).sum();
        System.out.printf("%.2f\n",creditGPA);

    }
    private static double scoreToGPa(int score) {
        if (score >= 90) return 4.0;
        else if (score >= 85) return 3.7;
        else if (score >= 82) return 3.3;
        else if (score >= 78) return 3.0;
        else if (score >= 75) return 2.7;
        else if (score >= 72) return 2.3;
        else if (score >= 68) return 2.0;
        else if (score >= 64) return 1.5;
        else if (score >= 60) return 1.0;
        else return 0;
    }

}
