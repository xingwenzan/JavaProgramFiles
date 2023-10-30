package Algorithm.AdvancedGuide.BasicAlgorithms;

/**
 * 递归实现排列型枚举
 * <a href="https://www.acwing.com/problem/content/96/">...</a>
 */
public class ArrangedEnumeration {
    private final int N;
    private final int[] path;

    public ArrangedEnumeration(int N) {
        this.N = N;
        this.path = new int[N];
    }

    private void dfs(int u, int st) {
        if (u == N) {
            for (int i : path) {
                System.out.printf("%d ", i);
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < N; i++) {
            if (((st >> i) & 1) == 1) continue;
            path[u] = i + 1;
            dfs(u + 1, st + (1 << i));
            path[u] = 0;
        }
    }

    public void allOutput() {
        dfs(0, 0);
    }
}
