package Algorithm.AlgorithmTemplate.MathematicalKnowledge;

public class EulerFunction {
    // 欧拉函数 https://www.acwing.com/problem/content/875/
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
}
