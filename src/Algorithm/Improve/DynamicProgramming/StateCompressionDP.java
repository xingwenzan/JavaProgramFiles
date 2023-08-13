package Algorithm.Improve.DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class StateCompressionDP {
    /*---------------------** 注释部分 **---------------------*/

    // 小国王 https://www.acwing.com/problem/content/1066/
    // 玉米田 https://www.acwing.com/problem/content/329/
    // 炮兵阵地 https://www.acwing.com/problem/content/294/
    // 愤怒的小鸟 https://www.acwing.com/problem/content/526/
    // 宝藏 https://www.acwing.com/problem/content/531/

    /*愤怒的小鸟
    视频 https://www.acwing.com/video/405/
    注释 https://www.acwing.com/activity/content/code/content/3422510/
     */

    /*宝藏
    解析（本题暂无视频） https://www.acwing.com/solution/content/4026/
     */

    /*---------------------** 变量定义部分 **---------------------*/

    private final double eps = 1e-8;
    private final int inf = 0x3f3f3f3f;

    /*---------------------** 私有函数部分 **---------------------*/

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

    // 愤怒的小鸟
    private int cmp(double x, double y) {
        if (Math.abs(x - y) < eps) return 0;
        if (x > y) return 1;
        return -1;
    }

    /*---------------------** 题目主体函数部分 **---------------------*/

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

    public int AngryBirds(int pigNum, String[] strings) {
        // 变量定义
        int N = 18, M = 1 << 18;
        PDD[] pigs = new PDD[N];   // 储存小猪
        // 储存抛物线/经过小猪的覆盖状态，path[i，j] 为该抛物线经过 i、j 号小猪时的状态，1 为此位上对应的小猪被抛物线经过
        int[][] path = new int[N][N];
        int[] f = new int[M];   // f[x] 状态 x 时（覆盖了哪些猪，没覆盖哪些）所需的最小抛物线数
        Arrays.fill(f, inf);
        f[0] = 0;

        // 读入
        for (int i = 0; i < pigNum; i++) {
            String[] s = strings[i].split(" ");
            double x = Double.parseDouble(s[0]), y = Double.parseDouble(s[1]);
            pigs[i] = new PDD(x, y);
        }

        // 预处理，求经过任两只猪（i、j）的抛物线和对应的状态
        for (int i = 0; i < pigNum; i++) {
            path[i][i] = 1 << i;
            for (int j = 0; j < pigNum; j++) {
                double x1 = pigs[i].x, y1 = pigs[i].y;
                double x2 = pigs[j].x, y2 = pigs[j].y;
                if (cmp(x1, x2) == 0) continue;   // 两点在一排，无抛物线
                double a = (y1 / x1 - y2 / x2) / (x1 - x2);
                double b = y1 / x1 - a * x1;
                if (cmp(a, 0) >= 0) continue;
                int state = 0;
                for (int k = 0; k < pigNum; k++) {
                    double x = pigs[k].x, y = pigs[k].y;
                    if (cmp(y, a * x * x + b * x) == 0) state += 1 << k;
                }
                path[i][j] = state;
            }
        }

        // DP
        for (int i = 0; i + 1 < (1 << pigNum); i++) {   // 遍历所有未覆盖全部小猪的状态
            int x = 0;
            for (int j = 0; j < pigNum; j++) {
                if (((i >> j) & 1) == 0) {
                    x = j;
                    break;
                }
            }
            for (int j = 0; j < pigNum; j++) {
                f[i | path[x][j]] = Math.min(f[i | path[x][j]], f[i] + 1);
            }
        }

        return f[(1 << pigNum) - 1];
    }

    public int PreciousDeposits(int Num, String[] strings) {
        // 变量定义
        int M = 1 << Num;
        int[][] d = new int[Num][Num];   // d[i,j] 为 i 和 j 之间最小距离
        int[] g = new int[M];   // g[i] 表示 i 与 g[i] 状态一步相互转移，若 g[i]&j==j 则可由 i 一步到 j
        int[][] f = new int[M][Num];   // 状态定义：f[i][h],当前生成树的状态是i，且树的深度为h的方案 的最小代价

        // 初始化
        // d
        for (int i = 0; i < Num; i++) {
            Arrays.fill(d[i], inf);
            d[i][i] = 0;
        }
        for (String string : strings) {
            String[] s = string.split(" ");
            int a = Integer.parseInt(s[0]) - 1, b = Integer.parseInt(s[1]) - 1, w = Integer.parseInt(s[2]);
            d[a][b] = d[b][a] = Math.min(d[a][b], w);
        }
        // f
        for (int i = 0; i < M; i++) Arrays.fill(f[i], inf);
        for (int i = 0; i < Num; i++) f[1 << i][0] = 0;   // 赞助商决定免费赞助他打通一条从地面到某个宝藏屋的通道
        // g，获取 i 最多可一步到达的状态 g[i]
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < Num; j++) {
                if (((i >> j) & 1) == 1) {
                    for (int k = 0; k < Num; k++) {
                        if (d[j][k] != inf) {
                            g[i] |= 1 << k;
                        }
                    }
                }
            }
        }

        // DP
        for (int i = 1; i < M; i++) {
            // 遍历 i 的所有子集 j（不包含 i 和 0，从 i 到 i 没有意义），获取到 i 的最小总代价
            for (int j = (i - 1) & i; j != 0; j = (j - 1) & i) {
                if ((g[j] & i) != i) continue;   // 只对可以一步转移到 i 的进行处理
                int remain = j ^ i;   // 取得 j 到 i 差的位
                int cost = 0;   //  j 到 i 的花费（1 层）
                // 遍历所有缺的位 k，获取补全所缺位的最小花费
                for (int k = 0; k < Num; k++) {
                    if (((remain >> k) & 1) != 1) continue;
                    int tmp = inf;
                    // 获取 j 状态有的所有位 l，并比较 l 到缺的这个位 k 的代价，获取最小值
                    for (int l = 0; l < Num; l++) {
                        if (((j >> l) & 1) != 1) continue;
                        tmp = Math.min(tmp, d[l][k]);
                    }
                    cost += tmp;
                }
                for (int k = 1; k < Num; k++) {
                    f[i][k] = Math.min(f[i][k], f[j][k - 1] + cost * k);
                }
            }
        }

        // 获取结果，比较各层级总代价，获取最小总代价
        int ans = inf;
        for (int i = 0; i < Num; i++) {
            ans = Math.min(ans, f[M - 1][i]);
        }

        return ans;
    }

    /*---------------------** 内部类部分 **---------------------*/

    // 愤怒的小鸟
    private static class PDD {
        double x, y;

        public PDD(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
}
