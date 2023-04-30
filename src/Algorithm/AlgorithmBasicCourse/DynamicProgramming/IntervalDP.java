package Algorithm.AlgorithmBasicCourse.DynamicProgramming;

public class IntervalDP {
    // 石子合并 https://www.acwing.com/problem/content/284/
    private final int N = 310;
    private final int[][] f = new int[N][N];

    public int[] prefixSum(String[] strings, int n) {
        int[] ans = new int[n + 1];
        ans[0] = 0;
        for (int i = 1; i <= n; i++) {
            ans[i] = ans[i - 1] + Integer.parseInt(strings[i - 1]);
        }
        return ans;
    }

    public int stonesMerge(String[] strings, int n) {
        int[] s = prefixSum(strings, n);
        for (int length = 2; length <= n; length++) {
            for (int l = 1; l + length - 1 <= n; l++) {
                int r = l + length - 1;
                f[l][r] = (int) 1e9;
                for (int k = l; k < r; k++) {
                    f[l][r] = Math.min(f[l][r], f[l][k] + f[k + 1][r] + s[r] - s[l - 1]);
                }
            }
        }
        return f[1][n];
    }
}
