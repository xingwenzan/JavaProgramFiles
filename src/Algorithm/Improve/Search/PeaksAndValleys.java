package Algorithm.Improve.Search;

/**
 * 山峰和山谷
 * <a href="https://www.acwing.com/problem/content/1108/">...</a>
 */
public class PeaksAndValleys {
    // 输入
    private final int sideLength;
    private final int[][] maps;
    // 中间
    private final boolean[][] st;
    private final int[][] q;
    private boolean hasHigh, hasLow;
    // 输出
    private int peaks = 0, valleys = 0;

    public PeaksAndValleys(int sideLength, int[][] maps) {
        this.sideLength = sideLength;
        this.maps = maps;
        st = new boolean[sideLength][sideLength];
        q = new int[(sideLength + 1) * (sideLength + 1)][2];
        floodFill();
    }

    private void bfs(int x, int y) {
        hasHigh = hasLow = false;
        int hh = 0, tt = 0;
        q[0] = new int[]{x, y};
        st[x][y] = true;
        while (hh <= tt) {
            int[] tmp = q[hh++];
            x = tmp[0];
            y = tmp[1];
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (i == x && j == y) continue;
                    if (i < 0 || j < 0 || i >= sideLength || j >= sideLength) continue;
                    if (maps[x][y] != maps[i][j]) {
                        if (maps[i][j] > maps[x][y]) hasHigh = true;
                        else hasLow = true;
                    } else if (!st[i][j]) {
                        q[++tt] = new int[]{i, j};
                        st[i][j] = true;
                    }
                }
            }
        }
    }

    private void floodFill() {
        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength; j++) {
                if (!st[i][j]) {
                    bfs(i, j);
                    if (!hasHigh) peaks++;
                    if (!hasLow) valleys++;
                }
            }
        }
    }

    public int getPeaks() {
        return peaks;
    }

    public int getValleys() {
        return valleys;
    }
}
