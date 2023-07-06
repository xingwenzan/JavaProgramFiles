package Algorithm.Improve.DynamicProgramming;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class BackpackModel {
    /*题目及链接
        采药 https://www.acwing.com/activity/content/problem/content/1267/
        装箱问题 https://www.acwing.com/problem/content/1026/
        宠物小精灵之收服 https://www.acwing.com/problem/content/1024/
        数字组合 https://www.acwing.com/problem/content/280/
        买书 https://www.acwing.com/problem/content/1025/
        货币系统 https://www.acwing.com/problem/content/1023/
        货币系统-NOIP https://www.acwing.com/problem/content/534/
        多重背包问题 III https://www.acwing.com/problem/content/6/
        庆功会 https://www.acwing.com/problem/content/1021/
        混合背包问题 https://www.acwing.com/problem/content/7/
        二维费用的背包问题 https://www.acwing.com/problem/content/8/
        潜水员 https://www.acwing.com/problem/content/1022/
        机器分配 https://www.acwing.com/problem/content/1015/
        开心的金明 https://www.acwing.com/problem/content/428/
        有依赖的背包问题 https://www.acwing.com/problem/content/10/
        背包问题求方案数 https://www.acwing.com/problem/content/11/
        背包问题求具体方案 https://www.acwing.com/problem/content/12/
        能量石 https://www.acwing.com/problem/content/736/
        金明的预算方案 https://www.acwing.com/problem/content/489/    数据存储比较麻烦，不写了
     */

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

    /*有依赖的背包问题
    树形 DP --(发现)--> 分组背包辅组解决，减少复杂度
     */

    /*N
        采药、背包问题求方案数、背包问题求具体方案   1010
        装箱问题   20010
        数字组合、有依赖的背包问题、货币系统-NOIP   110
        货币系统   3010
        开心的金明   30010
     */
    private final int N = 110;
    // 采药、装箱问题、数字组合、货币系统、有依赖的背包问题
    private final int[] vs = new int[N], ws = new int[N];
    // 宠物小精灵之收服、多重背包问题 III、庆功会、混合背包问题、能量石
    private final ArrayList<int[]> parameter = new ArrayList<>();
    private final int[] h = new int[N], e = new int[N], p = new int[N];   // 有依赖的背包问题
    private final int[][] fdb = new int[N][N];   // f of Dependent Backpack
    /*
    idx
        采药、装箱问题、数字组合、货币系统、能量石   第 i 个物品的 v、w
        有依赖的背包问题   树中存放的第 i 个节点
    root
        有依赖的背包问题   根节点
     */
    private int idx = 0, root = 0;


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
    能量石   S E L
     */
    public void add(int V, int P1, int P2) {
        if (idx == 0) {
            parameter.clear();
        }
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

    public void add(int x, int father, int vx, int wx) {
        vs[x] = vx;
        ws[x] = wx;
        if (father == -1) {
            root = x;
        } else {
            e[idx] = x;
            p[idx] = h[father];
            h[father] = idx++;
        }
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

    private void dfs(int u, int V) {
        // 分组背包选择子树（此时仅管子树体积情况，需后续添加 u 节点）
        for (int i = h[u]; i != -1; i = p[i]) {   // 遍历该节点的每一个子节点
            int son = e[i];
            dfs(son, V);
            for (int j = V - vs[u]; j >= 0; j--) {   // 遍历体积（子树及 u 总共占用体积），留不出 u 体积的不需要考虑
                for (int k = 0; k <= j; k++) {   // 遍历剩余体积（子树占用体积）
                    fdb[u][j] = Math.max(fdb[u][j], fdb[u][j - k] + fdb[son][k]);
                }
            }
        }
        // 添加 u 节点
        for (int i = V; i >= vs[u]; i--) {
            // 可存下 u 的加上 u
            fdb[u][i] = fdb[u][i - vs[u]] + ws[u];
        }
        for (int i = 0; i < vs[u]; i++) {
            // 放不下 u 的全清零
            fdb[u][i] = 0;
        }
    }

    public void dependentInit() {
        Arrays.fill(h, -1);
    }

    public int Dependent(int V) {
        dfs(root, V);
        return fdb[root][V];
    }

    public long PlanNumber(int V) {
        int mod = (int) 1e9 + 7;
        int[] f = new int[V + 10];
        Arrays.fill(f, 0);
        long[] g = new long[V + 10];
        Arrays.fill(g, 1);
        for (int i = 0; i < idx; i++) {
            int v = vs[i], w = ws[i];
            for (int j = V; j >= v; j--) {
                int newW = f[j - v] + w;
                if (f[j] < newW) {
                    f[j] = newW;
                    g[j] = g[j - v];
                } else if (f[j] == newW) {
                    g[j] = (g[j] + g[j - v]) % mod;
                }
            }
        }
        return g[V];
    }

    private int[][] SpecificPlan(int V) {
        int[][] f = new int[idx + 10][V + 10];
        for (int i = 0; i < idx + 10; i++) {
            Arrays.fill(f[i], 0);
        }
        for (int i = idx - 1; i >= 0; i--) {
            for (int j = 0; j <= V; j++) {
                f[i][j] = f[i + 1][j];
                if (j >= vs[i]) {
                    f[i][j] = Math.max(f[i][j], f[i + 1][j - vs[i]] + ws[i]);
                }
            }
        }
        return f;
    }

    public void SpecificPlanOut(int V) {
        int[][] f = SpecificPlan(V);
        int j = V;
        for (int i = 0; i < idx; i++) {
            if (j >= vs[i] && f[i][j] == f[i + 1][j - vs[i]] + ws[i]) {
                System.out.printf("%d ", i + 1);
                j -= vs[i];
            }
        }
    }

    public int EnergyStone() {
        parameter.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                double tmp = (o1[0] / (o1[2] + 1e-10) - o2[0] / (o2[2] + 1e-10));
                if (tmp > 0) {
                    return 1;
                } else if (tmp < 0) {
                    return -1;
                }
                return 0;
            }
        });
        int V = (int) 1e4;
        int[] f = new int[V + 10];
        Arrays.fill(f, 0);
        for (int i = 0; i < idx; i++) {
            int s = parameter.get(i)[0], e = parameter.get(i)[1], l = parameter.get(i)[2];
            for (int j = V; j >= s; j--) {
                f[j] = Math.max(f[j], f[j - s] + Math.max(0, e - (j - s) * l));
            }
        }
        idx = 0;
        int ans = 0;
        for (int i = 0; i < V + 10; i++) {
            ans = Math.max(f[i], ans);
        }
        return ans;
    }
}
