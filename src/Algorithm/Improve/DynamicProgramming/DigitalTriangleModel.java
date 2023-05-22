package Algorithm.Improve.DynamicProgramming;

import java.util.Arrays;

public class DigitalTriangleModel {
    // 摘花生 https://www.acwing.com/problem/content/1017/
    // 最低通行费 https://www.acwing.com/problem/content/1020/

    private final int T = 110;   // 摘花生 110
    private final int[][] w = new int[T][T], f = new int[T][T];   // 摘花生

    // 摘花生
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
}
