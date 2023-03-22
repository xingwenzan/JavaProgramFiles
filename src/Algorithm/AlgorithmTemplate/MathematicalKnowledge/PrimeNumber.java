package Algorithm.AlgorithmTemplate.MathematicalKnowledge;

public class PrimeNumber {
    // 试除法判定质数 https://www.acwing.com/problem/content/868/
    // 分解质因数 https://www.acwing.com/problem/content/869/
    // https://zhuanlan.zhihu.com/p/351002242
    public boolean isPrime(int x) {
        if (x < 2) {
            return false;
        }
        for (int i = 2; i <= x / i; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    public void primeFactor(int x) {
        for (int i = 2; i <= x / i; i++) {
            if (x % i == 0) {
                int num = 0;
                while (x % i == 0) {
                    num++;
                    x /= i;
                }
                System.out.printf("%d %d\n", i, num);
            }
        }
        if (x > 1) {
            System.out.printf("%d 1\n", x);
        }
        System.out.println();
    }
}
