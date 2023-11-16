package Algorithm.AdvancedGuide.BasicAlgorithms;

/**
 * 激光炸弹
 * <a href="https://www.acwing.com/problem/content/101/">...</a>
 */
public class LaserBomb {
    private final int r, N = 5010;
    private final int[][] map;
    private boolean st;

    public LaserBomb(int r) {
        this.r = r;
        map = new int[N][N];
        st = false;
    }

    public void add(int x, int y, int w) {
        map[x + 1][y + 1] += w;
    }

    public int count() {
        int ans = 0;
        if (!st) {
            prefixSum();
            st = true;
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                int x = Math.min(N - 1, i + r - 1), y = Math.min(N - 1, j + r - 1);
                ans = Math.max(ans, map[x][y] - map[i - 1][y] - map[x][j - 1] + map[i - 1][j - 1]);
            }
        }

        return ans;
    }

    private void prefixSum() {
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                map[i][j] += map[i - 1][j] + map[i][j - 1] - map[i - 1][j - 1];
            }
        }
    }
}
