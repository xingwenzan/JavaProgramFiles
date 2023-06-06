package Algorithm.Improve.DynamicProgramming;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class LongestAscendingSubsequence {
    // 怪盗基德的滑翔翼 https://www.acwing.com/problem/content/1019/
    // 登山 https://www.acwing.com/problem/content/1016/
    // 合唱队形 https://www.acwing.com/problem/content/484/
    // 友好城市 https://www.acwing.com/problem/content/1014/
    // 最大上升子序列和 https://www.acwing.com/problem/content/1018/
    // 拦截导弹 https://www.acwing.com/problem/content/1012/
    // 导弹防御系统 https://www.acwing.com/problem/content/description/189/   暴搜


    // 怪盗基德的滑翔翼、合唱队形 110   登山、最大上升子序列和、拦截导弹 1010   友好城市 5010   导弹防御系统 55
    private final int N = 55;
    private final int[] e = new int[N];   // 怪盗基德的滑翔翼、合唱队形、登山
    private final ArrayList<int[]> e2 = new ArrayList<>();   // 友好城市
    private final int[] up = new int[N], down = new int[N];   // 导弹防御系统
    private int ans;   // 导弹防御系统


    // 怪盗基德的滑翔翼、合唱队形、登山、最大上升子序列和、拦截导弹
    private void add(String[] strings, int length) {
        for (int i = 0; i < length; i++) {
            e[i] = Integer.parseInt(strings[i]);
        }
    }

    // 友好城市
    public void add(String @NotNull [] strings) {
        e2.add(new int[]{Integer.parseInt(strings[0]), Integer.parseInt(strings[1])});
    }


    public int HangGliding(String[] strings, int length) {
        add(strings, length);
        int[] f = new int[N];
        int ans = 0;
        Arrays.fill(f, 1);
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if (e[i] < e[j]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            ans = Math.max(ans, f[i]);
        }
        Arrays.fill(f, 1);
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if (e[i] > e[j]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }

    public int Mountaineering(String[] strings, int length) {
        add(strings, length);
        int[] up = new int[N], down = new int[N];
        int ans = 0;
        Arrays.fill(up, 1);
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if (e[i] > e[j]) {
                    up[i] = Math.max(up[i], up[j] + 1);
                }
            }
        }
        Arrays.fill(down, 1);
        for (int i = length - 1; i >= 0; i--) {
            for (int j = length - 1; j > i; j--) {
                if (e[i] > e[j]) {
                    down[i] = Math.max(down[i], down[j] + 1);
                }
            }
        }
        for (int k = 0; k < length; k++) {
            ans = Math.max(ans, up[k] + down[k] - 1);
        }
        return ans;
    }

    public int ChorusFormation(String[] strings, int length) {
        return length - Mountaineering(strings, length);
    }

    public int SisterCity(int length) {
        e2.sort(Comparator.comparingInt(o -> o[0]));
        int ans = 0;
        int[] f = new int[length];
        Arrays.fill(f, 1);
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if (e2.get(i)[1] > e2.get(j)[1]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }

    public int ToSum(String[] strings, int length) {
        add(strings, length);
        int[] f = new int[N];
        int ans = 0;
        for (int i = 0; i < length; i++) {
            f[i] = e[i];
            for (int j = 0; j < i; j++) {
                if (e[i] > e[j]) {
                    f[i] = Math.max(f[i], f[j] + e[i]);
                }
            }
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }

    public int[] InterceptorMissile(String @NotNull [] strings) {
        int length = strings.length;
        add(strings, length);
        // f 最长上升子序列   g 贪心 各子序列最小的值   ans 返回值，前者是最长上升子序列长度，后者是贪心的最小子序列数
        int[] f = new int[length], g = new int[length], ans = new int[2];
        Arrays.fill(f, 1);
        Arrays.fill(g, (int) 1e9);
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if (e[i] <= e[j]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            ans[0] = Math.max(ans[0], f[i]);

            int tmp = 0;
            while (tmp < ans[1] && e[i] > g[tmp]) {
                tmp++;
            }
            if (tmp == ans[1]) {
                g[ans[1]++] = e[i];
            } else {
                g[tmp] = e[i];
            }
        }
        return ans;
    }

    private void dfs(int idx, int upLen, int downLen, int length) {   // 导弹防御系统
        /*
        输入：当前位置索引、up 数组分了几组（放了几个防御系统）、down 数组分了几组（放了几个防御系统）、导弹数量
        输出：无输出，直接改 ans 为最小多少个防御系统
         */
        if (upLen + downLen >= ans) {
            return;
        }
        if (idx == length) {
            ans = Math.min(ans, upLen + downLen);
            return;
        }
        int k = 0;
        while (k < upLen && e[idx] <= up[k]) {
            k++;
        }
        if (k < upLen) {
            int tmp = up[k];
            up[k] = e[idx];
            dfs(idx + 1, upLen, downLen, length);
            up[k] = tmp;
        } else {
            up[upLen] = e[idx];
            dfs(idx + 1, upLen + 1, downLen, length);
        }
        k = 0;
        while (k < downLen && e[idx] >= down[k]) {
            k++;
        }
        if (k < downLen) {
            int tmp = down[k];
            down[k] = e[idx];
            dfs(idx + 1, upLen, downLen, length);
            down[k] = tmp;
        } else {
            down[downLen] = e[idx];
            dfs(idx + 1, upLen, downLen + 1, length);
        }
    }

    public int MissileDefenseSystem(String[] strings, int length) {
        add(strings, length);
        ans = length;
        dfs(0, 0, 0, length);
        return ans;
    }
}
