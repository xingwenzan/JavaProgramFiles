package Algorithm.Improve.Search;

/**
 * 马走日
 * <a href="https://www.acwing.com/problem/content/1118/">...</a>
 */
public class HorseWalkingDay {
    private final int n, m, sx, sy;
    private final int[] dx = {1, 1, -1, -1, 2, 2, -2, -2}, dy = {2, -2, 2, -2, 1, -1, 1, -1};
    private final boolean[][] map;
    private int ans = 0;

    public HorseWalkingDay(int n, int m, int sx, int sy) {
        this.m = m;
        this.n = n;
        this.sx = sx;
        this.sy = sy;
        map = new boolean[n][m];
    }

    private void dfs(int x, int y, int cnt) {
        if (cnt == n * m) {
            ans++;
            return;
        }
        map[x][y] = true;
        for (int i = 0; i < 8; i++) {
            int tx = x + dx[i], ty = y + dy[i];
            if (tx < 0 || ty < 0 || tx >= n || ty >= m) continue;
            if (map[tx][ty]) continue;
            dfs(tx, ty, cnt + 1);
        }
        map[x][y] = false;
    }

    public int getAns() {
        dfs(sx, sy, 1);
        return ans;
    }
}
