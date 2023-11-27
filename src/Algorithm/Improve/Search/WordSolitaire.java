package Algorithm.Improve.Search;

import org.jetbrains.annotations.NotNull;

/**
 * 单词接龙
 * <a href="https://www.acwing.com/problem/content/1119/">...</a>
 */
public class WordSolitaire {
    private final int n;
    private final char start;
    private final String[] words;
    private final int[] used;
    private final int[][] g;
    private int ans;

    public WordSolitaire(int n, char start, String[] words) {
        this.n = n;
        this.start = start;
        this.words = words;
        used = new int[n];
        g = new int[n][n];
    }

    private void dfs(@NotNull String dragon, int idx) {
        ans = Math.max(ans, dragon.length());

        used[idx]++;
        for (int i = 0; i < n; i++) {
            if (g[idx][i] == 0) continue;
            if (used[i] >= 2) continue;
            dfs(dragon + words[i].substring(g[idx][i]), i);
        }
        used[idx]--;
    }

    private void init() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                String a = words[i], b = words[j];
                for (int k = 1; k < Math.min(a.length(), b.length()); k++) {
                    if (a.substring(a.length() - k).equals(b.substring(0, k))) {
                        g[i][j] = k;
                        break;
                    }
                }
            }
        }
    }

    public int getAns() {
        init();
        for (int i = 0; i < n; i++) {
            if (words[i].charAt(0) == start) dfs(words[i], i);
        }
        return ans;
    }
}
