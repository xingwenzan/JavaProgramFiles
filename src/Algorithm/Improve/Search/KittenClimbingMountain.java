package Algorithm.Improve.Search;

import java.util.Arrays;

/**
 * 小猫爬山
 * <a href="https://www.acwing.com/problem/content/167/">...</a>
 */
public class KittenClimbingMountain {
    private final int n, w;
    private final int[] cats, sum;
    private int ans;

    public KittenClimbingMountain(int n, int w, int[] cats) {
        this.n = n;
        this.w = w;
        this.cats = cats;
        sum = new int[n];
        ans = n;
        Arrays.sort(this.cats);
        for (int start = 0, end = n - 1; start < end; start++, end--) {
            int temp = this.cats[end];
            this.cats[end] = this.cats[start];
            this.cats[start] = temp;
        }
    }

    void dfs(int u, int cnt) {
        if (cnt >= ans) return;
        if (u == n) {
            ans = cnt;
            return;
        }

        for (int i = 0; i < cnt; i++) {
            if (sum[i] + cats[u] <= w) {
                sum[i] += cats[u];
                dfs(u + 1, cnt);
                sum[i] -= cats[u];
            }
        }

        sum[cnt] = cats[u];
        dfs(u + 1, cnt + 1);
        sum[cnt] = 0;
    }

    public int getAns() {
        dfs(0, 0);
        return ans;
    }
}
