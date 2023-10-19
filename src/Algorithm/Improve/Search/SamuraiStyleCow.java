package Algorithm.Improve.Search;

import java.util.Arrays;

/**
 * 武士风度的牛
 * <a href="https://www.acwing.com/problem/content/190/">...</a>
 */
public class SamuraiStyleCow {
    private final int row, col;
    private final String[] lst;
    private final int[][] dis;
    private int x = -1, y = -1;
    private final int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2}, dy = {1, 2, 2, 1, -1, -2, -2, -1};

    public SamuraiStyleCow(int row, int col, String[] lst) {
        this.row = row;
        this.col = col;
        this.lst = lst;
        dis = new int[row][col];
        for (int i = 0; i < row; i++) {
            Arrays.fill(dis[i], -1);
        }
        for (int i = 0; i < row; i++) {
            if (x == -1) {
                for (int j = 0; j < col; j++) {
                    if (lst[i].charAt(j) == 'K') {
                        x = i;
                        y = j;
                    }
                }
            } else break;
        }
    }

    public int bfs() {
        dis[x][y] = 0;
        int[][] q = new int[(row + 1) * (col + 1)][2];
        q[0] = new int[]{x, y};
        int hh = 0, tt = 0;
        while (hh <= tt) {
            int[] tmp = q[hh++];
            for (int i = 0; i < 8; i++) {
                int nx = tmp[0] + dx[i], ny = tmp[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >= row || ny >= col) continue;
                if (lst[nx].charAt(ny) == '*') continue;
                if (dis[nx][ny] != -1) continue;
                if (lst[nx].charAt(ny) == 'H') return dis[tmp[0]][tmp[1]] + 1;
                dis[nx][ny] = dis[tmp[0]][tmp[1]] + 1;
                q[++tt] = new int[]{nx, ny};
            }
        }
        return -1;
    }
}
