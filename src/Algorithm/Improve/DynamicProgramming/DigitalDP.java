package Algorithm.Improve.DynamicProgramming;

import java.util.ArrayList;

/**
 * 数位 DP 套路
 * 题目套路   [l,r] 范围内满足某条件数的个数
 * DP 套路   dp(r)-dp(l-1)
 * 解题套路  dp(x)
 * 转化为 b 进制数表示   x = a_(n-1) * b^(n-1) + a_(n-2) * b^(n-2) + ... + a_(0) * b^(0)
 * 以树的方式分类（视频 14:14 处）   父节点下左树为该位的 a in [0,a_(n-i)-1]，此时左树代表必小于等于原数 x；右树为 a = a_(n-i)
 * 视情况计算求解
 */
public class DigitalDP {
    /*---------------------** 注释部分 **---------------------*/

    // 度的数量 https://www.acwing.com/problem/content/1083/

    /*---------------------** 变量定义部分 **---------------------*/

    // 度的数量 35
    private final int N = 35;

    /*---------------------** 私有函数部分 **---------------------*/

    /**
     * 获取 C_a^b
     *
     * @return C_a^b
     */
    private int[][] Cab() {
        int[][] f = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) f[i][j] = 1;
                else f[i][j] = f[i - 1][j] + f[i - 1][j - 1];
            }
        }
        return f;
    }

    private int DP(int n, int b, int k) {
        // 边界情况
        if (n == 0) return 0;
        // 将 x 换成 b 进制表示，其中索引高者表示高位
        ArrayList<Integer> num = new ArrayList<>();
        while (n > 0) {
            num.add(n % b);
            n /= b;
        }
        int[][] f = Cab();
        // 从高到低位遍历更新，每位只能填 0 或 1
        int ans = 0;   // 结果
        int last = 0;   // 表示前置情况，本题表示已经确定的 1 的数量
        for (int i = num.size() - 1; i >= 0; i--) {
            int x = num.get(i);
            if (x > 0) {   // 左树
                ans += f[i][k - last];   // 此位填 0 的满足的数量
                if (x > 1) {   // 即左树可能存在此位填 1 的情况，需要加上
                    if (k - last - 1 >= 0) ans += f[i][k - last - 1];
                    // 且此时填 1 或 0 已经无影响，可以跳出左树
                    break;
                } else {   // x=1，左树是否为 1 受后面影响，不能从此处直接计算，last++ 后下次更新，由于此位为 0 已求完，则只考虑为 1 情况
                    last++;
                    if (last > k) break;
                }
            }
            if (i == 0 && last == k) ans++;
        }
        return ans;
    }

    /*---------------------** 题目主体函数部分 **---------------------*/

    public int NumberOfDegrees(int l, int r, int b, int k) {
        return DP(r, b, k) - DP(l - 1, b, k);
    }
}
