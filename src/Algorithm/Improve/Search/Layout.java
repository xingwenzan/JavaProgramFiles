package Algorithm.Improve.Search;

public class Layout {
    private final int n;
    private int[] lst;
    private final int[][] copy;
    private int depth = 0;

    public Layout(int n, int[] lst) {
        this.n = n;
        this.lst = lst;
        copy = new int[5][n];
    }

    private int f() {
        int num = 0;
        for (int i = 0; i < n - 1; i++) {
            if (lst[i + 1] != lst[i] + 1) num++;
        }
        return (num + 2) / 3;
    }

    private boolean check() {
        for (int i = 0; i < n; i++) {
            if (lst[i] != i + 1) return false;
        }
        return true;
    }

    private boolean dfs(int u, int m) {
        if (u + f() > m) return false;
        if (check()) return true;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    copy[u] = lst.clone();
                    int x, y;
                    for (x = j + 1, y = i; x <= k; x++, y++) lst[y] = copy[u][x];
                    for (x = i; x <= j; x++, y++) lst[y] = copy[u][x];
                    if (dfs(u + 1, m)) return true;
                    lst = copy[u].clone();
                }
            }
        }
        return false;
    }

    public int getDepth() {
        while (depth < 5 && !dfs(0, depth)) depth++;
        return depth < 5 ? depth : -1;
    }
}
