package Algorithm.AdvancedGuide.BasicAlgorithms;

/**
 * 约数之和
 * <a href="https://www.acwing.com/problem/content/99/">...</a>
 * 详解
 * <a href="https://www.acwing.com/video/116/">...</a>
 */
public class DivisorsSum {
    private final int A, B;
    private final int mod = 9901;
    private int ans = 1;

    public DivisorsSum(int A, int B) {
        this.A = A;
        this.B = B;

        if (A == 0) ans = 0;
        else if (B != 0) count();
    }

    private void count() {
        // 分解质因数分别计算
        int x = A, y = B;
        for (int i = 2; i <= x / i; i++) {
            int num = 0;
            while (x % i == 0) {
                x /= i;
                num++;
            }
            if (num != 0) ans = ans * work(i, num * y) % mod;
        }
        if (x > 1) ans = ans * work(x, y) % mod;
    }

    private int work(int p, int k) {
        if (k == 0) return 1;
        if (k % 2 == 0) return (p % mod * work(p, k - 1) % mod + 1) % mod;
        return (1 + qmi(p, k / 2 + 1)) % mod * work(p, k / 2) % mod;
    }

    private int qmi(int p, int k) {
        p %= mod;
        int res = 1;
        while (k != 0) {
            if ((k & 1) == 1) res = res * p % mod;
            p = p * p % mod;
            k >>= 1;
        }
        return res;
    }

    public int getAns() {
        return ans;
    }
}
