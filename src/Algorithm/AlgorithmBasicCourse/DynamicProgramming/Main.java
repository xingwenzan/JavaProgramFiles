package Algorithm.AlgorithmBasicCourse.DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // 01背包问题
        String[] strings = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int v = Integer.parseInt(strings[1]);
        KnapsackProblem knapsackProblem = new KnapsackProblem();
        for (int i = 0; i < n; i++) {
            strings = bufferedReader.readLine().split(" ");
            knapsackProblem.add(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]));
        }
        System.out.println(knapsackProblem.naive01(n, v));
        System.out.println(knapsackProblem.optimization01(n, v));
        System.out.println(knapsackProblem.naiveCompletely(n, v));
        System.out.println(knapsackProblem.optimizationCompletely(n, v));
    }
}
