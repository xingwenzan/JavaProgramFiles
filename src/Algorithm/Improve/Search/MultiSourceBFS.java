package Algorithm.Improve.Search;

import java.util.Arrays;

/**
 * 多源BFS
 * 矩阵距离
 * <a href="https://www.acwing.com/problem/content/175/">...</a>
 */
public class MultiSourceBFS {
    private final int row, col;
    private final int[][] d, q;
    private int hh = 0, tt = -1;

    public MultiSourceBFS(int row, int col, String[] lst) {
        this.row = row;
        this.col = col;
        d = new int[row][col];
        q = new int[(row + 1) * (col + 1)][2];
        for (int i = 0; i < row; i++) {
            Arrays.fill(d[i], -1);
            for (int j = 0; j < col; j++) {
                if (lst[i].charAt(j) == '1') {
                    d[i][j] = 0;
                    q[++tt] = new int[]{i, j};
                }
            }
        }
        bfs();
    }

    private void bfs() {
        int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, -1, 0, 1};
        while (hh <= tt) {
            int[] tmp = q[hh++];
            for (int i = 0; i < 4; i++) {
                int x = tmp[0] + dx[i], y = tmp[1] + dy[i];
                if (x < 0 || y < 0 || x >= row || y >= col) continue;
                if (d[x][y] != -1) continue;

                d[x][y] = d[tmp[0]][tmp[1]] + 1;
                q[++tt] = new int[]{x, y};
            }
        }
    }

    public int[][] getD() {
        return d;
    }
}
