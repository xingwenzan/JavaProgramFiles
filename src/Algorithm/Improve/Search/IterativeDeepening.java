package Algorithm.Improve.Search;

/**
 * 迭代加深
 * 加成序列
 * <a href="https://www.acwing.com/problem/content/172/">...</a>
 */
public class IterativeDeepening {
    private final int n;
    private final int[] lst = new int[110];

    public IterativeDeepening(int n) {
        this.n = n;
        lst[0] = 1;
    }

    /**
     * 单次迭代函数
     *
     * @param u 当前所在第 u 个数
     * @param m 本次迭代最多有 m 个数
     * @return 最多 m 个数的情况下，能否实现目标
     */
    private boolean dfs(int u, int m) {
        if (u == m) return lst[m - 1] == n;
        boolean[] st = new boolean[110];
        for (int i = u - 1; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                int s = lst[i] + lst[j];
                if (s > n || s < lst[u - 1] || st[s]) continue;
                st[s] = true;
                lst[u] = s;
                if (dfs(u + 1, m)) return true;
            }
        }
        return false;
    }

    public int[] getAns() {
        int depth = 1;
        while (!dfs(1, depth)) depth++;
        int[] ans = new int[depth];
        System.arraycopy(lst, 0, ans, 0, depth);
        return ans;
    }
}
