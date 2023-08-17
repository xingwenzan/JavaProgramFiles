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
    // 凸多边形的划分 https://www.acwing.com/problem/content/1071/

    /* 凸多边形的划分
    由题划分之后每条边都一定会被用上，故无须把环做成 2 倍链，直接从（1，n）断开即可
     */

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

    /**
     * 高精度数字加法
     *
     * @param a 高精度数字
     * @param b 高精度数字
     * @return a+b
     */
    @Contract("_, _ -> new")
    private @NotNull HighPrecisionNum add(@NotNull HighPrecisionNum a, @NotNull HighPrecisionNum b) {
        long[] x = a.num, y = b.num, z = new long[35];
        int length = Math.max(a.length, b.length);
        long tmp = 0;
        for (int i = 0; i < length; i++) {
            tmp += x[i] + y[i];
            z[i] = tmp % 10;
            tmp /= 10;
        }
        while (tmp > 0) {
            z[length++] = tmp % 10;
            tmp /= 10;
        }
        return new HighPrecisionNum(z, length);
    }

    /**
     * 高精度数字乘法
     *
     * @param a 高精度数字
     * @param b 正常 int
     * @return a*b
     */
    @Contract("_, _ -> new")
    private @NotNull HighPrecisionNum mul(@NotNull HighPrecisionNum a, int b) {
        long[] x = a.num, y = new long[35];
        int length = a.length;
        long tmp = 0;
        for (int i = 0; i < length; i++) {
            tmp += x[i] * b;
            y[i] = tmp % 10;
            tmp /= 10;
        }
        while (tmp > 0) {
            y[length++] = tmp % 10;
            tmp /= 10;
        }
        return new HighPrecisionNum(y, length);
    }

    /**
     * 比较两高精度数字大小
     *
     * @param a 高精度数字
     * @param b 高精度数字
     * @return a>b 1   a=b 0   a<b -1
     */
    @Contract(pure = true)
    private int cmp(@NotNull HighPrecisionNum a, @NotNull HighPrecisionNum b) {
        if (a.length != b.length) return a.length > b.length ? 1 : -1;
        for (int i = a.length; i >= 0; i--) {
            if (a.num[i] != b.num[i]) return a.num[i] > b.num[i] ? 1 : -1;
        }
        return 0;
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

    public HighPrecisionNum ConvexPolygonsDivision(int[] lst, int length) {
        // 变量初始化
        int N = length + 5;
        int[] w = new int[N];
        System.arraycopy(lst, 0, w, 1, length);
        HighPrecisionNum[][] f = new HighPrecisionNum[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                f[i][j] = new HighPrecisionNum(0);
            }
        }

        // DP
        for (int i = 3; i <= length; i++) {
            for (int l = 1, r = l + i - 1; r <= length; r = ++l + i - 1) {
                long[] inf = new long[35];
                inf[34] = 1;
                f[l][r] = new HighPrecisionNum(inf, 35);
                for (int j = l + 1; j <= r - 1; j++) {
                    HighPrecisionNum tmp = new HighPrecisionNum(w[l]);
                    tmp = mul(tmp, w[j]);
                    tmp = mul(tmp, w[r]);
                    tmp = add(tmp, f[l][j]);
                    tmp = add(tmp, f[j][r]);
                    if (cmp(f[l][r], tmp) > 0) f[l][r] = tmp;
                }
            }
        }

        return f[1][length];
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

    /**
     * 高精度数字
     * (default)   包内所有代码可用
     * static   本类不依赖 IntervalDP 类对象即可创建
     */
    static class HighPrecisionNum {
        long[] num = new long[35];
        int length = 0;

        public HighPrecisionNum(int x) {
            while (x > 0) {
                num[length++] = x % 10;
                x /= 10;
            }
        }

        public HighPrecisionNum(long[] x, int length) {
            this.length = length;
            System.arraycopy(x, 0, num, 0, length);
        }

        public void outputNum() {
            for (int i = length - 1; i >= 0; i--) {
                System.out.printf("%d", num[i]);
            }
            System.out.println();
        }
    }
}
