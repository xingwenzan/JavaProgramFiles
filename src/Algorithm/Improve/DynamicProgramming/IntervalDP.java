package Algorithm.Improve.DynamicProgramming;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class IntervalDP {
    /*---------------------** 注释部分 **---------------------*/

    // 环形石子合并 https://www.acwing.com/problem/content/1070/
    // 能量项链 https://www.acwing.com/problem/content/322/
    // 加分二叉树 https://www.acwing.com/problem/content/481/

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
    @Contract(pure = true)
    private int @NotNull [] prefixSum2(int[] lst, int length) {
        int[] ans = new int[length * 2 + 1];
        for (int i = 1; i < 2 * length; i++) {
            ans[i] = ans[i - 1] + lst[(i - 1) % length];
        }
        return ans;
    }

    /**
     * DFS 获取树的前序遍历
     *
     * @param inArray 输入数组
     * @param l       左端点
     * @param r       右端点
     * @param goal    目标列表，作为返回值返回
     * @return goal
     * 应用   加分二叉树
     */
    private ArrayList<Integer> dfs(int[][] inArray, int l, int r, ArrayList<Integer> goal) {
        if (l > r) return goal;
        int k = inArray[l][r];
        goal.add(k);
        goal = dfs(inArray, l, k - 1, goal);
        goal = dfs(inArray, k + 1, r, goal);
        return goal;
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

    public int EnergyNecklace(int[] lst, int length) {
        // 变量初始化
        int N = 2 * length + 10;
        int[] w = new int[N];
        for (int i = 1; i <= length; i++) w[i] = w[i + length] = lst[i - 1];
        int[][] f = new int[N][N];

        // DP
        for (int i = 3; i <= length + 1; i++) {
            for (int l = 1, r = l + i - 1; r <= 2 * length; r = ++l + i - 1) {
                for (int k = l + 1; k < r; k++) {
                    f[l][r] = Math.max(f[l][r], f[l][k] + f[k][r] + w[l] * w[k] * w[r]);
                }
            }
        }

        // 取值
        int ans = 0;
        for (int i = 1; i <= length; i++) ans = Math.max(ans, f[i][i + length]);

        return ans;
    }

    public HashMap<Integer, ArrayList<Integer>> BonusBinaryTree(int[] lst, int length) {
        // 变量初始化
        int N = length + 5;
        int[] w = new int[N];
        System.arraycopy(lst, 0, w, 1, length);
        int[][] f = new int[N][N], g = new int[N][N];

        // DP
        for (int i = 1; i <= length; i++) {
            for (int l = 1, r = l + i - 1; r <= length; r = ++l + i - 1) {
                if (l == r) {
                    f[l][r] = w[l];
                    g[l][r] = l;
                } else {
                    for (int j = l; j <= r; j++) {
                        int left = j == l ? 1 : f[l][j - 1];
                        int right = j == r ? 1 : f[j + 1][r];
                        int score = left * right + w[j];
                        if (score > f[l][r]) {
                            f[l][r] = score;
                            g[l][r] = j;
                        }
                    }
                }
            }
        }

        // DFS 输出
        HashMap<Integer, ArrayList<Integer>> ans = new HashMap<>();
        ArrayList<Integer> out = new ArrayList<>();
        out = dfs(g, 1, length, out);
        ans.put(f[1][length], out);
        return ans;
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
