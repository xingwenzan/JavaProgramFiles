package Algorithm.AlgorithmBasicCourse.MathematicalKnowledge;

public class ChineseRemainderTheorem {
    // 表达整数的奇怪方式 https://www.acwing.com/problem/content/206/

    /*
     https://www.acwing.com/solution/content/3539/
     https://www.acwing.com/activity/content/code/content/5425217/
     正确答案，但没学会，待定
     */

    private long x, y, a, m;

    public long exgcd(long i, long j) {   // i*x+j*y = gcd
        if (j == 0) {
            x = 1;
            y = 0;   // 此时 y 可以是任何数
            return i;   // 最大公约数，本题里可有可无
        }
        long gcd = exgcd(j, i % j);
        long tmp = x;
        x = y;
        y = tmp - i / j * x;
        return gcd;
    }

    public void formulaMerge(long ai, long mi) {
        long gcd = exgcd(a, ai);
        if ((mi - m) % gcd != 0) {
            m = -1;
            return;
        }
        x = x * ((mi - m) / gcd);
        x = (x % (ai / gcd) + (ai / gcd)) % (ai / gcd);
        m = x * a + m;
        a = a / gcd * ai;
    }

    public long getA() {
        return a;
    }

    public void setA(long a) {
        this.a = a;
    }

    public long getM() {
        return m;
    }

    public void setM(long m) {
        this.m = m;
    }
}
