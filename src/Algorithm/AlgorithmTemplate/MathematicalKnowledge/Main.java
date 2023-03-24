package Algorithm.AlgorithmTemplate.MathematicalKnowledge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringJoiner;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // 筛质数、试除法求约数
//        PrimeNumber primeNumber = new PrimeNumber();   // 筛质数
        ApproximateNumber approximateNumber = new ApproximateNumber();   // 试除法求约数
        int n = Integer.parseInt(bufferedReader.readLine());
//        System.out.println(primeNumber.ehrlichSieve(n));   // 筛质数
//        System.out.println(primeNumber.linearSieve(n));   // 筛质数
        List<Integer> outList;   // 试除法求约数 - 列表模式
        PriorityQueue<Integer> outHeap;   // 试除法求约数 - 堆模式
        int x;   // 试除法求约数
        for (int i = 0; i < n; i++) {   // 试除法求约数
            x = Integer.parseInt(bufferedReader.readLine());
            outList = approximateNumber.approximationList(x);   // 试除法求约数 - 列表模式
            outHeap = approximateNumber.approximationHeap(x);   // 试除法求约数 - 堆模式
            // 试除法求约数 - 堆模式 - 1242ms
            while (!outHeap.isEmpty()) {
                System.out.printf("%d ", outHeap.poll());
            }
            System.out.println();
            // 试除法求约数 - 列表模式 - 输出1 - 501ms
            StringJoiner joiner = new StringJoiner(" ");
            outList.forEach(item -> joiner.add(String.valueOf(item)));
            System.out.println(joiner);
            // 试除法求约数 - 列表模式 - 输出2 - 1315ms
            for (int j = 0; j < outList.size(); j++) {
                System.out.printf("%d ", outList.get(j));
            }
            System.out.println();
        }

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
