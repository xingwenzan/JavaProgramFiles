package Algorithm.Basic.DynamicProgramming;

public class CountingDP {
    /*
    整数划分 https://www.acwing.com/problem/content/902/
    https://www.acwing.com/video/332/
    https://www.acwing.com/activity/content/code/content/62496/
     */
    private final int N = 1010, mod = (int) 1e9 + 7;

    public int bag(int x) {
        int[] f = new int[N];
        f[0] = 1;
        for (int i = 1; i <= x; i++) {
            for (int j = i; j <= x; j++) {
                f[j] = (f[j] + f[j - i]) % mod;
            }
        }
        return f[x];
    }

    public int other(int x) {
        int[][] f = new int[N][N];
        f[1][1] = 1;
        for (int i = 2; i <= x; i++) {
            for (int j = 1; j <= i; j++) {
                f[i][j] = (f[i - 1][j - 1] + f[i - j][j]) % mod;
            }
        }
        int ans = 0;
        for (int i = 1; i <= x; i++) {
            ans = (ans + f[x][i]) % mod;
        }
        return ans;
    }
}
