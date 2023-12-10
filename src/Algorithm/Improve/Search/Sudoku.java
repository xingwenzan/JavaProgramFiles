package Algorithm.Improve.Search;

import java.util.HashMap;

/**
 * 数独
 * <a href="https://www.acwing.com/problem/content/168/">...</a>
 */
public class Sudoku {
    private final int N = 9, M = 1 << N;
    private final int[] row = new int[N], col = new int[N], oneNum = new int[M];
    private final int[][] cell = new int[3][3];
    private final HashMap<Integer, Integer> onePoint = new HashMap<>();
    private char[] s;

    public Sudoku() {
        for (int i = 0; i < N; i++) {
            onePoint.put(1 << i, i);
        }
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                oneNum[i] += (i >> j) & 1;
            }
        }
    }

    private int lowBit(int x) {
        return x & (-x);
    }

    private int get(int x, int y) {
        return row[x] & col[y] & cell[x / 3][y / 3];
    }

    private int init() {
        for (int i = 0; i < N; i++) row[i] = col[i] = M - 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cell[i][j] = M - 1;
            }
        }
        int cnt = 0;
        for (int i = 0, k = 0; i < N; i++) {
            for (int j = 0; j < N; j++, k++) {
                if (s[k] == '.') cnt++;
                else draw(i, j, s[k] - '1', true);
            }
        }
        return cnt;
    }

    private void draw(int x, int y, int t, boolean st) {
        if (st) s[x * 9 + y] = (char) (t + '1');
        else s[x * 9 + y] = '.';

        int p = st ? (1 << t) : -(1 << t);
        row[x] -= p;
        col[y] -= p;
        cell[x / 3][y / 3] -= p;
    }

    private boolean dfs(int u) {
        if (u == 0) return true;

        int minV = 10, minX = -1, minY = -1;
        int tmp;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (s[i * N + j] != '.') continue;
                tmp = oneNum[get(i, j)];
                if (tmp < minV) {
                    minV = tmp;
                    minX = i;
                    minY = j;
                }
            }
        }

        for (int i = get(minX, minY); i != 0; i -= lowBit(i)) {
            tmp = onePoint.get(lowBit(i));
            draw(minX, minY, tmp, true);
            if (dfs(u - 1)) return true;
            draw(minX, minY, tmp, false);
        }

        return false;
    }

    public String getAns(String string) {
        s = string.toCharArray();
        int cnt = init();

        dfs(cnt);

        return new String(s);
    }
}
