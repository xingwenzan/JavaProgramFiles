package Algorithm.AlgorithmTemplate.MathematicalKnowledge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // 容斥原理 能被整除的数
        String[] strings = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(strings[0]), m = Integer.parseInt(strings[1]);
        strings = bufferedReader.readLine().split(" ");
        int[] ints = new int[m];
        for (int i = 0; i < m; i++) {
            ints[i] = Integer.parseInt(strings[i]);
        }
        InclusionExclusionPrinciple iep = new InclusionExclusionPrinciple();
        System.out.println(iep.divisibleNumber(n, m, ints));


        /*
        // 求组合数 IV、满足条件的01序列
        CombinatorialNumbers cn = new CombinatorialNumbers();
        // 满足条件的01序列
        int n = Integer.parseInt(bufferedReader.readLine());
        System.out.println(cn.cattelan(n));
        // 求组合数 IV
        String[] strings = bufferedReader.readLine().split(" ");
        int a = Integer.parseInt(strings[0]), b = Integer.parseInt(strings[1]);
        ArrayList<Integer> ans = cn.findIV(a, b);
        for (int i = ans.size() - 1; i >= 0; i--) {
            System.out.printf("%d", ans.get(i));
        }

         */


        /*
        // 求组合数 I、求组合数 II、求组合数 III
        int n = Integer.parseInt(bufferedReader.readLine());
        String[] strings;
        CombinatorialNumbers combinatorialNumbers = new CombinatorialNumbers();
//        combinatorialNumbers.initI();   // 求组合数 I
//        combinatorialNumbers.initII();   // 求组合数 II
        for (int i = 0; i < n; i++) {
            strings = bufferedReader.readLine().split(" ");
//            int a = Integer.parseInt(strings[0]), b = Integer.parseInt(strings[1]);   // 求组合数 I、求组合数 II
            long a = Long.parseLong(strings[0]), b = Long.parseLong(strings[1]);
            int p = Integer.parseInt(strings[2]);
//            System.out.println(combinatorialNumbers.findI(a, b));   // 求组合数 I
//            System.out.println(combinatorialNumbers.findII(a, b));   // 求组合数 II
            System.out.println(combinatorialNumbers.lucas(a, b, p));
        }

         */


        /*
        // 高斯消元解线性方程组、高斯消元解异或线性方程组
        int n = Integer.parseInt(bufferedReader.readLine());
        String[] strings;
        GaussianElimination gaussianElimination = new GaussianElimination();
        for (int i = 0; i < n; i++) {
            strings = bufferedReader.readLine().split(" ");
            for (int j = 0; j <= n; j++) {
//                gaussianElimination.init(i, j, Double.parseDouble(strings[j]));   // 高斯消元解线性方程组
                gaussianElimination.init(i, j, Integer.parseInt(strings[j]));   // 高斯消元解异或线性方程组
            }
        }
//        int judge = gaussianElimination.SolvingSystemsOfLinearEquations(n);
        int judge = gaussianElimination.SolvingXORLinearEquations(n);
        if (judge == 0) {
            for (int i : gaussianElimination.solutionXOR()) {
                System.out.printf("%d\n", i);
            }
        } else if (judge == 1) {
            System.out.println("Multiple sets of solutions");
        } else if (judge == 2) {
            System.out.println("No solution");
        }

         */


        /*
        // 表达整数的奇怪方式
        int n = Integer.parseInt(bufferedReader.readLine());
        ChineseRemainderTheorem crt = new ChineseRemainderTheorem();
        String[] strings = bufferedReader.readLine().split(" ");
        long a1 = Integer.parseInt(strings[0]), m1 = Integer.parseInt(strings[1]), a2, m2;
        crt.setA(a1);
        crt.setM(m1);
//        for (int i = 0; i < n - 1; i++) {
//            strings = bufferedReader.readLine().split(" ");
//            long a2 = Integer.parseInt(strings[0]), m2 = Integer.parseInt(strings[1]);
//            crt.formulaMerge(a2, m2);
//            a1 = crt.getA();
//            m1 = crt.getM();
//            if (m1 == -1) {
//                break;
//            }
//        }
        while (m1 != -1 && --n > 0) {
            strings = bufferedReader.readLine().split(" ");
            a2 = Integer.parseInt(strings[0]);
            m2 = Integer.parseInt(strings[1]);
            crt.formulaMerge(a2, m2);
            a1 = crt.getA();
            m1 = crt.getM();
        }
        if (m1 == -1) {
            System.out.println(-1);
        } else {
            System.out.println((m1 % a1 + a1) % a1);
        }

         */


        /*
        // 扩展欧几里得算法、线性同余方程
        int n = Integer.parseInt(bufferedReader.readLine());
        ExtendedEuclidean extendedEuclidean = new ExtendedEuclidean();   // 扩展欧几里得算法、线性同余方程
        String[] strings;
        for (int i = 0; i < n; i++) {   // 扩展欧几里得算法
            strings = bufferedReader.readLine().split(" ");
            int a = Integer.parseInt(strings[0]), b = Integer.parseInt(strings[1]);
            extendedEuclidean.exgcd(a, b);
            System.out.printf("%d %d\n", extendedEuclidean.getX(), extendedEuclidean.getY());
        }
        for (int i = 0; i < n; i++) {   // 线性同余方程
            strings = bufferedReader.readLine().split(" ");
            int a = Integer.parseInt(strings[0]), b = Integer.parseInt(strings[1]), m = Integer.parseInt(strings[2]), gcd = extendedEuclidean.exgcd(a, m);
            if (b % gcd != 0) {
                System.out.println("impossible");
            } else {
                System.out.println((long) b / gcd * extendedEuclidean.getX() % m);
            }
        }

         */


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
