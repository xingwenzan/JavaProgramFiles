package Algorithm.Improve.Search;

import java.util.Arrays;

/**
 * 抓住那头牛
 * <a href="https://www.acwing.com/problem/content/1102/">...</a>
 */
public class CatchThatCow {
    private final int N = (int) 1e5 + 10;
    private final int n, k;
    private final int[] axis = new int[N];   // 数轴

    public CatchThatCow(int n, int k) {
        this.n = n;
        this.k = k;
        Arrays.fill(axis, -1);
        count();
    }

    private void count() {
        int[] q = new int[N];
        q[0] = n;
        int hh = 0, tt = 0;

        axis[n] = 0;
        while (hh <= tt) {
            int tmp = q[hh++];
            int[] next = new int[]{tmp + 1, tmp - 1, tmp * 2};
            for (int nt : next) {
                if (nt < 0 || nt >= N) continue;
                if (axis[nt] != -1) continue;

                axis[nt] = axis[tmp] + 1;

                if (nt == k) return;
                q[++tt] = nt;
            }
        }
    }

    public int getAns() {
        return axis[k];
    }
}
