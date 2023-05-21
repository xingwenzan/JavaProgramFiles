package Algorithm.Basic.DynamicProgramming;

import java.util.Arrays;

public class MemorySearch {
    // 滑雪 https://www.acwing.com/problem/content/903/
    private final int N = 310;
    private int row, col;
    private final int[][] high = new int[N][N], state = new int[N][N];

    public void init(int r, int c) {
        for (int i = 0; i < N; i++) {
            Arrays.fill(state[i], -1);
        }
        row = r;
        col = c;
    }

    public void add(int r, int c, int num) {
        high[r][c] = num;
    }

    private int dp(int r, int c) {
        if (state[r][c] != -1) {
            return state[r][c];
        }
        state[r][c] = 1;
        int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
        for (int i = 0; i < 4; i++) {
            int x = r + dx[i], y = c + dy[i];
            if (x >= 0 && x < row && y >= 0 && y < col && high[x][y] < high[r][c]) {
                state[r][c] = Math.max(state[r][c], dp(x, y) + 1);
            }
        }
        return state[r][c];
    }

    public int skiing() {
        int ans = 1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                ans = Math.max(ans, dp(i, j));
            }
        }
        return ans;
    }
}
