package Algorithm.Basic.MathematicalKnowledge;

public class FastPower {
    // 快速幂 https://www.acwing.com/problem/content/877/
    // 快速幂求逆元 https://www.acwing.com/problem/content/878/

    /*
     a^b mod p
     所谓快速幂的快速在于将指数b分解成了二进制数表示，b= xk ... x1 x0，而代码实现的核心则是反复平方
     a^b = a^(2^xk+...2^x1+2^x0) = a^(2^xk) * ... * a^(2^x1) * a^(2^x0)
     这样就把a^b分解成logb个数相乘了，其中相邻两项中后一项是前一项的平方
     */

    /*
     逆元公式推导：a/b ≡ a*x (mod m)；等式两边同时乘 b，得：b*a/b ≡ a*b*x (mod m)，化简得：a ≡ a*b*x (mod m)
     所以 b*x ≡ 1 (mod m)，由费马小定理，b^(m-1) ≡ 1 (mod m) 其中 m 为质数。联系本方程 b * b^(m-2) ≡ 1，所以 b 的逆元 x 为 b^(m-2)；
     若 b 是 m 的倍数，则无解的，因为 b 是 m 的倍数，那么 b*x 也必定是 m 的倍数，模 m 的余数为 0，必定不为 1，是无解的情况。
     若 b 不是 m 的倍数，由于 m 是质数，那么 b 与 m 是互质的，由费马小定理可知，b^(m-1) ≡ 1 (mod m)，一定存在逆元，一定有解。
     */

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
