package Algorithm.Improve.DynamicProgramming;

public class DigitalTriangleModel {
    // 摘花生 https://www.acwing.com/problem/content/1017/

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
}
