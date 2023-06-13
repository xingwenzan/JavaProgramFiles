package Algorithm.Improve.DynamicProgramming;

import java.util.ArrayList;

public class BackpackModel {
    // 采药 https://www.acwing.com/activity/content/problem/content/1267/
    // 装箱问题 https://www.acwing.com/problem/content/1026/
    // 宠物小精灵之收服 https://www.acwing.com/problem/content/1024/

    private final int N = 20010;   // 采药 1010   装箱问题 20010
    private final int[] vs = new int[N], ws = new int[N];   // 采药、装箱问题
    private final ArrayList<int[]> vw = new ArrayList<>();   // 宠物小精灵之收服
    private int idx = 0;


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
}
