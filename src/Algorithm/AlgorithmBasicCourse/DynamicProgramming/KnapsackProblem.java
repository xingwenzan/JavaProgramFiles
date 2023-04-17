package Algorithm.AlgorithmBasicCourse.DynamicProgramming;

import java.util.Arrays;

public class KnapsackProblem {
    // 01背包问题 https://www.acwing.com/problem/content/description/2/

    private final int N = 1010;
    private int idx = 1;
    private final int[] volumes = new int[N], weights = new int[N];

    public void add(int v, int w) {
        volumes[idx] = v;
        weights[idx] = w;
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
                if (j >= volumes[i]) {
                    alls[i][j] = Math.max(alls[i][j], alls[i - 1][j - volumes[i]] + weights[i]);
                }
            }
        }
        return alls[objectNum][bagVolume];
    }

    public int optimization01(int objectNum, int bagVolume) {
        int[] alls = new int[N];
        Arrays.fill(alls, 0);
        for (int i = 1; i <= objectNum; i++) {
            for (int j = bagVolume; j >= volumes[i]; j--) {
                alls[j] = Math.max(alls[j], alls[j - volumes[i]] + weights[i]);
            }
        }
        return alls[bagVolume];
    }

}
