package Algorithm.Improve.DynamicProgramming;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

public class BackpackModel {
    // 采药 https://www.acwing.com/activity/content/problem/content/1267/
    // 装箱问题 https://www.acwing.com/problem/content/1026/
    // 宠物小精灵之收服 https://www.acwing.com/problem/content/1024/
    // 数字组合 https://www.acwing.com/problem/content/280/
    // 买书 https://www.acwing.com/problem/content/1025/
    // 货币系统 https://www.acwing.com/problem/content/1023/
    // 货币系统-NOIP https://www.acwing.com/problem/content/534/
    // 多重背包问题 III https://www.acwing.com/problem/content/6/
    // 庆功会 https://www.acwing.com/problem/content/1021/
    // 混合背包问题 https://www.acwing.com/problem/content/7/
    // 二维费用的背包问题 https://www.acwing.com/problem/content/8/
    // 潜水员 https://www.acwing.com/problem/content/1022/
    // 机器分配 https://www.acwing.com/problem/content/1015/
    // 开心的金明 https://www.acwing.com/problem/content/428/

    /* 多重背包问题 III
    多重背包的单调队列优化方法
    https://www.acwing.com/solution/content/6500/
    https://www.acwing.com/solution/content/53507/
    https://www.acwing.com/activity/content/code/content/117236/
     */

    /* 混合背包问题
    先判断是否完全，否则使用二进制优化多重解决
     */

    /*开心的金明
    做法同采药，就不花费时间精力了
     */

    // 采药 1010   装箱问题 20010   数字组合 110   货币系统 3010   货币系统-NOIP 110   开心的金明 30010
    private final int N = 110;
    private final int[] vs = new int[N], ws = new int[N];   // 采药、装箱问题、数字组合、货币系统
    private final ArrayList<int[]> parameter = new ArrayList<>();   // 宠物小精灵之收服、多重背包问题 III、庆功会、混合背包问题
    private int idx = 0;


    private void add(String @NotNull [] V) {
        for (String v : V) {
            vs[idx++] = Integer.parseInt(v);
        }
    }

    public void add(int V) {
        vs[idx] = V;
        idx++;
    }

    public void add(int V, int W) {
        vs[idx] = V;
        ws[idx] = W;
        idx++;
    }

    /*
    宠物小精灵之收服   V HP W
    多重背包问题 III、庆功会、混合背包问题   V W S
    二维费用的背包问题   V M W
    潜水员   V1 V2 W
     */
    public void add(int V, int P1, int P2) {
        parameter.add(new int[]{V, P1, P2});
        idx++;
    }

    /*
    机器分配   该公司性质不同数量机器带来的价值   最多机器数量
     */
    public void add(String[] strings, int num) {
        int[] tmp = new int[num + 1];
        tmp[0] = 0;
        for (int i = 1; i <= num; i++) {
            tmp[i] = Integer.parseInt(strings[i - 1]);
        }
        parameter.add(tmp);
        idx++;
    }


    public int CollectHerbs(int V) {
        int[] f = new int[N];
        for (int i = 0; i < idx; i++) {
            int v = vs[i], w = ws[i];
            for (int j = V; j >= v; j--) {
                f[j] = Math.max(f[j], f[j - v] + w);
            }
        }
        return f[V];
    }

    public int PackingProblem(int V) {
        return V - CollectHerbs(V);
    }

    public int[] PokemonConquer(int V, int HP) {
        int[][] f = new int[V + 10][HP + 10];
        int[] ans = new int[2];
        for (int i = 0; i < idx; i++) {
            int v = parameter.get(i)[0], hp = parameter.get(i)[1], w = parameter.get(i)[2];
            for (int j = V; j >= v; j--) {
                for (int k = HP; k >= hp; k--) {
                    f[j][k] = Math.max(f[j][k], f[j - v][k - hp] + w);
                }
            }
        }
        ans[0] = f[V][HP - 1];
        int tmp = HP - 1;
        while (tmp > 0 && f[V][tmp - 1] == ans[0]) {
            tmp--;
        }
        ans[1] = HP - tmp;
        return ans;
    }

    public int NumberCombinations(String[] strings, int V) {
        add(strings);
        int[] f = new int[V + 10];
        f[0] = 1;
        for (int i = 0; i < idx; i++) {
            for (int j = V; j >= vs[i]; j--) {
                f[j] += f[j - vs[i]];
            }
        }
        return f[V];
    }

