package Algorithm.Improve.GraphTheory;

import java.util.Arrays;

/**
 * 香甜的黄油
 * <a href="https://www.acwing.com/problem/content/1129/">...</a>
 */
public class SweetButter {
    private final int P;
    private final int INF = (int) 2e9;
    private final int[] h, e, ne, w;
    private int idx = 0, ans = INF;
    private final int[] d, cows, q;
    private final boolean[] st;

    public SweetButter(int p, int c, int[] cows) {
        P = p;

        this.cows = cows;
        d = new int[P + 10];
        st = new boolean[P + 10];
        q = new int[c * 3];

        h = new int[P + 10];
        Arrays.fill(h, -1);
        e = new int[c * 2 + 10];
        ne = new int[c * 2 + 10];
        w = new int[c * 2 + 10];
    }

    private void add1(int fa, int son, int weight) {
        e[idx] = son;
        w[idx] = weight;
        ne[idx] = h[fa];
        h[fa] = idx++;
    }

    void add2(int p1, int p2, int weight) {
        add1(p1, p2, weight);
        add1(p2, p1, weight);
    }

    int spfa(int start) {
        Arrays.fill(d, INF);
        Arrays.fill(st, false);

        q[0] = start;
        st[start] = true;
        int hh = 0, tt = 1;
        d[start] = 0;

        while (hh < tt) {
            int fa = q[hh++];
            st[fa] = false;

            for (int i = h[fa]; i != -1; i = ne[i]) {
                int son = e[i];
                if (d[son] > d[fa] + w[i]) {
                    d[son] = d[fa] + w[i];
                    if (!st[son]) {
                        q[tt++] = son;
                        st[son] = true;
                    }
                }
            }
        }

        int res = 0;
        for (int i : cows) {
            if (d[i] == INF) return INF;
            res += d[i];
        }
        return res;
    }

    public int getAns() {
        for (int i = 1; i <= P; i++) {
            ans = Math.min(ans, spfa(i));
        }
        return ans;
    }
}
