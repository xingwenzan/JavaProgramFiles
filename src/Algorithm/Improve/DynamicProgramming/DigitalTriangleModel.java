package Algorithm.Improve.DynamicProgramming;

import java.util.Arrays;

public class DigitalTriangleModel {
    // 摘花生 https://www.acwing.com/problem/content/1017/
    // 最低通行费 https://www.acwing.com/problem/content/1020/
    // 方格取数 https://www.acwing.com/problem/content/1029/
    // 传纸条 https://www.acwing.com/problem/content/277/   （使用`方格取数`可过）

    private final int T = 15;   // 摘花生、最低通行费 110 方格取数 15
    private final int[][] w = new int[T][T], f = new int[T][T];   // f 摘花生、最低通行费 w 摘花生、最低通行费、方格取数
    private final int[][][] q = new int[2 * T][T][T];   // 方格取数

    // 摘花生、最低通行费
    public void add(int r, int c, int x) {
        w[r][c] = x;
    }

    // 摘花生
    public int pickingPeanuts(int R, int C) {
        for (int r = 1; r <= R; r++) {
            for (int c = 1; c <= C; c++) {
                f[r][c] = Math.max(f[r - 1][c], f[r][c - 1]) + w[r - 1][c - 1];
            }
        }
        return f[R][C];
    }

    // 最低通行费
    public int minimumToll(int n) {
        for (int i = 0; i < T; i++) {
            Arrays.fill(f[i], (int) 1e9);
        }
        f[0][1] = 0;
        f[1][0] = 1;
        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= n; c++) {
                f[r][c] = Math.min(f[r - 1][c], f[r][c - 1]) + w[r - 1][c - 1];
            }
        }
        return f[n][n];
    }

    // 方格取数
    public int squareAccess(int n) {
        // f[k][i1][i2] k 两人各一共走k步   i1 一个人横向走了i步   i2 另一个人横向走了i2步
        for (int k = 2; k <= 2 * n; k++) {
            for (int i1 = 1; i1 <= n; i1++) {
                for (int i2 = 1; i2 <= n; i2++) {
                    int j1 = k - i1, j2 = k - i2;
                    if (1 <= j1 && j1 <= n && 1 <= j2 && j2 <= n) {
                        q[k][i1][i2] = (i1 != i2 ? w[i1][j1] + w[i2][j2] : w[i1][j1]);
                        int t = q[k - 1][i1][i2];
                        t = Math.max(t, q[k - 1][i1 - 1][i2]);
                        t = Math.max(t, q[k - 1][i1][i2 - 1]);
                        t = Math.max(t, q[k - 1][i1 - 1][i2 - 1]);
                        q[k][i1][i2] += t;
                    }
                }
            }
        }
        return q[2 * n][n][n];
    }
}
