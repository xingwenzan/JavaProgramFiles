package Algorithm.Improve.DynamicProgramming;

public class BackpackModel {
    // 采药 https://www.acwing.com/activity/content/problem/content/1267/

    private final int N = 1010;   // 采药 1010
    private final int[] f = new int[N], vs = new int[N], ws = new int[N];
    private int idx = 0;


    public void add(int V, int W) {
        vs[idx] = V;
        ws[idx] = W;
        idx++;
    }


    public int CollectHerbs(int V) {
        for (int i = 0; i < idx; i++) {
            int v = vs[i], w = ws[i];
            for (int j = V; j >= v; j--) {
                f[j] = Math.max(f[j], f[j - v] + w);
            }
        }
        return f[V];
    }
}
