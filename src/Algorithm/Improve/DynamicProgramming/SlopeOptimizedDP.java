package Algorithm.Improve.DynamicProgramming;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class SlopeOptimizedDP {
    /*---------------------** 注释部分 **---------------------*/

    // 任务安排1 https://www.acwing.com/problem/content/302/

    /*---------------------** 变量定义部分 **---------------------*/

    private final int INF = 0x3f3f3f3f;
    private final long INFL = (long) 9e18;

    /*---------------------** 私有函数部分 **---------------------*/

    /**
     * 前缀和
     *
     * @param lst 输入数组
     * @return 返回前缀和数组，且开头额外多个 0
     */
    @Contract(pure = true)
    private int @NotNull [] prefixSum(int @NotNull [] lst) {
        int n = lst.length;
        int[] ints = new int[n + 1];
        ints[0] = 0;
        for (int i = 0; i < n; i++) {
            ints[i + 1] = ints[i] + lst[i];
        }
        return ints;
    }

    /*---------------------** 题目主体函数部分 **---------------------*/

    public long Task1(int n, int s, int[] c, int[] t) {
        long[] f = new long[n + 10];
        Arrays.fill(f, INFL);
        f[0] = 0;
        int[] C = prefixSum(c);
        int[] T = prefixSum(t);
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                f[i] = Math.min(f[i], f[j] + (long) T[i] * (C[i] - C[j]) + (long) s * (C[n] - C[j]));
            }
        }
        return f[n];
    }
}
