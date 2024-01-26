package Algorithm.AdvancedGuide.BasicAlgorithms;

/**
 * 股票买卖 II
 * <a href="https://www.acwing.com/problem/content/1057/">...</a>
 */
public class StockJobbingII {
    private final int n;
    private final int[] lst;
    private int ans = 0;

    public StockJobbingII(int n, int[] lst) {
        this.n = n;
        this.lst = lst;
    }

    public int getAns() {
        for (int i = 0; i < n - 1; i++) ans += lst[i + 1] > lst[i] ? lst[i + 1] - lst[i] : 0;
        return ans;
    }
}
