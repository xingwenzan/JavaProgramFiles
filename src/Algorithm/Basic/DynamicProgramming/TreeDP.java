package Algorithm.Basic.DynamicProgramming;

import java.util.Arrays;

public class TreeDP {
    // 没有上司的舞会 https://www.acwing.com/problem/content/287/
    private final int N = 6010;
    private final int[] h = new int[N], e = new int[N], p = new int[N];
    private int idx = 0;
    private final int[] happy = new int[N];
    private final boolean[] hasFather = new boolean[N];
    private final int[][] f = new int[N][2];

    public void init() {
        Arrays.fill(h, -1);
    }

    public void add(int father, int son) {
        e[idx] = son;
        p[idx] = h[father];
        h[father] = idx++;
        hasFather[son] = true;
    }

    public void setHappy(int num, int pointer) {
        happy[pointer] = num;
    }

    private void dfs(int father) {
        f[father][1] = happy[father];
        for (int pointer = h[father]; pointer != -1; pointer = p[pointer]) {
            int son = e[pointer];
            dfs(son);
            f[father][0] += Math.max(f[son][0], f[son][1]);
            f[father][1] += f[son][0];
        }
    }

    public int promWithoutBoss() {
        int root = 1;
        while (hasFather[root]) {
            root++;
        }
        dfs(root);
        return Math.max(f[root][0], f[root][1]);
    }
}