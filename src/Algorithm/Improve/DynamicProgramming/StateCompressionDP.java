package Algorithm.Improve.DynamicProgramming;

import java.util.ArrayList;
import java.util.HashMap;

public class StateCompressionDP {
    // 小国王 https://www.acwing.com/problem/content/1066/
    // 玉米田 https://www.acwing.com/problem/content/329/
    // 炮兵阵地 https://www.acwing.com/problem/content/294/

    // 小国王、玉米田
    private boolean check1(int state) {   // 判断该状态是否合法
        return (state & (state << 1)) == 0;
    }

    // 炮兵阵地
    private boolean check2(int state) {
        return (state & (state << 1)) == 0 && (state & (state << 2)) == 0;
    }

    // 小国王、炮兵阵地
    private int count1(int num) {   // 判断该状态有多少 1
        int ans = 0;
        while (num != 0) {
            ans += num & 1;
            num >>= 1;
        }
        return ans;
    }

    public long LittleKing(int Num, int King) {
        // f[i][j][k]   前 i 行摆好，已经摆放 j 给国王，第 i 行状态 k
        int N = 12, M = 110, S = 1 << 10;
        ArrayList<Integer> state = new ArrayList<>();   // 所有合法状态
        int[] cnt = new int[S];   // 各状态 1 的个数
        HashMap<Integer, ArrayList<Integer>> head = new HashMap<>();   // 各状态可以转移到的状态（a 可转移到 b，b 亦可转移到 a）
        long[][][] f = new long[N][M][S];

        // 预处理所有合法状态
        for (int i = 0; i < (1 << Num); i++) {
            if (check1(i)) {
                state.add(i);
                cnt[i] = count1(i);
            }
        }
        // 预处理该状态所有可转移状态
        for (int a : state) {
            for (int b : state) {
                if (((a & b) == 0) && check1(a | b)) {
                    if (!head.containsKey(a)) {
                        ArrayList<Integer> tmp = new ArrayList<>();
                        tmp.add(b);
                        head.put(a, tmp);
                    } else {
                        head.get(a).add(b);
                    }
                }
            }
        }
        // DP
        f[0][0][0] = 1;
        for (int i = 1; i <= Num + 1; i++) {
            for (int j = 0; j <= King; j++) {
                for (int a : state) {
                    for (int b : head.get(a)) {
                        if (j >= cnt[a]) {
                            f[i][j][a] += f[i - 1][j - cnt[a]][b];
                        }
                    }
                }
            }
        }
        return f[Num + 1][King][0];
    }

    public int Cornfield(int row, int col, String[][] strings) {
        int N = 14, M = 1 << 12, mod = (int) 1e8;
        int[][] f = new int[N][M];
        int[] w = new int[N];   // 田
        ArrayList<Integer> state = new ArrayList<>();
        HashMap<Integer, ArrayList<Integer>> head = new HashMap<>();   // 各状态可以转移到的状态（a 可转移到 b，b 亦可转移到 a）

        // 获取田地的二进制表示状态
        // （题中 1 可种 0 不可种，下面则相反，不可种的地方种了 & 后就不为 0，方便 DP 中的判断）
        for (int i = 1; i <= row; i++) {
            for (int j = 0; j < col; j++) {
                w[i] += (1 << j) * (1 - Integer.parseInt(strings[i - 1][j]));
            }
        }
        // 获取合法状态
        for (int i = 0; i < (1 << col); i++) {
            if (check1(i)) {
                state.add(i);
            }
        }
        // 获取可（被）转移状态
        for (int a : state) {
            for (int b : state) {
                if ((a & b) == 0) {
                    if (!head.containsKey(a)) {
                        ArrayList<Integer> tmp = new ArrayList<>();
                        tmp.add(b);
                        head.put(a, tmp);
                    } else {
                        head.get(a).add(b);
                    }
                }
            }
        }
        // DP
        f[0][0] = 1;
        for (int i = 1; i <= row + 1; i++) {
            for (int j : state) {
                if ((j & w[i]) == 0) {
                    for (int k : head.get(j)) {
                        f[i][j] = (f[i][j] + f[i - 1][k]) % mod;
                    }
                }
            }
        }
        return f[row + 1][0];
    }

    public int ArtilleryEmplacement(int row, int col, String[] strings) {
        // f[i][j][k]   前 i 行摆好，已经摆放 j 给国王，第 i 行状态 k
        int N = 105, M = 1 << 10;
        int[] g = new int[N];   // 地图
        ArrayList<Integer> state = new ArrayList<>();   // 所有合法状态
        int[] cnt = new int[M];   // 各状态 1 的个数
        HashMap<Integer, ArrayList<Integer>> head = new HashMap<>();   // 各状态可以转移到的状态（a 可转移到 b，b 亦可转移到 a）
        /*
        滚动数组储存
        原为 f[i,j,k]，i 为到第 i 行，j、k 为 i、i-1 行状态
        现为 f[2,j,k]，2 为前第 1、2 行（滚动），j、k 不变，且当时作为前第 2 行者也用作第 i 行
         */
        int[][][] f = new int[2][M][M];

        // 地图读入
        for (int i = 1; i <= row; i++) {
            String s = strings[i - 1];
            for (int j = 0; j < col; j++) {
                g[i] += ((s.charAt(j) == 'H') ? 1 : 0) << j;
            }
        }
        // 预处理所有合法状态
        for (int i = 0; i < (1 << col); i++) {
            if (check2(i)) {
                state.add(i);
                cnt[i] = count1(i);
            }
        }
        // 预处理该状态所有可转移状态
        for (int a : state) {
            for (int b : state) {
                if ((a & b) == 0) {
                    if (!head.containsKey(a)) {
                        ArrayList<Integer> tmp = new ArrayList<>();
                        tmp.add(b);
                        head.put(a, tmp);
                    } else {
                        head.get(a).add(b);
                    }
                }
            }
        }
        // DP
        for (int i = 1; i <= row; i++) {   // i 行
            for (int j : state) {   // i 行状态
                for (int k : head.get(j)) {   // i-1 行状态
                    for (int u : head.get(j)) {   // i-2 行状态
                        if ((k & u) != 0) {
                            continue;
                        }
                        // 理论上 i、i-1、i-2 都要判定是否在 H，后两者如果不合法其 f 一定是 0，不影响计算
                        if ((j & g[i]) != 0) {
                            continue;
                        }
                        f[i & 1][j][k] = Math.max(f[i & 1][j][k], f[(i - 1) & 1][k][u] + cnt[j]);
                    }
                }
            }
        }
        // 取结果
        int ans = 0;
        for (int i : state) {
            for (int j : head.get(i)) {
                ans = Math.max(ans, f[row & 1][i][j]);
            }
        }

        return ans;
    }
}
