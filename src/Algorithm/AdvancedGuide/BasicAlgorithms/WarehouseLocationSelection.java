package Algorithm.AdvancedGuide.BasicAlgorithms;

import java.util.Arrays;

/**
 * 货仓选址
 * <a href="https://www.acwing.com/problem/content/106/">...</a>
 */
public class WarehouseLocationSelection {
    private final int n;
    private final int[] a;

    public WarehouseLocationSelection(int n, int[] a) {
        this.n = n;
        this.a = a;
    }

    public int getAns() {
        Arrays.sort(a);
        int t = a[n / 2], ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.abs(a[i] - t);
        }
        return ans;
    }
}
