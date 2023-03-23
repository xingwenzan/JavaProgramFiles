package Algorithm.AlgorithmTemplate.MathematicalKnowledge;

import java.util.Arrays;

public class PrimeNumber {
    // 试除法判定质数 https://www.acwing.com/problem/content/868/
    // 分解质因数 https://www.acwing.com/problem/content/869/
    // https://zhuanlan.zhihu.com/p/351002242
    // 筛质数 https://www.acwing.com/problem/content/870/

    int N = (int) 1e6 + 10;
    int[] prime = new int[N];
    boolean[] state = new boolean[N];
    int num;

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

    public int ehrlichSieve(int x) {
        Arrays.fill(state, false);
        num = 0;
        for (int i = 2; i <= x; i++) {
            if (!state[i]) {
                num++;
                for (int j = i + i; j <= x; j += i) {
                    state[j] = true;
                }
            }
        }
        return num;
    }


    public int linearSieve(int x) {
        Arrays.fill(prime, 0);
        Arrays.fill(state, false);
        num = 0;
        for (int i = 2; i <= x; i++) {
            if (!state[i]) {
                prime[num++] = i;
            }
            for (int j = 0; prime[j] <= x / i; j++) {
                state[i * prime[j]] = true;
                if (i % prime[j] == 0) {
                    break;
                }
            }
        }
        return num;
    }
}
