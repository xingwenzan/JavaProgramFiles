package Algorithm.Basic.DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // 滑雪
        String[] strings = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(strings[0]), m = Integer.parseInt(strings[1]);
        MemorySearch memorySearch = new MemorySearch();
        memorySearch.init(n, m);
        for (int i = 0; i < n; i++) {
            strings = bufferedReader.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                memorySearch.add(i, j, Integer.parseInt(strings[j]));
            }
        }
        System.out.println(memorySearch.skiing());


        /*
        // 没有上司的舞会
        int n = Integer.parseInt(bufferedReader.readLine());
        TreeDP treeDP = new TreeDP();
        treeDP.init();
        for (int i = 1; i <= n; i++) {
            int x = Integer.parseInt(bufferedReader.readLine());
            treeDP.setHappy(x, i);
        }
        String[] strings;
        for (int i = 0; i < n - 1; i++) {
            strings = bufferedReader.readLine().split(" ");
            int son = Integer.parseInt(strings[0]);
            int father = Integer.parseInt(strings[1]);
            treeDP.add(father, son);
        }
        System.out.println(treeDP.promWithoutBoss());

         */


        /*
        // 最短Hamilton路径
        int n = Integer.parseInt(bufferedReader.readLine());
        String[][] w = new String[n][n];
        for (int i = 0; i < n; i++) {
            w[i] = bufferedReader.readLine().split(" ");
        }
        StateCompressedDP stateCompressedDP = new StateCompressedDP();
        System.out.println(stateCompressedDP.ShortestHamiltonPath(n, w));

         */


        /*
        // 蒙德里安的梦想
        String[] strings;
        StateCompressedDP stateCompressedDP = new StateCompressedDP();
        while (true) {
            strings = bufferedReader.readLine().split(" ");
            int n = Integer.parseInt(strings[0]), m = Integer.parseInt(strings[1]);
            if (n == 0 && m == 0) {
                break;
            }
            System.out.println(stateCompressedDP.MondrianDream(n, m));
        }

         */


        /*
        // 整数划分
        int n = Integer.parseInt(bufferedReader.readLine());
        CountingDP countingDP = new CountingDP();
        System.out.println(countingDP.bag(n));
        System.out.println(countingDP.other(n));

         */


        /*
        // 石子合并
        int n = Integer.parseInt(bufferedReader.readLine());
        String[] strings = bufferedReader.readLine().split(" ");
        IntervalDP intervalDP = new IntervalDP();
        System.out.println(intervalDP.stonesMerge(strings, n));

         */


        /*
        // 编辑距离
        String[] raedString = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(raedString[0]);
        int m = Integer.parseInt(raedString[1]);
        String[] strings = new String[n];
        LinearDP linearDP = new LinearDP();
        for (int i = 0; i < n; i++) {
            strings[i] = bufferedReader.readLine();
        }
        for (int i = 0; i < m; i++) {
            raedString = bufferedReader.readLine().split(" ");
            System.out.println(linearDP.EditDistance(raedString[0], Integer.parseInt(raedString[1]), strings, n));
        }

         */


        /*
        // 最短编辑距离
        bufferedReader.readLine();
        String A = bufferedReader.readLine();
        bufferedReader.readLine();
        String B = bufferedReader.readLine();
        LinearDP linearDP = new LinearDP();
        System.out.println(linearDP.ShortestEditDistance(A, B));

         */


        /*
        // 最长公共子序列
        bufferedReader.readLine();
        String A = bufferedReader.readLine(), B = bufferedReader.readLine();
        LinearDP linearDP = new LinearDP();
        System.out.println(linearDP.LongestCommonSubsequence(A, B));

         */


        /*
        // 最长上升子序列; 最长上升子序列 II
        int n = Integer.parseInt(bufferedReader.readLine());
        String[] strings = bufferedReader.readLine().split(" ");
        LinearDP linearDP = new LinearDP();
        for (int i = 0; i < n; i++) {
            linearDP.addLAS(Integer.parseInt(strings[i]));
        }
        System.out.println(linearDP.longestAscendingSubsequenceDP());   // 最长上升子序列
        System.out.println(linearDP.longestAscendingSubsequenceII());   // 最长上升子序列 II

         */


        /*
        // 数字三角形
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

         */


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
