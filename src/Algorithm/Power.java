package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Power {
    // 题目：乘方；链接：https://www.acwing.com/problem/content/4731/
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = bufferedReader.readLine().split(" ");
        int a = Integer.parseInt(strings[0]);
        int b = Integer.parseInt(strings[1]);
        long ans = 1;
        for (int i = 0; i < b; i++) {
            ans *= a;
            if (ans > 1000000000) {
                System.out.println(-1);
                break;
            }
            if (i == b - 1) {
                System.out.println(ans);
            }
        }
    }
}
