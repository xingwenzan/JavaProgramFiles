package Algorithm.AlgorithmBasicCourse.MathematicalKnowledge;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CombinatorialNumbers {
    // 组合数 C_a^b 四种方法区别在于适合的数据范围不同
    // 求组合数 I https://www.acwing.com/problem/content/887/   本模板是先预处理出来所有情况，再直接选
    // 求组合数 II https://www.acwing.com/problem/content/888/   C_a^b = a!/[(a-b)!*b!] 预处理出 i! 和 (i!)^(-1) (i! 的逆元）
    // 求组合数 III https://www.acwing.com/problem/content/889/   C_a^b ≡ C_{a%p}^{b%p} * C_{a//p}^{b//p}
    // 求组合数 IV https://www.acwing.com/problem/content/890/   筛质数 - a!分解 - 高精度计算
    // 满足条件的01序列 https://www.acwing.com/problem/content/891/   n 的卡特兰数 C_2n^n -C_2n^{n-1} = (C_2n^n)/(n+1)

    private final int NI = 2010, NII = (int) 1e5 + 10, mod = (int) 1e9 + 7, NIV = 5010;
    private int pointer = 0;
    private final int[][] CabI = new int[NI][NI];
    private final long[] fact = new long[NII], infact = new long[NII];
    private final int[] primes = new int[NIV], powers = new int[NIV];
    private final boolean[] state = new boolean[NIV];

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

    public long Cab(int a, int b, int mod) {
        if (b > a) {
            return 0;
        }
        long ans = 1;
        FastPower fastPower = new FastPower();
        for (int i = 1, j = a; i <= b; i++, j--) {
            ans = ans * j % mod;
            ans = ans * fastPower.fastPower(i, mod - 2, mod) % mod;
        }
        return ans;
    }

    public long lucas(long a, long b, int mod) {
        if (a < mod && b < mod) {
            return Cab((int) a, (int) b, mod);
        }
        return Cab((int) (a % mod), (int) (b % mod), mod) * lucas(a / mod, b / mod, mod) % mod;
    }

    public void getPrime(int num) {   // 筛出到 num 的质数
        for (int i = 2; i <= num; i++) {
            if (!state[i]) {
                primes[pointer++] = i;
            }
            for (int j = 0; j < pointer; j++) {
                if (i * primes[j] <= num) {
                    state[i * primes[j]] = true;
                }
                if (i % primes[j] == 0) {
                    break;
                }
            }
        }
    }

    public int getPower(int num, int prime) {   // 得出 num! 的质因数 prime 次幂数
        int cnt = 0;
        while (num > 0) {
            cnt += num / prime;
            num /= prime;
        }
        return cnt;
    }

    public ArrayList<Integer> mul(@NotNull ArrayList<Integer> ints, int num) {   // 高精度乘法
        int tmp = 0;
        for (int i = 0; i < ints.size(); i++) {
            tmp += ints.get(i) * num;
            ints.set(i, tmp % 10);
            tmp /= 10;
        }
        while (tmp > 0) {
            ints.add(tmp % 10);
            tmp /= 10;
        }
        return ints;
    }

    public ArrayList<Integer> findIV(int a, int b) {
        getPrime(a);
        for (int i = 0; i < pointer; i++) {
            int p = primes[i];
            powers[i] = getPower(a, p) - getPower(b, p) - getPower(a - b, p);
        }
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(1);
        for (int i = 0; i < pointer; i++) {
            for (int j = 0; j < powers[i]; j++) {
                ans = mul(ans, primes[i]);
            }
        }
        return ans;
    }

    public long cattelan(int num) {
        FastPower fastPower = new FastPower();
        return Cab(2 * num, num, mod) * fastPower.fastPower(num + 1, mod - 2, mod) % mod;
    }
}
