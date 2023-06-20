package Algorithm.Improve.DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // 货币系统-NOPI
        int t = Integer.parseInt(bufferedReader.readLine());
        BackpackModel bp = new BackpackModel();
        for (int i = 0; i < t; i++) {
            bufferedReader.readLine();
//            int n = Integer.parseInt(bufferedReader.readLine());
            String[] strings = bufferedReader.readLine().split(" ");
            System.out.println(bp.MonetarySystemNOIP(strings));
        }



        /*
        // 货币系统
        String[] strings = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(strings[0]), m = Integer.parseInt(strings[1]);
        BackpackModel bp = new BackpackModel();
        for (int i = 0; i < n; i++) {
            bp.add(Integer.parseInt(bufferedReader.readLine()));
        }
        System.out.println(bp.MonetarySystem(m));

         */


        /*
        // 买书
        int n = Integer.parseInt(bufferedReader.readLine());
        BackpackModel bp = new BackpackModel();
        System.out.println(bp.BuyBooks(n));

         */


        /*
        // 数字组合
        String[] strings = bufferedReader.readLine().split(" ");
        int M = Integer.parseInt(strings[1]);
        strings = bufferedReader.readLine().split(" ");
        BackpackModel bp = new BackpackModel();
        System.out.println(bp.NumberCombinations(strings, M));

         */


        /*
        // 宠物小精灵之收服
        String[] strings = bufferedReader.readLine().split(" ");
        BackpackModel bp = new BackpackModel();
        int K = Integer.parseInt(strings[2]), M = Integer.parseInt(strings[1]), N = Integer.parseInt(strings[0]);
        for (int i = 0; i < K; i++) {
            strings = bufferedReader.readLine().split(" ");
            bp.add(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), 1);
        }
        int[] ans = bp.PokemonConquer(N, M);
        System.out.printf("%d %d", ans[0], ans[1]);

         */



        /*
        // 装箱问题
        int V = Integer.parseInt(bufferedReader.readLine());
        int n = Integer.parseInt(bufferedReader.readLine());
        BackpackModel bp = new BackpackModel();
        for (int i = 0; i < n; i++) {
            int tmp = Integer.parseInt(bufferedReader.readLine());
            bp.add(tmp, tmp);
        }
        System.out.println(bp.PackingProblem(V));

         */


        /*
        // 采药
        String[] strings = bufferedReader.readLine().split(" ");
        int t = Integer.parseInt(strings[0]), m = Integer.parseInt(strings[1]);
        BackpackModel bp = new BackpackModel();
        for (int i = 0; i < m; i++) {
            strings = bufferedReader.readLine().split(" ");
            bp.add(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]));
        }
        System.out.println(bp.CollectHerbs(t));

         */


        /*
        // 最长公共上升子序列
        LongestAscendingSubsequence LIS = new LongestAscendingSubsequence();
        int n = Integer.parseInt(bufferedReader.readLine());
        String[] strA = bufferedReader.readLine().split(" ");
        String[] strB = bufferedReader.readLine().split(" ");
        System.out.println(LIS.Common(strA, strB, n));

         */


        /*
        // 导弹防御系统
        LongestAscendingSubsequence LIS = new LongestAscendingSubsequence();
        int n = Integer.parseInt(bufferedReader.readLine());
        while (n > 0) {
            String[] strings = bufferedReader.readLine().split(" ");
            System.out.println(LIS.MissileDefenseSystem(strings, n));
            n = Integer.parseInt(bufferedReader.readLine());
        }

         */


        /*
        // 拦截导弹
        String[] strings = bufferedReader.readLine().split(" ");
        LongestAscendingSubsequence LIS = new LongestAscendingSubsequence();
        int[] ans = LIS.InterceptorMissile(strings);
        System.out.printf("%d\n%d", ans[0], ans[1]);

         */


        /*
        // 友好城市
        int n = Integer.parseInt(bufferedReader.readLine());
        LongestAscendingSubsequence LIS = new LongestAscendingSubsequence();
        for (int i = 0; i < n; i++) {
            String[] strings = bufferedReader.readLine().split(" ");
            LIS.addList(strings);
        }
        System.out.println(LIS.SisterCity(n));

         */


        /*
        // 登山、合唱队形、最大上升子序列和
        int n = Integer.parseInt(bufferedReader.readLine());
        String[] strings = bufferedReader.readLine().split(" ");
        LongestAscendingSubsequence LIS = new LongestAscendingSubsequence();
        System.out.println(LIS.Mountaineering(strings, n));   // 登山
        System.out.println(LIS.ChorusFormation(strings, n));   // 合唱队形
        System.out.println(LIS.ToSum(strings, n));   // 最大上升子序列和

         */


        /*
        // 怪盗基德的滑翔翼
        int k = Integer.parseInt(bufferedReader.readLine());
        LongestAscendingSubsequence LIS = new LongestAscendingSubsequence();
        for (int i = 0; i < k; i++) {
            int n = Integer.parseInt(bufferedReader.readLine());
            String[] strings = bufferedReader.readLine().split(" ");
            System.out.println(LIS.HangGliding(strings, n));
        }

         */


        /*
        // 方格取数
        int n = Integer.parseInt(bufferedReader.readLine());
        String[] strings;
        DigitalTriangleModel digitalTriangleModel = new DigitalTriangleModel();
        while (true) {
            strings = bufferedReader.readLine().split(" ");
            int r = Integer.parseInt(strings[0]), c = Integer.parseInt(strings[1]), x = Integer.parseInt(strings[2]);
            if (r == 0 && c == 0 && x == 0) {
                break;
            }
            digitalTriangleModel.add(r, c, x);
        }
        System.out.println(digitalTriangleModel.squareAccess(n));

         */


        /*
        // 最低通行费
        int n = Integer.parseInt(bufferedReader.readLine());
        String[] strings;
        DigitalTriangleModel digitalTriangleModel = new DigitalTriangleModel();
        for (int r = 0; r < n; r++) {
            strings = bufferedReader.readLine().split(" ");
            for (int c = 0; c < n; c++) {
                digitalTriangleModel.add(r, c, Integer.parseInt(strings[c]));
            }
        }
        System.out.println(digitalTriangleModel.minimumToll(n));

         */


        /*
        // 摘花生
        int n = Integer.parseInt(bufferedReader.readLine());
        String[] strings;
        DigitalTriangleModel digitalTriangleModel = new DigitalTriangleModel();
        for (int o = 0; o < n; o++) {
            strings = bufferedReader.readLine().split(" ");
            int R = Integer.parseInt(strings[0]), C = Integer.parseInt(strings[1]);
            for (int r = 0; r < R; r++) {
                strings = bufferedReader.readLine().split(" ");
                for (int c = 0; c < C; c++) {
                    digitalTriangleModel.add(r, c, Integer.parseInt(strings[c]));
                }
            }
            System.out.println(digitalTriangleModel.pickingPeanuts(R, C));
        }

         */
    }
}
