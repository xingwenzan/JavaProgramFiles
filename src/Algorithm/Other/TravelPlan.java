package Algorithm.Other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TravelPlan {
    // 题目：出行计划；链接：https://www.acwing.com/problem/content/description/4458/
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nmk = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(nmk[0]);
        int m = Integer.parseInt(nmk[1]);
        int k = Integer.parseInt(nmk[2]);
        int[] ans = new int[200010];
        for (int i = 0; i < n; i++) {
            String[] strings = bufferedReader.readLine().split(" ");
            int t = Integer.parseInt(strings[0]);
            int c = Integer.parseInt(strings[1]);
            int l = t - c + 1 - k;
            int r = t - k;
            if (r > 0) {
                ans[Math.max(1, l)]++;
                ans[r + 1]--;
            }
        }
        for (int i = 1; i < 200010; i++) {
            ans[i] += ans[i - 1];
        }
        while (m-- > 0) {
            int q = Integer.parseInt(bufferedReader.readLine());
            System.out.println(ans[q]);
        }
    }
}
