package Algorithm.AlgorithmTemplate.MathematicalKnowledge;

public class PrimeNumber {
    // 试除法判定质数 https://www.acwing.com/problem/content/868/
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
}
