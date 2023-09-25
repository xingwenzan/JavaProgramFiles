package Algorithm.Improve.Search.FloodFill;


/**
 * 池塘计数
 * <a href="https://www.acwing.com/problem/content/1099/">...</a>
 */
public class PondCount {
    private final String[] strings;
    private final int n, m;
    private final boolean[][] st;
    private final int[][] q;
    private int ans = 0;

    public PondCount(String[] strings, int n, int m) {
        this.strings = strings;
        this.n = n;
        this.m = m;
        st = new boolean[n + 1][m + 1];
        q = new int[(n + 1) * (m + 1)][2];
    }

    private void bfs(int x, int y) {
        int hh = 0, tt = 0;
        q[0] = new int[]{x, y};
        st[x][y] = true;

        while (hh <= tt) {
            int[] tmp = q[hh++];
            for (int i = tmp[0] - 1; i <= tmp[0] + 1; i++) {
                for (int j = tmp[1] - 1; j <= tmp[1] + 1; j++) {
                    if (i == tmp[0] && j == tmp[1]) continue;
                    if (i < 0 || j < 0 || i >= n || j >= m) continue;
                    if (strings[i].charAt(j) == '.' || st[i][j]) continue;

                    q[++tt] = new int[]{i, j};
                    st[i][j] = true;
                }
            }
        }
    }

    public int getAns() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (strings[i].charAt(j) == 'W' && !st[i][j]) {
                    bfs(i, j);
                    ans++;
                }
            }
        }
        return ans;
    }
}
