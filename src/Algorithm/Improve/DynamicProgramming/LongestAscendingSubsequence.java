package Algorithm.Improve.DynamicProgramming;

import java.util.Arrays;

public class LongestAscendingSubsequence {
    // 怪盗基德的滑翔翼 https://www.acwing.com/problem/content/1019/
    private final int N = 110;
    private final int[] e = new int[N], f = new int[N];

    private void add(String[] strings, int length) {
        for (int i = 0; i < length; i++) {
            e[i] = Integer.parseInt(strings[i]);
        }
    }

    public int HangGliding(String[] strings, int length) {
        add(strings, length);
        int ans = 0;
        Arrays.fill(f, 1);
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if (e[i] < e[j]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            ans = Math.max(ans, f[i]);
        }
        Arrays.fill(f, 1);
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if (e[i] > e[j]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }
}
