package Algorithm.AlgorithmTemplate.MathematicalKnowledge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        ExtendedEuclidean extendedEuclidean = new ExtendedEuclidean();
        String[] strings;
        for (int i = 0; i < n; i++) {
            strings = bufferedReader.readLine().split(" ");
            int a = Integer.parseInt(strings[0]), b = Integer.parseInt(strings[1]);
            extendedEuclidean.exgcd(a, b);
            System.out.printf("%d %d\n", extendedEuclidean.getX(), extendedEuclidean.getY());
        }

        /*
        // 快速幂、快速幂求逆元
        int n = Integer.parseInt(bufferedReader.readLine());
        FastPower fastPower = new FastPower();
        String[] strings;
        for (int i = 0; i < n; i++) {
            strings = bufferedReader.readLine().split(" ");
            // 快速幂
//            int a = Integer.parseInt(strings[0]), b = Integer.parseInt(strings[1]), p = Integer.parseInt(strings[2]);
//            System.out.println(fastPower.fastPower(a, b, p));
            // 快速幂求逆元
            int a = Integer.parseInt(strings[0]), p = Integer.parseInt(strings[1]);
            if (a % p != 0) {
                System.out.println(fastPower.fastPower(a, p - 2, p));
            } else {
                System.out.println("impossible");
            }
        }

         */


        /*
        // 欧拉函数、筛法求欧拉函数
        int n = Integer.parseInt(bufferedReader.readLine());
        EulerFunction eulerFunction = new EulerFunction();
        System.out.println(eulerFunction.sumEuler(n));   // 筛法求欧拉函数
        for (int i = 0; i < n; i++) {   // 欧拉函数
            int x = Integer.parseInt(bufferedReader.readLine());
            System.out.println(eulerFunction.eulerFunction(x));
        }

         */


        /*
        // 约数个数、约数之和、最大公约数
        int n = Integer.parseInt(bufferedReader.readLine());
        ApproximateNumber approximateNumber = new ApproximateNumber();
        int[] lst = new int[n];   // 约数个数、约数之和
        String[] strings;   // 最大公约数
        for (int i = 0; i < n; i++) {
            lst[i] = Integer.parseInt(bufferedReader.readLine());   // 约数个数、约数之和
            strings = bufferedReader.readLine().split(" ");   // 最大公约数
            int a = Integer.parseInt(strings[0]);   // 最大公约数
            int b = Integer.parseInt(strings[1]);   // 最大公约数
            System.out.println(approximateNumber.greatestCommonDivisor(a, b));   // 最大公约数
        }
        System.out.println(approximateNumber.approximateCount(lst));   // 约数个数
        System.out.println(approximateNumber.approximateSum(lst));   // 约数之和

         */


        /*
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

         */


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
