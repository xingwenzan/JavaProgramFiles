package Algorithm.AlgorithmTemplate.MathematicalKnowledge;

public class CombinatorialNumbers {
    // 组合数 C_a^b 四种方法区别在于适合的数据范围不同
    // 求组合数 I https://www.acwing.com/problem/content/887/   本模板是先预处理出来所有情况，再直接选
    // 求组合数 II https://www.acwing.com/problem/content/888/   C_a^b = a!/[(a-b)!*b!] 预处理出 i! 和 (i!)^(-1) (i! 的逆元）

    private final int NI = 2010, NII = (int) 1e5 + 10, mod = (int) 1e9 + 7;
    private final int[][] CabI = new int[NI][NI];
    private final long[] fact = new long[NII], infact = new long[NII];

    public void initI() {
        for (int a = 0; a < NI; a++) {
            for (int b = 0; b <= a; b++) {
                if (b == 0) {
                    CabI[a][b] = 1;
                } else {
                    CabI[a][b] = (CabI[a - 1][b] + CabI[a - 1][b - 1]) % mod;
                }
            }
        }
    }

    public void initII() {
        FastPower fastPower = new FastPower();
        fact[0] = infact[0] = 1;
        for (int i = 1; i < NII; i++) {
            fact[i] = fact[i - 1] * i % mod;
            infact[i] = infact[i - 1] * fastPower.fastPower(i, mod - 2, mod) % mod;
        }
    }

    public int findI(int a, int b) {
        return CabI[a][b];
    }

    public long findII(int a, int b) {
        return fact[a] * infact[a - b] % mod * infact[b] % mod;
    }
}
