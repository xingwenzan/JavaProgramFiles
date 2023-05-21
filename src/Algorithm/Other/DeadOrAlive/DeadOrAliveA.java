package Algorithm.Other.DeadOrAlive;

import java.util.Arrays;
import java.util.Scanner;

public class DeadOrAliveA {
    //题目：死或生；链接：https://www.acwing.com/problem/content/submission/4794/
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String delimeter = " ";
        int[] first = {0, 0};
        int[] second = {0, 0};

        for (int i = 0; i < n; i++) {
            String enter = scanner.nextLine(); // 读取字符串
            String[] enterByStr = enter.split(delimeter); // 字符串分割
            int[] enterByInt = Arrays.stream(enterByStr)
                    .mapToInt(Integer::parseInt)
                    .toArray(); // 字符串数组转数字数组
            if (enterByInt[0] == 1) {
                first[0] += enterByInt[1];
                first[1] += enterByInt[2];
            } else if (enterByInt[0] == 2) {
                second[0] += enterByInt[1];
                second[1] += enterByInt[2];
            }


        }
        if (first[0] >= first[1]) {
            System.out.println("LIVE");
        } else {
            System.out.println("DEAD");
        }

        if (second[0] >= second[1]) {
            System.out.println("LIVE");
        } else {
            System.out.println("DEAD");
        }

    }
}
