package Algorithm.Improve.DynamicProgramming;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class StateMachineModel {
    // 大盗阿福 https://www.acwing.com/problem/content/1051/
    // 股票买卖 IV https://www.acwing.com/problem/content/1059/
    // 股票买卖 V https://www.acwing.com/problem/content/1060/
    // 设计密码 https://www.acwing.com/problem/content/1054/   状态机 + KMP
    // 修复DNA https://www.acwing.com/problem/content/1055/

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

    /*修复DNA
    Tire 树 + AC 自动机
    AC 自动机
        用于多个字符串之间的前缀、后缀匹配，且由后缀指向前缀
        fail[i]
            用于存放 Tire 树中第 i 行应当指向的行数（fail[i] 行）
            这两行对应的是同一个字母，且在 fail[i]!=0 时上一个字母也相同
        学习链接   https://www.acwing.com/blog/content/695/
     */


    private final int N = 1010, INF = 0x3f3f3f3f;   // 修复DNA 1010
    private final int[][] tire = new int[N][4], f = new int[N][N];   // 修复DNA
    private final int[] ne = new int[N], dar = new int[N];
    private int idx = 0;


    public void init() {
        for (int i = 0; i < N; i++) {
            Arrays.fill(tire[i], 0);
            Arrays.fill(f[i], INF);
        }
        Arrays.fill(ne, 0);
        Arrays.fill(dar, 0);
        idx = 0;
        f[0][0] = 0;
    }

    private int get(char c) {
        if (c == 'A') {
            return 0;
        } else if (c == 'C') {
            return 1;
        } else if (c == 'G') {
            return 2;
        }
        return 3;
    }

    public void add(@NotNull String s) {
        int p = 0;
        for (int i = 0; i < s.length(); i++) {
            int c = get(s.charAt(i));
            if (tire[p][c] == 0) {
                tire[p][c] = ++idx;
            }
            p = tire[p][c];
        }
        dar[p] = 1;
    }

    private void build() {
        int[] q = new int[N];
        int hh = 0, tt = -1;
        for (int i = 0; i < 4; i++) {
            if (tire[0][i] != 0) {
                q[++tt] = tire[0][i];
            }
        }
        while (hh <= tt) {
            int t = q[hh++];
            for (int i = 0; i < 4; i++) {
                int p = tire[t][i];
                if (p == 0) {
                    tire[t][i] = tire[ne[t]][i];
                } else {
                    ne[p] = tire[ne[t]][i];
                    q[++tt] = p;
                    dar[p] |= dar[ne[p]];
                }
            }
        }
    }


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

    public int RepairDNA(@NotNull String s) {
        int m = s.length();
        build();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j <= idx; j++) {
                for (int k = 0; k < 4; k++) {
                    int t = get(s.charAt(i)) != k ? 1 : 0;
                    int p = tire[j][k];
                    if (dar[p] == 0) {
                        f[i + 1][p] = Math.min(f[i + 1][p], f[i][j] + t);
                    }
                }
            }
        }
        int ans = INF;
        for (int i = 0; i <= idx; i++) {
            ans = Math.min(ans, f[m][i]);
        }
        return ans == INF ? -1 : ans;
    }
}
