package Algorithm.Improve.Search;

import java.util.Arrays;

/**
 * 迷宫问题
 * <a href="https://www.acwing.com/problem/content/1078/">...</a>
 */
public class MazeProblem {
    private final int size;
    private final int[][] maze;
    private final Point[][] p;

    public MazeProblem(int size, int[][] maze) {
        this.size = size;
        this.maze = maze;
        p = new Point[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(p[i], new Point());
        }
        bfs();
    }

    private void bfs() {
        int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, -1, 0, 1};
        Point[] q = new Point[(size + 1) * (size + 1)];
        q[0] = new Point(size - 1, size - 1);
        int hh = 0, tt = 0;
        while (hh <= tt) {
            Point tmp = q[hh++];
            for (int i = 0; i < 4; i++) {
                int x = tmp.x + dx[i], y = tmp.y + dy[i];
                if (x < 0 || y < 0 || x >= size || y >= size) continue;
                if (maze[x][y] == 1) continue;
                if (p[x][y].x != -1) continue;
                q[++tt] = new Point(x, y);
                p[x][y] = tmp;
            }
        }
    }

    public void output() {
        int x = 0, y = 0;
        while (true) {
            System.out.printf("%d %d\n", x, y);
            if (x == size - 1 && y == size - 1) break;
            Point tmp = p[x][y];
            x = tmp.x;
            y = tmp.y;
        }
    }

    private static class Point {
        private int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point() {
            x = -1;
            y = -1;
        }
    }
}
