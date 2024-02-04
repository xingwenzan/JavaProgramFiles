package Algorithm.Improve.GraphTheory;

public class MinimumCost {
    private final int n;
    private int start, end;
    private final double[][] g;
    private final double[] d;
    private final boolean[] st;

    public MinimumCost(int n) {
        this.n = n;
        g = new double[n + 10][n + 10];
        d = new double[n + 10];
        st = new boolean[n + 10];
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    void add(int x, int y, int z) {
        g[x][y] = g[y][x] = Math.max(g[x][y], (double) (100 - z) / 100);
    }

    private void dijkstra() {
        d[start] = 1;
        for (int i = 0; i < n; i++) {
            int t = -1;
            for (int j = 1; j <= n; j++) {
                if ((!st[j]) && (t == -1 || d[j] > d[t])) t = j;
            }
            st[t] = true;
            for (int j = 1; j <= n; j++) {
                d[j] = Math.max(d[j], d[t] * g[t][j]);
            }
        }
    }

    public double getAns() {
        dijkstra();
        return d[end];
    }
}
