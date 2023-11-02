package Algorithm.Improve.Search;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 双端队列广搜
 * 题目：电路维修
 * <a href="https://www.acwing.com/problem/content/177/">...</a>
 * 资料：双端队列
 * <a href="https://juejin.cn/post/7049628663439949837">...</a>
 */
public class DoubleEndedQueueSearch {
    private final int r, c;
    private final String[] cable;
    private final int[][] dist;
    private final int INF = 0x3f3f3f3f;

    public DoubleEndedQueueSearch(int r, int c, String[] cable) {
        this.r = r;
        this.c = c;
        this.cable = cable;
        this.dist = new int[r + 1][c + 1];
        for (int i = 0; i < r + 1; i++) {
            Arrays.fill(dist[i], INF);
        }
        dist[0][0] = 0;
        bfs();
    }

    private void bfs() {
        int[] dx = new int[]{-1, -1, 1, 1}, dy = new int[]{-1, 1, 1, -1};
        String p = "\\/\\/";
        int[] px = new int[]{-1, -1, 0, 0}, py = new int[]{-1, 0, 0, -1};
        boolean[][] st = new boolean[r + 1][c + 1];

        LinkedList<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        while (!q.isEmpty()) {
            int[] tmp = q.removeFirst();
            int x = tmp[0], y = tmp[1];
            if (x == r && y == c) return;
            if (st[x][y]) continue;
            st[x][y] = true;
            for (int i = 0; i < 4; i++) {
                int tx = x + dx[i], ty = y + dy[i];
                if (tx < 0 || ty < 0 || tx > r || ty > c) continue;
                int cx = x + px[i], cy = y + py[i];
                boolean w = cable[cx].charAt(cy) != p.charAt(i);
                int d = dist[x][y] + (w ? 1 : 0);
                if (d < dist[tx][ty]) {
                    dist[tx][ty] = d;

                    if (w) q.addLast(new int[]{tx, ty});
                    else q.addFirst(new int[]{tx, ty});
                }
            }
        }
    }

    public int getAns() {
        if (dist[r][c] == INF) return -1;
        return dist[r][c];
    }
}
