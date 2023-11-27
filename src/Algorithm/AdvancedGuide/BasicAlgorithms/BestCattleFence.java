package Algorithm.AdvancedGuide.BasicAlgorithms;

/**
 * 最佳牛围栏
 * 题目 <a href="https://www.acwing.com/problem/content/104/">...</a>
 * 详解 <a href="https://www.acwing.com/video/86/">...</a>
 */
public class BestCattleFence {
    private final int n, m;
    private final int[] cows;
    private double ans = 0.0;

    public BestCattleFence(int n, int m, int[] cows) {
        this.n = n;
        this.m = m;
        this.cows = cows;
    }

    private boolean judge(double arg) {
        double[] s = new double[n + 1];
        s[0] = 0;
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + cows[i - 1] - arg;
        double minv = 0;
        for (int i = 0, j = m; j <= n; i++, j++) {
            minv = Math.min(minv, s[i]);
            if (s[j] >= minv) return true;
        }
        return false;
    }

    private void count() {
        double l = 0, r = 2000;
        while (r - l >= 1e-5) {
            double mid = (r + l) / 2;
            if (judge(mid)) l = mid;
            else r = mid;
        }
        ans = r;
    }

    public double getAns() {
        count();
        return ans;
    }
}
