package Algorithm.Improve.Search;

import java.util.Arrays;

/**
 * 木棒
 * <a href="https://www.acwing.com/problem/content/169/">...</a>
 */
public class WoodenStick {
    private final int n;
    private final int[] w;
    private final boolean[] st;
    private int L_one;
    private final int L_sum;

    public WoodenStick(int n, int[] w) {
        this.n = n;
        this.w = w;
        Arrays.sort(w);
        for (int start = 0, end = n - 1; start < end; start++, end--) {
            int temp = w[end];
            w[end] = w[start];
            w[start] = temp;
        }
        st = new boolean[n];
        L_one = Arrays.stream(w).max().orElse(1);
        L_sum = Arrays.stream(w).sum();
    }

    private boolean dfs(int u, int cur, int start) {
        if (u * L_one == L_sum) return true;
        if (cur == L_one) return dfs(u + 1, 0, 0);

        for (int i = start; i < n; i++) {
            if (st[i] || cur + w[i] > L_one) continue;

            st[i] = true;
            if (dfs(u, cur + w[i], i + 1)) return true;
            st[i] = false;

            if (cur == 0 || cur + w[i] == L_one) return false;

            while (i + 1 < n && w[i + 1] == w[i]) {
                i++;
            }
        }
        return false;
    }

    public int getL_one() {
        while (L_sum % L_one != 0 || !dfs(0, 0, 0)) {
            L_one++;
        }
        return L_one;
    }
}
