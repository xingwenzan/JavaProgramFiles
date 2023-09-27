package Algorithm.AdvancedGuide.BasicAlgorithms;

/**
 * a^b
 * <a href="https://www.acwing.com/problem/content/91/">...</a>
 */
public class PowerABC {
    private final long basic, index, mod;


    public PowerABC(long basic, long index, long mod) {
        this.basic = basic;
        this.index = index;
        this.mod = mod;
    }

    public long getAns() {
        long ans = 1 % mod;
        long a = basic, b = index;
        while (b > 0) {
            if ((b & 1) == 1) ans = ans * a % mod;
            a = a * a % mod;
            b >>= 1;
        }
        return ans;
    }
}
