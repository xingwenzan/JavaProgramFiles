package Algorithm.Improve.Search;

import java.util.Arrays;

/**
 * 生日蛋糕
 * <a href="https://www.acwing.com/problem/content/170/">...</a>
 */
public class BirthdayCake {
    private final int n, m, M = 25, INF = (int) 1e9;
    private final int[] R = new int[M], H = new int[M], minV = new int[M], minS = new int[M];
    private int ans = INF;

    public BirthdayCake(int n, int m) {
        this.n = n;
        this.m = m;
        Arrays.fill(R, INF);
        Arrays.fill(H, INF);
        for (int i = 1; i <= m; i++) {
            minV[i] = minV[i - 1] + i * i * i;   // 前 i 层最小总体积
            minS[i] = minS[i - 1] + 2 * i * i;   // 前 i 层最小总侧面积
        }
    }

    /**
     * @param u 当前层数
     * @param v 当前层数之下（即 u+1、u+2……m 层）总体积
     * @param s 当前层数之下（即 u+1、u+2……m 层）总面积，含底面积
     */
    private void dfs(int u, int v, int s) {
        if (v + minV[u] > n) return;
        if (s + minS[u] >= ans) return;
        if (s + 2 * (n - v) / R[u + 1] >= ans) return;

        if (u == 0) {
            if (v == n) ans = s;
            return;
        }

        for (int r = Math.min(R[u + 1] - 1, (int) Math.sqrt(n - v)); r >= u; r--) {
            for (int h = Math.min(H[u + 1] - 1, (n - v) / r / r); h >= u; h--) {
                int t = u == m ? r * r : 0;
                R[u] = r;
                H[u] = h;
                dfs(u - 1, v + r * r * h, s + t + 2 * r * h);
            }
        }
    }

    public int getAns() {
        dfs(m, 0, 0);
        if (ans == INF) return 0;
        return ans;
    }
}