    public int BuyBooks(int V) {
        int[] v = {10, 20, 50, 100}, f = new int[V + 10];
        f[0] = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = v[i]; j <= V; j++) {
                f[j] += f[j - v[i]];
            }
        }
        return f[V];
    }

    public long MonetarySystem(int V) {
        long[] f = new long[V + 10];
        f[0] = 1;
        for (int i = 0; i < idx; i++) {
            int v = vs[i];
            for (int j = v; j <= V; j++) {
                f[j] += f[j - v];
            }
        }
        return f[V];
    }

    public int MonetarySystemNOIP(String[] strings) {
        idx = 0;
        add(strings);
        Arrays.sort(vs, 0, idx);
        int maxNum = vs[idx - 1];
        boolean[] f = new boolean[maxNum + 10];
        Arrays.fill(f, false);
        f[0] = true;
        int ans = 0;
        for (int i = 0; i < idx; i++) {
            int v = vs[i];
            if (!f[v]) {
                ans++;
            }
            for (int j = v; j <= maxNum; j++) {
                f[j] |= f[j - v];
            }
        }
        return ans;
    }

    public int MultipleKnapsackIII(int V) {
        int[] f = new int[V + 10];
        for (int o = 0; o < idx; o++) {
            int v = parameter.get(o)[0], w = parameter.get(o)[1], s = parameter.get(o)[2];
            int[] cf = f.clone();   // copy f
            for (int i = 0; i < v; i++) {   // 模 v 的余数
                int[] ids = new int[V + 10];   // 用于记录单调队列窗口中留下的数的索引 —— 索引数组
                int hh = 0, tt = -1;   // 单调队列索引数组窗口的头索引、尾索引，用于框住窗口
                for (int j = i; j <= V; j += v) {
                    if (hh <= tt && ids[hh] < j - s * v) {   // 单调队列窗口不空且头在窗口外，砍头
                        hh++;
                    }
                    while (hh <= tt && cf[ids[tt]] - (ids[tt] - i) / v * w <= cf[j] - (j - i) / v * w) {   // 保证窗口单调递减，否则断尾
                        tt--;
                    }
                    ids[++tt] = j;
                    f[j] = cf[ids[hh]] + (j - ids[hh]) / v * w;   // 更新 f[j] 最大值
                }
            }
        }
        return f[V];
    }

    public int VictoryMeeting(int V) {
        int[] f = new int[V + 10];
        for (int o = 0; o < idx; o++) {
            int v = parameter.get(o)[0], w = parameter.get(o)[1], s = parameter.get(o)[2];
            for (int i = V; i >= 0; i--) {
                for (int j = 0; j <= s && j * v <= i; j++) {
                    f[i] = Math.max(f[i], f[i - j * v] + j * w);
                }
            }
        }
        return f[V];
    }

    public int MixedKnapsack(int V) {
        int[] f = new int[V + 10];
        for (int o = 0; o < idx; o++) {
            int v = parameter.get(o)[0], w = parameter.get(o)[1], s = parameter.get(o)[2];
            if (s == 0) {   // 完全
                for (int i = v; i <= V; i++) {
                    f[i] = Math.max(f[i], f[i - v] + w);
                }
            } else {
                if (s == -1) {
                    s = 1;
                }
                for (int k = 1; k <= s; k *= 2) {
                    for (int i = V; i >= k * v; i--) {
                        f[i] = Math.max(f[i], f[i - k * v] + k * w);
                    }
                    s -= k;
                }
                if (s > 0) {
                    for (int i = V; i >= s * v; i--) {
                        f[i] = Math.max(f[i], f[i - s * v] + s * w);
                    }
                }
            }
        }
        return f[V];
    }

    public int TwoDimensionalCost(int V, int M) {
        int[][] f = new int[V + 10][M + 10];
        for (int i = 0; i < V + 10; i++) {
            Arrays.fill(f[i], 0);
        }
        for (int o = 0; o < idx; o++) {
            int v = parameter.get(o)[0], m = parameter.get(o)[1], w = parameter.get(o)[2];
            for (int i = V; i >= v; i--) {
                for (int j = M; j >= m; j--) {
                    f[i][j] = Math.max(f[i][j], f[i - v][j - m] + w);
                }
            }
        }
        return f[V][M];
    }

    public int Diver(int V1, int V2) {
        int[][] f = new int[V1 + 10][V2 + 10];
        for (int i = 0; i < V1 + 10; i++) {
            Arrays.fill(f[i], (int) 1e9);
        }
        f[0][0] = 0;
        for (int o = 0; o < idx; o++) {
            int v1 = parameter.get(o)[0], v2 = parameter.get(o)[1], w = parameter.get(o)[2];
            for (int i = V1; i >= 0; i--) {
                for (int j = V2; j >= 0; j--) {
                    f[i][j] = Math.min(f[i][j], f[Math.max(0, i - v1)][Math.max(0, j - v2)] + w);
                }
            }
        }
        return f[V1][V2];
    }

    private int[][] MachineDistribution(int companyNum, int machineNum) {
        int[][] f = new int[companyNum + 1][machineNum + 1];
        for (int i = 0; i < companyNum; i++) {
            Arrays.fill(f[i], 0);
        }
        for (int i = 1; i <= companyNum; i++) {   // 公司
            int[] w = parameter.get(i - 1);
            for (int j = 1; j <= machineNum; j++) {   // 体积（最多 j 个机器的情况）
                for (int k = 0; k <= j; k++) {   //  i 号 公司 k 个机器的价值
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - k] + w[k]);
                }
            }
        }
        return f;
    }

    public void MachineDistributionOut(int companyNum, int machineNum) {
        int[][] f = MachineDistribution(companyNum, machineNum);
        System.out.println(f[companyNum][machineNum]);
        int[] num = new int[companyNum + 1];   // 每个公司的个数
        int j = machineNum;
        for (int i = companyNum; i > 0; i--) {
            int[] w = parameter.get(i - 1);
            for (int k = 0; k <= j; k++) {
                if (f[i][j] == f[i - 1][j - k] + w[k]) {
                    num[i] = k;
                    j -= k;
                    break;
                }
            }
        }
        for (int i = 1; i <= companyNum; i++) {
            System.out.printf("%d %d\n", i, num[i]);
        }
    }
}
