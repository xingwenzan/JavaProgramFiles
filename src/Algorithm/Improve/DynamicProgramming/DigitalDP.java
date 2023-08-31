package Algorithm.Improve.DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;

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
    // 数字游戏 https://www.acwing.com/problem/content/1084/   视频 https://www.acwing.com/video/488/
    // Windy数 https://www.acwing.com/problem/content/1085/

    /*---------------------** 变量定义部分 **---------------------*/

    // 度的数量 35   数字游戏、Windy数 15
    private final int N = 15;

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

    /**
     * DP 获取 f[i,j]
     * 应用   数字游戏
     *
     * @return f[i, j] 最高位为 j，共 i 位的不降数数量
     */
    private int[][] FijNG() {
        int[][] f = new int[N][N];
        // 一位数
        Arrays.fill(f[1], 1);
        // 多位数
        for (int i = 2; i < N; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = j; k <= 9; k++) {
                    f[i][j] += f[i - 1][k];
                }
            }
        }
        return f;
    }

    /**
     * DP 获取 f[i,j]
     * 应用   Windy数
     *
     * @return f[i, j] 最高位为 j，共 i 位的 Windy数 数量
     */
    private int[][] FijWN() {
        int[][] f = new int[N][N];
        // 一位数
        Arrays.fill(f[1], 1);
        // 多位数
        for (int i = 2; i < N; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k <= 9; k++) {
                    if (Math.abs(j - k) >= 2) {
                        f[i][j] += f[i - 1][k];
                    }
                }
            }
        }
        return f;
    }

    /**
     * 度的数量 DP
     *
     * @param n 数字上限
     * @param b b 进制
     * @param k k 个 1
     * @return [0, n] 内所有符合题目的数的数量
     */
    private int DP1(int n, int b, int k) {
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

    /**
     * 数字游戏 DP
     *
     * @param n 数字上限
     * @return [0, n] 内所有符合题目的数的数量
     */
    private int DP2(int n) {
        // 边界情况
        if (n == 0) return 1;   // 理论上不需要特判，但下方拆位的时候如果 n 是 0 可能为空列表
        // 拆位
        ArrayList<Integer> num = new ArrayList<>();
        while (n > 0) {
            num.add(n % 10);
            n /= 10;
        }
        // 正式 DP
        int[][] f = FijNG();
        int ans = 0, last = 0;   // 本题 last 代表 num 正在处理位的上一位数字
        for (int i = num.size() - 1; i >= 0; i--) {   // 从高到低遍历位
            int x = num.get(i);   // 该位对应数字
            for (int j = last; j < x; j++) {   // 遍历该位可能放的数字（左树）
                ans += f[i + 1][j];
            }
            // 本位数 < 上一位数，右树没了，循环停止
            if (x < last) break;
            last = x;
            // 安全到达最后一位，说明 num 本身符合要求
            if (i == 0) ans++;
        }
        return ans;
    }

    /**
     * Windy 数 DP
     * 分情况讨论前导 0 原因   假如size = 5，每个数都是从位数5开始枚举，但其实00013也是windy数，（这个前导0导致res没加）但其实13也在范围内
     *
     * @param n 数字上限
     * @return [1, n] 内所有符合题目的数的数量
     */
    private int DP3(int n) {
        // 边界情况
        if (n == 0) return 0;
        // 拆位
        ArrayList<Integer> num = new ArrayList<>();
        while (n > 0) {
            num.add(n % 10);
            n /= 10;
        }
        // 正式 DP
        int[][] f = FijWN();
        int ans = 0, last = -2;   // 本题 last 代表 num 正在处理位的上一位数字
        // 含前导 0 情况
        for (int i = num.size() - 1; i >= 0; i--) {   // 从高到低遍历位
            int x = num.get(i);   // 该位对应数字
            for (int j = (i == num.size() - 1 ? 1 : 0); j < x; j++) {   // 遍历该位可能放的数字（左树）
                if (Math.abs(j - last) >= 2) {
                    ans += f[i + 1][j];
                }
            }
            // 本位数 < 上一位数，右树没了，循环停止
            if (Math.abs(x - last) < 2) break;
            last = x;
            // 安全到达最后一位，说明 num 本身符合要求
            if (i == 0) ans++;
        }
        // 特殊处理不该含前导 0 情况
        for (int i = 1; i < num.size(); i++) {
            for (int j = 1; j <= 9; j++) {
                ans += f[i][j];
            }
        }
        return ans;
    }

    /*---------------------** 题目主体函数部分 **---------------------*/

    public int NumberOfDegrees(int l, int r, int b, int k) {
        return DP1(r, b, k) - DP1(l - 1, b, k);
    }

    public int NumbersGame(int l, int r) {
        return DP2(r) - DP2(l - 1);
    }

    public int WindyNumber(int l, int r) {
        return DP3(r) - DP3(l - 1);
    }
}
