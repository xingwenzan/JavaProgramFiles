package Algorithm.Improve.Search;

/**
 * 迷宫
 * <a href="https://www.acwing.com/problem/content/1114/">...</a>
 */
public class Maze {
    private final int n;
    private final String[] map;
    private final int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    private final boolean[][] st;

    public Maze(int n, String[] map) {
        this.n = n;
        this.map = map;
        st = new boolean[n][n];
    }

    public boolean dfs(int sx, int sy, int ex, int ey) {
        if (map[sx].charAt(sy) == '#' || map[ex].charAt(ey) == '#') return false;
        if (sx == ex && sy == ey) return true;
        boolean ans = false;
        for (int i = 0; i < 4; i++) {
            int x = sx + dx[i], y = sy + dy[i];
            if (x < 0 || y < 0 || x >= n || y >= n) continue;
            if (map[x].charAt(y) == '#') continue;
            if (st[x][y]) continue;
            st[x][y] = true;
            ans = (ans || dfs(x, y, ex, ey));
        }
        return ans;
    }
}
