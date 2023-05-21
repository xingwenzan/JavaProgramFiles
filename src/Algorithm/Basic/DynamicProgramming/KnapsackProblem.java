package Algorithm.Basic.DynamicProgramming;

import java.util.Arrays;

public class KnapsackProblem {
    /*
    01背包问题 https://www.acwing.com/problem/content/description/2/
    https://www.acwing.com/activity/content/code/content/625657/
    f[i][j] = max(f[i-1][j], f[i-1][j-v[i]]+w[i])
     */

    /*
    完全背包问题 https://www.acwing.com/problem/content/3/
    https://www.acwing.com/video/945/
    f[i][j] = max(f[i-1][j], f[i][j-v[i]]+w[i])
     */

    /*
    多重背包问题 I https://www.acwing.com/problem/content/4/
    f[i][j] = max(f[i-1][j], f[i-1][j-v[i]]+w[i],……,f[i-1][j-k*v[i]]+k*w[i],……,f[i-1][j-s[i]*v[i]]+s[i]*w[i])
     */

    /*
    多重背包问题 II https://www.acwing.com/problem/content/5/
    多重背包的二进制优化方法，将 s 拆分成 1 2 4 8 …… 2^k …… 2^n s-2^n 个该物品的组合。然后 01
    01 朴素版会 MLE，只能用 01 优化版
     */

    /*
    分组背包问题 https://www.acwing.com/problem/content/9/
    f[i][j] = max(f[i-1][j], f[i-1][j-v[i][1]]+w[i][1], …… , f[i-1][j-v[i][s]]+w[i][s])
     */

    private int idx = 0;   // 01、完全、多重 I、多重 II
    private final int N = 1000 * ((int) log2(2000) + 1) + 10;   // 多重 II = 11010；01、完全 = 1010；多重 I、分组 = 110
    private final int[] V = new int[N], W = new int[N], S = new int[N];   // 01、完全、多重 I、多重 II
    private int groupIdx = 0, objectIdx = 0;   // 分组
    private final int[] SG = new int[N];   // 分组
    private final int[][] VG = new int[N][N], WG = new int[N][N];   // 分组


    public static double log2(int x) {
        return Math.log(x) / Math.log(2);
    }

    public void add(int v, int w) {   // 01、完全
        idx++;
        V[idx] = v;
        W[idx] = w;
    }

    public void addsI(int v, int w, int s) {   // 多重 I
        idx++;
        V[idx] = v;
        W[idx] = w;
        S[idx] = s;
    }

    public void addsII(int v, int w, int s) {   // 多重 II
        int bit = 1;
        while (bit <= s) {
            idx++;
            V[idx] = v * bit;
            W[idx] = w * bit;
            s -= bit;
            bit *= 2;
        }
        if (s >= 0) {
            idx++;
            V[idx] = v * s;
            W[idx] = w * s;
        }
    }

    public void addGroup(int s) {
        groupIdx++;
        SG[groupIdx] = s;
    }

    public void addObject(int s, int v, int w) {
        objectIdx++;
        VG[groupIdx][objectIdx] = v;
        WG[groupIdx][objectIdx] = w;
        if (objectIdx >= s) {
            objectIdx = 0;
        }
    }

    public int naive01(int objectNum, int bagVolume) {
        int[][] alls = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(alls[i], 0);
        }
        for (int i = 1; i <= objectNum; i++) {
            for (int j = 1; j <= bagVolume; j++) {
                alls[i][j] = alls[i - 1][j];
                if (j >= V[i]) {
                    alls[i][j] = Math.max(alls[i][j], alls[i - 1][j - V[i]] + W[i]);
                }
            }
        }
        return alls[objectNum][bagVolume];
    }

    public int optimization01(int objectNum, int bagVolume) {
        int[] alls = new int[N];
        Arrays.fill(alls, 0);
        for (int i = 1; i <= objectNum; i++) {
            for (int j = bagVolume; j >= V[i]; j--) {
                alls[j] = Math.max(alls[j], alls[j - V[i]] + W[i]);
            }
        }
        return alls[bagVolume];
    }

    public int naiveCompletely(int objectNum, int bagVolume) {
        int[][] alls = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(alls[i], 0);
        }
        for (int i = 1; i <= objectNum; i++) {
            for (int j = 1; j <= bagVolume; j++) {
                alls[i][j] = alls[i - 1][j];
                if (j >= V[i]) {
                    alls[i][j] = Math.max(alls[i][j], alls[i][j - V[i]] + W[i]);
                }
            }
        }
        return alls[objectNum][bagVolume];
    }

    public int optimizationCompletely(int objectNum, int bagVolume) {
        int[] alls = new int[N];
        Arrays.fill(alls, 0);
        for (int i = 1; i <= objectNum; i++) {
            for (int j = V[i]; j <= bagVolume; j++) {
                alls[j] = Math.max(alls[j], alls[j - V[i]] + W[i]);
            }
        }
        return alls[bagVolume];
    }

    public int naiveMultipleI(int objectNum, int bagVolume) {
        int[][] alls = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(alls[i], 0);
        }
        for (int i = 1; i <= objectNum; i++) {
            for (int j = 1; j <= bagVolume; j++) {
                alls[i][j] = alls[i - 1][j];
                for (int k = 1; k <= S[i] && k * V[i] <= j; k++) {
                    alls[i][j] = Math.max(alls[i][j], alls[i - 1][j - k * V[i]] + k * W[i]);
                }
            }
        }
        return alls[objectNum][bagVolume];
    }

    public int optimizationMultipleI(int objectNum, int bagVolume) {
        int[] alls = new int[N];
        Arrays.fill(alls, 0);
        for (int i = 1; i <= objectNum; i++) {
            for (int j = bagVolume; j >= V[i]; j--) {
                for (int k = 1; k <= S[i] && k * V[i] <= j; k++) {
                    alls[j] = Math.max(alls[j], alls[j - k * V[i]] + k * W[i]);
                }
            }
        }
        return alls[bagVolume];
    }

    public int MultipleII(int bagVolume) {
        return optimization01(idx, bagVolume);
    }

    public int naiveGroup(int nGroup, int bagVolume) {
        int[][] alls = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(alls[i], 0);
        }
        for (int i = 1; i <= nGroup; i++) {
            for (int j = 1; j <= bagVolume; j++) {
                alls[i][j] = alls[i - 1][j];
                for (int k = 1; k <= SG[i]; k++) {
                    if (j >= VG[i][k]) {
                        alls[i][j] = Math.max(alls[i][j], alls[i - 1][j - VG[i][k]] + WG[i][k]);
                    }
                }
            }
        }
        return alls[nGroup][bagVolume];
    }

    public int optimizationGroup(int nGroup, int bagVolume) {
        int[] alls = new int[N];
        Arrays.fill(alls, 0);
        for (int i = 1; i <= nGroup; i++) {
            for (int j = bagVolume; j >= 1; j--) {
                for (int k = 1; k <= SG[i]; k++) {
                    if (j >= VG[i][k]) {
                        alls[j] = Math.max(alls[j], alls[j - VG[i][k]] + WG[i][k]);
                    }
                }
            }
        }
        return alls[bagVolume];
    }
}
