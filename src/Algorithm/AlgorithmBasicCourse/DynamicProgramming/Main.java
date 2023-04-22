package Algorithm.AlgorithmBasicCourse.DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        String[] strings;
        LinearDP linearDP = new LinearDP();
        for (int i = 0; i < n; i++) {
            strings = bufferedReader.readLine().split(" ");
            for (int j = 0; j <= i; j++) {
                linearDP.addNT(Integer.parseInt(strings[j]));
            }
        }
        System.out.println(linearDP.numberTriangleDP(n));


        /*
        // 分组背包问题
        String[] strings = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int v = Integer.parseInt(strings[1]);
        KnapsackProblem knapsackProblem = new KnapsackProblem();
        int s;
        for (int i = 0; i < n; i++) {
            s = Integer.parseInt(bufferedReader.readLine());
            knapsackProblem.addGroup(s);
            for (int j = 0; j < s; j++) {
                strings = bufferedReader.readLine().split(" ");
                knapsackProblem.addObject(s, Integer.parseInt(strings[0]), Integer.parseInt(strings[1]));
            }
        }
        System.out.println(knapsackProblem.naiveGroup(n, v));
        System.out.println(knapsackProblem.optimizationGroup(n, v));

         */


        /*
        // 01背包问题、完全背包问题、多重背包问题 I、多重背包问题 II
        String[] strings = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int v = Integer.parseInt(strings[1]);
        KnapsackProblem knapsackProblem = new KnapsackProblem();
        for (int i = 0; i < n; i++) {
            strings = bufferedReader.readLine().split(" ");
            // 01、完全
            knapsackProblem.add(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]));
            // 多重 I
            knapsackProblem.addsI(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));
            // 多重 II
            knapsackProblem.addsII(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));
        }
        System.out.println(knapsackProblem.naive01(n, v));
        System.out.println(knapsackProblem.optimization01(n, v));
        System.out.println(knapsackProblem.naiveCompletely(n, v));
        System.out.println(knapsackProblem.optimizationCompletely(n, v));
        System.out.println(knapsackProblem.naiveMultipleI(n, v));
        System.out.println(knapsackProblem.optimizationMultipleI(n,v));
        System.out.println(knapsackProblem.MultipleII(v));

         */
    }
}
