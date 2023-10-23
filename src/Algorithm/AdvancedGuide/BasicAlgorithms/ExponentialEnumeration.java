package Algorithm.AdvancedGuide.BasicAlgorithms;

/**
 * 递归实现指数型枚举
 * <a href="https://www.acwing.com/problem/content/94/">...</a>
 */
public class ExponentialEnumeration {
    private final int n;

    public ExponentialEnumeration(int n) {
        this.n = n;
    }

    private void dfs(int u, int st) {
        if (u == n) {
            for (int i = 0; i < n; i++) {
                if (((st >> i) & 1) == 1) System.out.printf("%d ", i + 1);
            }
            System.out.println();
            return;
        }
        dfs(u + 1, st + (1 << u));
        dfs(u + 1, st);
    }

    public void allOutput() {
        dfs(0, 0);
    }
}
