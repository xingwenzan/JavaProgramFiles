package Algorithm.Improve.DynamicProgramming;

import java.util.Arrays;

public class IntervalDP {
    /*---------------------** 注释部分 **---------------------*/

    // 环形石子合并 https://www.acwing.com/problem/content/1070/

    /*---------------------** 变量定义部分 **---------------------*/

    private final int INF = 0x3f3f3f3f;

    /*---------------------** 私有函数部分 **---------------------*/

    /**
     * 双倍前缀和
     *
     * @param lst    输入数组
     * @param length 输入数组有效长度
     * @return 两个 lst 拼接后的前缀和，长度是 length 的 2 倍加 1，0 号位是 0，代表前 0 个的前缀和位 0
     * 应用   环形石子合并
     */
    private int[] prefixSum2(int[] lst, int length) {
        int[] ans = new int[length * 2 + 1];
        for (int i = 1; i < 2 * length; i++) {
            ans[i] = ans[i - 1] + lst[(i - 1) % length];
        }
        return ans;
    }

    /*---------------------** 题目主体函数部分 **---------------------*/

    public PII RingPebblesMerge(int[] lst, int length) {
        // 变量设置
        int N = length * 2 + 10;   // 因为是环形，故开双倍大小的数组
        int[] s = prefixSum2(lst, length);
        int[][] fMax = new int[N][N], fMin = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(fMax[i], -INF);
            Arrays.fill(fMin[i], INF);
        }

        // DP
        for (int i = 1; i <= length; i++) {
            for (int l = 1, r = l + i - 1; r < length * 2; r = ++l + i - 1) {
                if (l == r) {
                    fMax[l][r] = fMin[l][r] = 0;
                } else {
                    for (int j = l; j < r; j++) {
                        fMax[l][r] = Math.max(fMax[l][r], fMax[l][j] + fMax[j + 1][r] + s[r] - s[l - 1]);
                        fMin[l][r] = Math.min(fMin[l][r], fMin[l][j] + fMin[j + 1][r] + s[r] - s[l - 1]);
                    }
                }
            }
        }

        // 取值
        int M = -INF, m = INF;
        for (int i = 1; i <= length; i++) {
            M = Math.max(M, fMax[i][i + length - 1]);
            m = Math.min(m, fMin[i][i + length - 1]);
        }

        return new PII(M, m);
    }

    /*---------------------** 内部类部分 **---------------------*/

    /**
     * Pair<int,int>
     * (default)   包内所有代码可用
     * static   本类不依赖 IntervalDP 类对象即可创建
     */
    static class PII {
        int x, y;

        public PII(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
