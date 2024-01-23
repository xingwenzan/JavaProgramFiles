package Algorithm.AdvancedGuide.BasicAlgorithms;

import java.util.Arrays;

/**
 * 倍增
 * 天才ACM
 * <a href="https://www.acwing.com/problem/content/111/">...</a>
 */
public class Multiply {
    private final int n, m;
    private final long t;
    private final int[] lst;

    public Multiply(int n, int m, long t, int[] lst) {
        this.n = n;
        this.m = m;
        this.t = t;
        this.lst = lst;
    }

    private long pow(long x) {
        return x * x;
    }

    private long get(int l, int r) {
        int k = r - l;
        int[] tmp = new int[k];
        for (int i = 0, j = l; j < r; i++, j++) tmp[i] = lst[j];
        Arrays.sort(tmp);
        long sum = 0;
        for (int i = 0, j = k; i < m && i < j; i++, j--) sum += pow(tmp[j - 1] - tmp[i]);
        return sum;
    }

    private int count() {
        int ans = 0;
        int start = 0, end = 0;
        while (end < n) {
            int len = 1;
            while (len != 0) {
                if (end + len <= n && get(start, end + len) <= t) {
                    end += len;
                    len <<= 1;
                } else len >>= 1;
            }
            start = end;
            ans++;
        }
        return ans;
    }

    public int getAns() {
        return count();
    }
}
