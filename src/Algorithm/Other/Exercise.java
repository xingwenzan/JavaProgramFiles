package Algorithm.Other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Exercise {
    // 题目：健身；链接：https://www.acwing.com/problem/content/4797/
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        String[] s = bufferedReader.readLine().split(" ");
        String[] out = {"chest", "biceps", "back"};
        int[] sportTime = {0, 0, 0};
        for (int i = 0; i < n; i++) {
            int time = Integer.parseInt(s[i]);
            sportTime[i % 3] += time;
        }
        int ans = sportTime[0];
        String outAns = out[0];
        for (int i = 0; i < 3; i++) {
            if (ans <= sportTime[i]) {
                outAns = out[i];
                ans = sportTime[i];
            }
        }
        System.out.println(outAns);
    }
}
