package Algorithm.Other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DeleteSequence {
    // 题目：删除序列；链接：https://www.acwing.com/problem/content/4799/
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedReader.readLine();
        long[] value = new long[100010];
        long[] score = new long[100010];
        String[] strings = bufferedReader.readLine().split(" ");
        for (String s : strings) {
            int i = Integer.parseInt(s);
            value[i] += i;
        }
        for (int i = 1; i < 100010; i++) {
            score[i] = Math.max(score[i - 1], score[Math.max(0, i - 2)] + value[i]);
        }
        System.out.println(score[100010 - 1]);
    }
}
