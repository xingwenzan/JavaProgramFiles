package Algorithm.AdvancedGuide.BasicAlgorithms;

/**
 * 最高的牛
 * <a href="https://www.acwing.com/problem/content/103/">...</a>
 */
public class TallestCow {
    private final int n;
    private final int[] cow;
    private final boolean[][] st;

    public TallestCow(int n, int h) {
        this.n = n;
        cow = new int[n + 1];
        cow[1] = h;
        st = new boolean[n + 10][n + 10];
    }

    public void add(int a, int b) {
        if (!st[a][b]) {
            cow[a + 1] -= 1;
            cow[b] += 1;
            st[a][b] = st[b][a] = true;
        }
    }

    public int[] getCow() {
        for (int i = 2; i <= n; i++) {
            cow[i] += cow[i - 1];
        }
        return cow;
    }
}
