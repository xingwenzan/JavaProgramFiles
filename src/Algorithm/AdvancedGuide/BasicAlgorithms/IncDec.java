package Algorithm.AdvancedGuide.BasicAlgorithms;

/**
 * 增减序列
 * <a href="https://www.acwing.com/problem/content/102/">...</a>
 */
public class IncDec {
    private final int n;
    private final int[] s;
    private long cnt, ans;

    public IncDec(int n, int[] s) {
        this.n = n;
        this.s = s;
        diff();
    }

    private void diff() {
        for (int i = n - 1; i > 0; i--) {
            s[i] -= s[i - 1];
        }
        long inc = 0, dec = 0;
        for (int i = 1; i < n; i++) {
            if (s[i] > 0) inc += s[i];
            else dec -= s[i];
        }
        cnt = Math.max(inc, dec);
        ans = Math.abs(inc - dec) + 1;
    }

    public long getCnt() {
        return cnt;
    }

    public long getAns() {
        return ans;
    }
}
