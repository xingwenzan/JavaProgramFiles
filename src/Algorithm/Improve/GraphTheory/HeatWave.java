package Algorithm.Improve.GraphTheory;

import java.util.Arrays;

/**
 * 热浪
 * <a href="https://www.acwing.com/problem/content/1131/">...</a>
 */
public class HeatWave {
    private final int t, c, start, end;
    private final int[] h, e, ne, w, dist;
    private int idx = 0;

    public HeatWave(int t, int c, int start, int end) {
        this.t = t;
        this.c = c;
        this.start = start;
        this.end = end;
        h = new int[t + 10];
        Arrays.fill(h, -1);
        e = new int[c * 2 + 10];
        ne = new int[c * 2 + 10];
        w = new int[c * 2 + 10];
        dist = new int[t + 10];
        Arrays.fill(dist, (int) 1e9);
        dist[start] = 0;
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

    private void spfa() {
        int[] q = new int[c * 3];
        int hh = 0, tt = 1;
        boolean[] st = new boolean[t + 10];
        q[0] = start;
        st[start] = true;

        while (hh < tt) {
            int fa = q[hh++];
            st[fa] = false;

            for (int i = h[fa]; i != -1; i = ne[i]) {
                int son = e[i];
                if (dist[son] > dist[fa] + w[i]) {
                    dist[son] = dist[fa] + w[i];
                    if (!st[son]) {
                        q[tt++] = son;
                        st[son] = true;
                    }
                }
            }
        }
    }

    public int getAns() {
        spfa();
        return dist[end];
    }
}
