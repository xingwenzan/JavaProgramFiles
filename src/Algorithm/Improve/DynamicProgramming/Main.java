package Algorithm.Improve.DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // 最长公共上升子序列
        LongestAscendingSubsequence LIS = new LongestAscendingSubsequence();
        int n = Integer.parseInt(bufferedReader.readLine());
        String[] strA = bufferedReader.readLine().split(" ");
        String[] strB = bufferedReader.readLine().split(" ");
        System.out.println(LIS.Common(strA, strB, n));


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
