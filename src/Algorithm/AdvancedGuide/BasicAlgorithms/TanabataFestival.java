package Algorithm.AdvancedGuide.BasicAlgorithms;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 七夕祭
 * <a href="https://www.acwing.com/problem/content/107/">...</a>
 */
public class TanabataFestival {
    private final int n, m, t;
    private final int[] row, col;
    private final HashMap<String, Long> ans = new HashMap<>();

    public TanabataFestival(int n, int m, int t) {
        this.n = n;
        this.m = m;
        this.t = t;
        row = new int[n];
        col = new int[m];
    }

    public void add(int x, int y) {
        row[x - 1]++;
        col[y - 1]++;
    }

    private int count(int num, int[] lst) {
        if (t % num != 0) return -1;
        int res = 0, tmp = t / num;
        int[] s = new int[num];
        for (int i = 1; i < num; i++) s[i] = s[i - 1] + lst[i - 1];
        for (int i = 1; i < num; i++) s[i] -= i * tmp;
        Arrays.sort(s);
        tmp = s[num / 2];
        for (int i = 0; i < num; i++) {
            res += Math.abs(s[i] - tmp);
        }
        return res;
    }

    private void work() {
        long r = count(n, row), c = count(m, col);
        if (r != -1 && c != -1) ans.put("both", r + c);
        else if (r != -1) ans.put("row", r);
        else if (c != -1) ans.put("column", c);
        else ans.put("impossible", (long) -1);
    }

    public HashMap<String, Long> getAns() {
        work();
        return ans;
    }
}
