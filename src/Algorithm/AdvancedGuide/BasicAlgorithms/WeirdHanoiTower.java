package Algorithm.AdvancedGuide.BasicAlgorithms;

import java.util.Arrays;

/**
 * 奇怪的汉诺塔
 * <a href="https://www.acwing.com/problem/content/98/">...</a>
 */
public class WeirdHanoiTower {
    private final int[] f;

    public WeirdHanoiTower(int n) {
        int[] d = new int[n + 1];
        this.f = new int[n + 1];
        int INF = 0x3f3f3f3f;
        Arrays.fill(f, INF);
        f[0] = 0;
        for (int i = 0; i < n + 1; i++) {
            d[i] = (1 << i) - 1;
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < i; j++) {
                f[i] = Math.min(f[i], f[j] * 2 + d[i - j]);
            }
        }
    }

    public int[] getF() {
        return f;
    }
}
