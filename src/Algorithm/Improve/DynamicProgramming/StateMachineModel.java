package Algorithm.Improve.DynamicProgramming;

public class StateMachineModel {
    // 大盗阿福 https://www.acwing.com/problem/content/1051/
    public int ThiefAlfred(String[] strings) {
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
}
