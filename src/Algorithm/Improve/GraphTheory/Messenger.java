package Algorithm.Improve.GraphTheory;

import java.util.Arrays;

public class Messenger {
    private final int n, INF = (int) 1e9 + 10;
    private final int[][] d;
    private int ans = 0;

    public Messenger(int n) {
        this.n = n;
        d = new int[n + 10][n + 10];
        for (int i = 0; i < n + 10; i++) {
            Arrays.fill(d[i], INF);
            d[i][i] = 0;
        }
    }

    void add(int p1, int p2, int w) {
        d[p1][p2] = d[p2][p1] = w;
    }

    private void count() {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, d[1][i]);
            if (ans == INF) break;
        }
    }

    public int getAns() {
        count();
        return ans != INF ? ans : -1;
    }
}
