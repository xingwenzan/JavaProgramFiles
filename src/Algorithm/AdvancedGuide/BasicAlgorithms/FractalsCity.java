package Algorithm.AdvancedGuide.BasicAlgorithms;

/**
 * 分形之城
 * <a href="https://www.acwing.com/problem/content/100/">...</a>
 */
public class FractalsCity {
    private final long N, A, B;

    public FractalsCity(long N, long A, long B) {
        this.N = N;
        //  索引从 0 开始，但题目从 1 开始，所以减一
        this.A = A - 1;
        this.B = B - 1;
    }

    public long getAns() {
        long[] axy = xy(N, A), bxy = xy(N, B);
        long x = Math.abs(axy[0] - bxy[0]), y = Math.abs(axy[1] - bxy[1]);
        return Math.round(Math.sqrt(x * x + y * y) * 10);
    }

    private long[] xy(long n, long m) {
        if (n == 0) return new long[]{0L, 0L};
        long cnt = 1L << (2 * (n - 1)), side = 1L << (n - 1);
        long z = m / cnt;
        long[] xAndy = xy(n - 1, m % cnt);
        long x = xAndy[0], y = xAndy[1];
        if (z == 0) return new long[]{y, x};
        if (z == 1) return new long[]{x, y + side};
        if (z == 2) return new long[]{x + side, y + side};
        return new long[]{2 * side - y - 1, side - x - 1};
    }
}
