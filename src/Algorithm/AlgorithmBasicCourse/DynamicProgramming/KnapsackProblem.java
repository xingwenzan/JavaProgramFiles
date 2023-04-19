package Algorithm.AlgorithmBasicCourse.DynamicProgramming;

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

    private final int N = 1010;
    private int idx = 1;
    private final int[] V = new int[N], W = new int[N], S = new int[N];

    public void add(int v, int w) {   // 01、完全
        V[idx] = v;
        W[idx] = w;
        idx++;
    }

    public void adds(int v, int w, int s) {   // 多重
        V[idx] = v;
        W[idx] = w;
        S[idx] = s;
        idx++;
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

    public int naiveMultiple(int objectNum, int bagVolume) {
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
}
