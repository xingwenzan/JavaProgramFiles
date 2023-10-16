package Algorithm.AdvancedGuide.BasicAlgorithms;

import java.util.Arrays;

/**
 * 最短Hamilton路径
 * <a href="https://www.acwing.com/problem/content/93/">...</a>
 */
public class ShortestHamiltonPath {
    private final int size;
    private final int[][] map;
    private final int INF = 0x3f3f3f3f;
    private final int[][] f;

    public ShortestHamiltonPath(int size, int[][] map) {
        this.size = size;
        this.map = map;
        f = new int[1 << size][size];
        for (int i = 0; i < (1 << size); i++) {
            Arrays.fill(f[i], INF);
        }
        f[1][0] = 0;
        DP();
    }

    private void DP() {
        for (int i = 0; i < (1 << size); i++) {
            for (int j = 0; j < size; j++) {
                if (((i >> j) & 1) != 1) continue;
                for (int k = 0; k < size; k++) {
                    if ((((i - (1 << j)) >> k) & 1) != 1) continue;
                    f[i][j] = Math.min(f[i - (1 << j)][k] + map[k][j], f[i][j]);
                }
            }
        }
    }

    public int getAns() {
        return f[(1 << size) - 1][size - 1];
    }
}
