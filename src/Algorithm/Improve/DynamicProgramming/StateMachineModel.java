package Algorithm.Improve.DynamicProgramming;

import org.jetbrains.annotations.NotNull;

public class StateMachineModel {
    // 大盗阿福 https://www.acwing.com/problem/content/1051/
    // 股票买卖 IV https://www.acwing.com/problem/content/1059/
    // 股票买卖 V https://www.acwing.com/problem/content/1060/
    // 设计密码 https://www.acwing.com/problem/content/1054/   状态机 + KMP

    /*股票买卖
    股票买卖 IV
        f[i][0/1]
        f[i][0] 第 i 次交易手中无票
        f[i][1] 第 i 次交易手中有票
    股票买卖 V
        f[i][0/1/2]
        f[i][0] 第 i 天交易手中有票
        f[i][1] 第 i 天交易手中无票且在冷静期
        f[i][2] 第 i 天交易手中无票且不在冷静期
     */


    public int ThiefAlfred(String @NotNull [] strings) {
        int N = strings.length;
        int[][] f = new int[N + 10][2];
        f[0][0] = 0;
        f[0][1] = 0;
        for (int i = 1; i <= N; i++) {
            int w = Integer.parseInt(strings[i - 1]);
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][1]);
            f[i][1] = f[i - 1][0] + w;
        }
        return Math.max(f[N][0], f[N][1]);
    }

    public int StockTradingIV(String @NotNull [] strings, int k) {
        int[][] f = new int[k + 10][2];
        for (int i = 0; i < strings.length; i++) {
            int w = Integer.parseInt(strings[i]);
            for (int j = 1; j <= k; j++) {
                if (i == 0) {
                    f[j][1] = -w;
                } else {
                    f[j][0] = Math.max(f[j][0], f[j][1] + w);
                    f[j][1] = Math.max(f[j][1], f[j - 1][0] - w);
                }
            }
        }
        return f[k][0];
    }

    public int StockTradingV(String @NotNull [] strings) {
        int N = strings.length;
        int[] f = new int[3];
        f[0] = -Integer.parseInt(strings[0]);
        for (int i = 1; i < N; i++) {
            f[0] = Math.max(f[0], f[2] - Integer.parseInt(strings[i]));
            f[2] = Math.max(f[1], f[2]);
            f[1] = f[0] + Integer.parseInt(strings[i]);
        }
        return Math.max(f[1], f[2]);
    }

    public int DesignPassword(@NotNull String s, int n) {
        int m = s.length();
        s = " " + s;
        int[] ne = new int[n + 10];
        int[][] f = new int[n + 10][m + 10];
        int mod = (int) 1e9 + 7;

        for (int i = 2, j = 0; i <= m; i++) {
            while (j != 0 && s.charAt(i) != s.charAt(j + 1)) {
                j = ne[j];
            }
            if (s.charAt(i) == s.charAt(j + 1)) {
                j++;
            }
            ne[i] = j;
        }

        f[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < 26; k++) {
                    char letter = (char) (k + 'a');
                    int u = j;
                    while (u != 0 && letter != s.charAt(u + 1)) {
                        u = ne[u];
                    }
                    if (letter == s.charAt(u + 1)) {
                        u++;
                    }
                    if (u < m) {
                        f[i][u] = (f[i][u] + f[i - 1][j]) % mod;
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            ans = (f[n][i] + ans) % mod;
        }
        return ans;
    }
}
