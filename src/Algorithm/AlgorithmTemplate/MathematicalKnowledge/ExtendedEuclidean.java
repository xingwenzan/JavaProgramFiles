package Algorithm.AlgorithmTemplate.MathematicalKnowledge;

public class ExtendedEuclidean {
    // 扩展欧几里得算法 https://www.acwing.com/problem/content/879/

    /*
     扩展辗转相除法、求最大公约数算法扩展
     https://blog.csdn.net/destiny1507/article/details/81750874
     */

    private int x, y;

    public int exgcd(int a, int b) {   // a*x+b*y = gcd
        if (b == 0) {
            x = 1;
            y = 0;   // 此时 y 可以是任何数
            return a;   // 最大公约数，本题里可有可无
        }
        int gcd = exgcd(b, a % b);
        int tmp = x;
        x = y;
        y = tmp - a / b * x;
        return gcd;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
