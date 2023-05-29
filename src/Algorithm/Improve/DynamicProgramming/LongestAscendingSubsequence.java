package Algorithm.Improve.DynamicProgramming;

import java.util.Arrays;

public class LongestAscendingSubsequence {
    // 怪盗基德的滑翔翼 https://www.acwing.com/problem/content/1019/
    // 登山 https://www.acwing.com/problem/content/1016/
    private final int N = 1010;   // 怪盗基德的滑翔翼 110 登山 1010
    private final int[] e = new int[N];

    private void add(String[] strings, int length) {
        for (int i = 0; i < length; i++) {
            e[i] = Integer.parseInt(strings[i]);
        }
    }

    public int HangGliding(String[] strings, int length) {
        add(strings, length);
        int[] f = new int[N];
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


    public int Mountaineering(String[] strings, int length) {
        add(strings, length);
        int[] up = new int[N], down = new int[N];
        int ans = 0;
        Arrays.fill(up, 1);
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if (e[i] > e[j]) {
                    up[i] = Math.max(up[i], up[j] + 1);
                }
            }
        }
        Arrays.fill(down, 1);
        for (int i = length - 1; i >= 0; i--) {
            for (int j = length - 1; j > i; j--) {
                if (e[i] > e[j]) {
                    down[i] = Math.max(down[i], down[j] + 1);
                }
            }
        }
        for (int k = 0; k < length; k++) {
            ans = Math.max(ans, up[k] + down[k] - 1);
        }
        return ans;
    }
}
