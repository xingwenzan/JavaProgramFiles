package Algorithm.AlgorithmTemplate.MathematicalKnowledge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // 试除法判定质数
        PrimeNumber primeNumber = new PrimeNumber();
        int n = Integer.parseInt(bufferedReader.readLine());
        int x;
        for (int i = 0; i < n; i++) {
            x = Integer.parseInt(bufferedReader.readLine());
            if (primeNumber.isPrime(x)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }
}
