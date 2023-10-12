package Algorithm.AdvancedGuide.BasicAlgorithms;

/**
 * 64位整数乘法
 * <a href="https://www.acwing.com/problem/content/92/">...</a>
 */
public class BitOF64IntegerMultiplication {
    private long a, b, mod;

    public BitOF64IntegerMultiplication(long a, long b, long mod) {
        this.a = a;
        this.b = b;
        this.mod = mod;
    }

    public long getAns() {
        long ans = 0;
        while (b != 0) {
            if ((b & 1) == 1) ans = (ans + a) % mod;
            a = (a + a) % mod;
            b >>= 1;
        }
        return ans;
    }
}
