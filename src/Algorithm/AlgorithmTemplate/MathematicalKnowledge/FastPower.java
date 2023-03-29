package Algorithm.AlgorithmTemplate.MathematicalKnowledge;

public class FastPower {
    // 快速幂 https://www.acwing.com/problem/content/877/

    public long fastPower(long base, long index, long mod) {
        long ans = 1 % mod;
        while (index != 0) {
            if ((index & 1) == 1) {
                ans = ans * base % mod;
            }
            base = base * base % mod;
            index >>= 1;
        }
        return ans;
    }
}
