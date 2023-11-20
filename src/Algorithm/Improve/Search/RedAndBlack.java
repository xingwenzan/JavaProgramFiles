package Algorithm.Improve.Search;

/**
 * 红与黑
 * <a href="https://www.acwing.com/problem/content/1115/">...</a>
 */
public class RedAndBlack {
    private final int w, h;
    private final String[] s;
    private final boolean[][] st;
    private final int[] dx = {0, -1, 0, 1}, dy = {-1, 0, 1, 0};
    private final int ans;

    public RedAndBlack(int w, int h, String[] s) {
        this.w = w;
        this.h = h;
        this.s = s;
        st = new boolean[h][w];
        int sy = -1;
        int sx = -1;
        for (int i = 0; i < h; i++) {
            if (sx != -1) break;
            for (int j = 0; j < w; j++) {
                if (s[i].charAt(j) == '@') {
                    sx = i;
                    sy = j;
                    break;
                }
            }
        }
        ans = dfs(sx, sy);
    }

    private int dfs(int x, int y) {
        int res = 1;
        st[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int tx = x + dx[i], ty = y + dy[i];
            if (tx < 0 || ty < 0 || tx >= h || ty >= w) continue;
            if (st[tx][ty]) continue;
            if (s[tx].charAt(ty) == '#') continue;
            res += dfs(tx, ty);
        }
        return res;
    }

    public int getAns() {
        return ans;
    }
}
