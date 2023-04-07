package Algorithm.AlgorithmTemplate.MathematicalKnowledge;

public class CombinatorialNumbers {
    // 组合数 C_a^b 四种方法区别在于适合的数据范围不同
    // 求组合数 I https://www.acwing.com/problem/content/887/ 本模板是先预处理出来所有情况，再直接选

    private final int N = 2010;
    private final int[][] CabI = new int[N][N];

    public void initI() {
        for (int a = 0; a < N; a++) {
            for (int b = 0; b <= a; b++) {
                if (b == 0) {
                    CabI[a][b] = 1;
                } else {
                    int mod = (int) 1e9 + 7;
                    CabI[a][b] = (CabI[a - 1][b] + CabI[a - 1][b - 1]) % mod;
                }
            }
        }
    }

    public int findI(int a, int b) {
        return CabI[a][b];
    }
}
