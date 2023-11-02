package Algorithm.AdvancedGuide.BasicAlgorithms;

/**
 * 费解的开关
 * <a href="https://www.acwing.com/problem/content/97/">...</a>
 */
public class ConfusingSwitch {
    private final char[][] state;
    private final char[][] backUp;
    private final int INF = 0x3f3f3f3f;
    private int minStep = INF;

    public ConfusingSwitch(char[][] state) {
        this.state = state;
        backUp = new char[5][5];
        count();
    }

    private void count() {
        for (int i = 0; i < (1 << 5); i++) {
            // 复制
            for (int j = 0; j < 5; j++) {
                System.arraycopy(state[j], 0, backUp[j], 0, 5);
            }
            minStep = Math.min(minStep, work(i));
            // 回滚
            for (int j = 0; j < 5; j++) {
                System.arraycopy(backUp[j], 0, state[j], 0, 5);
            }
        }
    }

    private int work(int op) {
        int ans = 0;
        // 根据输入状态改变第一行
        for (int i = 0; i < 5; i++) {
            if (((op >> i) & 1) == 1) {
                changeState(0, i);
                ans++;
            }
        }
        // 根据第一行改变确定整体状态
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (state[i][j] == '0') {
                    changeState(i + 1, j);
                    ans++;
                }
            }
        }
        // 判断最后一行是否变成目标状态
        for (int i = 0; i < 5; i++) {
            if (state[4][i] == '0') return INF;
        }
        return ans;
    }

    private void changeState(int x, int y) {
        int[] dx = new int[]{0, 0, -1, 0, 1, 0}, dy = new int[]{0, -1, 0, 1, 0};
        for (int i = 0; i < 5; i++) {
            int tx = x + dx[i], ty = y + dy[i];
            if (tx < 0 || ty < 0 || tx >= 5 || ty >= 5) continue;
            state[tx][ty] ^= 1;
        }
    }

    public int getMinStep() {
        return minStep;
    }
}
