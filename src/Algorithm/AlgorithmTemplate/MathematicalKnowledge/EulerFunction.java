package Algorithm.AlgorithmTemplate.MathematicalKnowledge;

import java.util.Arrays;

public class EulerFunction {
    // 欧拉函数 https://www.acwing.com/problem/content/875/
    // 筛法求欧拉函数 https://www.acwing.com/problem/content/876/

    int N = (int) 1e6 + 10;

    public int eulerFunction(int x) {
        int ans = x;
        for (int i = 2; i <= x / i; i++) {
            if (x % i == 0) {
                ans = ans / i * (i - 1);
                while (x % i == 0) {
                    x /= i;
                }
            }
        }
        if (x > 1) {
            ans = ans / x * (x - 1);
        }
        return ans;
    }


    public long sumEuler(int x) {
        int[] prime = new int[N], euler = new int[N];
        boolean[] state = new boolean[N];
        int primePointer = 0;
        euler[1] = 1;
        Arrays.fill(state, false);
        for (int i = 2; i <= x; i++) {
            if (!state[i]) {
                prime[primePointer++] = i;
                euler[i] = i - 1;
            }
            for (int j = 0; prime[j] <= x / i; j++) {
                int tmp = prime[j] * i;
                state[tmp] = true;
                if (i % prime[j] == 0) {
                    euler[tmp] = prime[j] * euler[i];
                    break;
                }
                euler[tmp] = euler[i] * (prime[j] - 1);
            }
        }
        long ans = 0;
        for (int i = 1; i <= x; i++) {
            ans += euler[i];
        }
        return ans;
    }
}
