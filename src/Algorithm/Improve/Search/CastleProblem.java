package Algorithm.Improve.Search;

/**
 * 城堡问题
 * <a href="https://www.acwing.com/problem/content/1100/">...</a>
 */
public class CastleProblem {
    // 输入参数
    private final int n, m;
    private final int[][] room;
    // 过程参数
    private final boolean[][] st;
    private final int[] dx = new int[]{0, -1, 0, 1}, dy = new int[]{-1, 0, 1, 0};
    // 结果参数
    private int cnt = 0, area = 0;

    public CastleProblem(int n, int m, int[][] room) {
        this.n = n;
        this.m = m;
        this.room = room;
        this.st = new boolean[n][m];
        floodFill();
    }


    /**
     * bfs 计算连通块面积
     *
     * @param x 房子横坐标
     * @param y 房子纵坐标
     * @return 连通块面积
     */
    private int bfs(int x, int y) {
        int ans = 0;

        int hh = 0, tt = -1;
        int[][] q = new int[(n + 1) * (m + 1)][2];
        q[++tt] = new int[]{x, y};
        st[x][y] = true;

        while (hh <= tt) {
            int[] tmp = q[hh++];
            ans++;
            for (int i = 0; i < 4; i++) {
                if (((room[tmp[0]][tmp[1]] >> i) & 1) == 1) continue;
                x = tmp[0] + dx[i];
                y = tmp[1] + dy[i];
                if (st[x][y]) continue;
                q[++tt] = new int[]{x, y};
                st[x][y] = true;
            }
        }
        return ans;
    }

    private void floodFill() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!st[i][j]) {
                    area = Math.max(area, bfs(i, j));
                    cnt++;
                }
            }
        }
    }

    public int getCnt() {
        return cnt;
    }

    public int getArea() {
        return area;
    }
}
