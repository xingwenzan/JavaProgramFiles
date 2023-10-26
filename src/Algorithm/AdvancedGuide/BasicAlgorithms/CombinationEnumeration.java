package Algorithm.AdvancedGuide.BasicAlgorithms;

/**
 * 递归实现组合型枚举
 * <a href="https://www.acwing.com/problem/content/95/">...</a>
 */
public class CombinationEnumeration {
    private final int range, num;

    public CombinationEnumeration(int range, int num) {
        this.range = range;
        this.num = num;
    }

    private void dfs(int u, int s, int st) {
        if (s == num) {
            for (int i = 0; i < range; i++) {
                if (((st >> i) & 1) == 1) {
                    System.out.printf("%d ", i + 1);
                }
            }
            System.out.println();
            return;
        }

        if (u == range) return;

        for (int i = u; i < range; i++) {
            dfs(i + 1, s + 1, st + (1 << i));
        }
    }

    public void allOutput() {
        dfs(0, 0, 0);
    }
}
