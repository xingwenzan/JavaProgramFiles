package Algorithm.Improve.Search;

import java.util.Arrays;

/**
 * 回转游戏
 * <a href="https://www.acwing.com/problem/content/183/">...</a>
 */
public class SlalomGame {
    /*
    棋盘编号/索引
      0     1
      2     3
4  5  6  7  8  9  10
      11    12
13 14 15 16 17 18 19
      20    21
      22    23
     */
    private final int[] lst;
    private final int[][] op = {
            {0, 2, 6, 11, 15, 20, 22},   // A
            {1, 3, 8, 12, 17, 21, 23},   // B
            {10, 9, 8, 7, 6, 5, 4},   // C
            {19, 18, 17, 16, 15, 14, 13},   // D
            {23, 21, 17, 12, 8, 3, 1},   // E
            {22, 20, 15, 11, 6, 2, 0},   // F
            {13, 14, 15, 16, 17, 18, 19},   // G
            {4, 5, 6, 7, 8, 9, 10}   // H
    };
    private final int[] reOp = {5, 4, 7, 6, 1, 0, 3, 2}, center = {6, 7, 8, 11, 12, 15, 16, 17};
    private final int[] path = new int[100];
    private int depth = 0;


    public SlalomGame(int[] lst) {
        this.lst = lst;
    }

    private int f() {
        int[] num = new int[4];
        for (int i = 0; i < 8; i++) num[lst[center[i]]]++;
        return 8 - Arrays.stream(num).max().getAsInt();
    }

    private boolean check() {
        return f() == 0;
    }

    private void doing(int x) {
        int tmp = lst[op[x][0]];
        for (int i = 0; i < 6; i++) lst[op[x][i]] = lst[op[x][i + 1]];
        lst[op[x][6]] = tmp;
    }

    private boolean dfs(int u, int m, int last) {
        if (u + f() > m) return false;
        if (check()) return true;

        for (int i = 0; i < 8; i++) {
            if (last == reOp[i]) continue;
            path[u] = i;
            doing(i);
            if (dfs(u + 1, m, i)) return true;
            doing(reOp[i]);
        }

        return false;
    }

    public String getAns() {
        StringBuilder ans = new StringBuilder();
        while (!dfs(0, depth, -1)) depth++;
        if (depth == 0) return "No moves needed";
        for (int i = 0; i < depth; i++) {
            ans.append((char) (path[i] + 65));
        }
        return ans.toString();
    }

    public int getCenterNum() {
        return lst[6];
    }
}
