package Algorithm.AlgorithmTemplate.MathematicalKnowledge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // 筛质数
        PrimeNumber primeNumber = new PrimeNumber();
        int n = Integer.parseInt(bufferedReader.readLine());
        System.out.println(primeNumber.ehrlichSieve(n));
        System.out.println(primeNumber.linearSieve(n));

        /*
        // 试除法判定质数、分解质因数
        PrimeNumber primeNumber = new PrimeNumber();
        int n = Integer.parseInt(bufferedReader.readLine());
        int x;
        for (int i = 0; i < n; i++) {
            x = Integer.parseInt(bufferedReader.readLine());
            // 分解质因数
            primeNumber.primeFactor(x);
            // 试除法判定质数
//            if (primeNumber.isPrime(x)) {
//                System.out.println("Yes");
//            } else {
//                System.out.println("No");
//            }
        }

         */
    }
}
