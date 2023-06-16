package Algorithm.Improve.DynamicProgramming;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class BackpackModel {
    // 采药 https://www.acwing.com/activity/content/problem/content/1267/
    // 装箱问题 https://www.acwing.com/problem/content/1026/
    // 宠物小精灵之收服 https://www.acwing.com/problem/content/1024/
    // 数字组合 https://www.acwing.com/problem/content/280/
    // 买书 https://www.acwing.com/problem/content/1025/
    // 货币系统 https://www.acwing.com/problem/content/1023/

    private final int N = 3010;   // 采药 1010   装箱问题 20010   数字组合 110   货币系统 3010
    private final int[] vs = new int[N], ws = new int[N];   // 采药、装箱问题、数字组合、货币系统
    private final ArrayList<int[]> vw = new ArrayList<>();   // 宠物小精灵之收服
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

    public void add(int V1, int V2, int W) {
        vw.add(new int[]{V1, V2, W});
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
            int v = vw.get(i)[0], hp = vw.get(i)[1], w = vw.get(i)[2];
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
}
