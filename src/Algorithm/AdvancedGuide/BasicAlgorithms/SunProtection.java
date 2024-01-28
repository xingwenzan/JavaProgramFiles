package Algorithm.AdvancedGuide.BasicAlgorithms;

import java.util.ArrayList;

/**
 * 防晒
 * <a href="https://www.acwing.com/problem/content/112/">...</a>
 */
public class SunProtection {
    private final int c;
    private final ArrayList<int[]> cows;
    private final int[] spf;
    private int ans = 0;

    public SunProtection(int c, ArrayList<int[]> cows, int[] spf) {
        this.c = c;
        this.cows = cows;
        this.spf = spf;
    }

    public int getAns() {
        cows.sort(((o1, o2) -> o2[0] - o1[0]));
        for (int i = 0; i < c; i++) {
            for (int j = 1000; j >= 1; j--) {
                if (spf[j] == 0) continue;
                if (cows.get(i)[0] <= j && j <= cows.get(i)[1]) {
                    ans++;
                    spf[j]--;
                    break;
                }
            }
        }
        return ans;
    }
}
