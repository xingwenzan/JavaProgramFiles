package Algorithm.Improve.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // 八数码
        String s = String.join("", bufferedReader.readLine().split(" "));
        EightDigits ed = new EightDigits(s);
        System.out.println(ed.getAns());


        /*
        // 双端队列广搜
        int n = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            String[] strings = bufferedReader.readLine().split(" ");
            int r = Integer.parseInt(strings[0]), c = Integer.parseInt(strings[1]);
            String[] lst = new String[r];
            for (int j = 0; j < r; j++) {
                lst[j] = bufferedReader.readLine();
            }
            DoubleEndedQueueSearch DEQS = new DoubleEndedQueueSearch(r, c, lst);
            int ans = DEQS.getAns();
            if (ans == -1) System.out.println("NO SOLUTION");
            else System.out.println(ans);
        }

         */


        /*
        // 矩阵距离
        String[] strings = bufferedReader.readLine().split(" ");
        int r = Integer.parseInt(strings[0]), c = Integer.parseInt(strings[1]);
        String[] lst = new String[r];
        for (int i = 0; i < r; i++) {
            lst[i] = bufferedReader.readLine();
        }
        MultiSourceBFS ms = new MultiSourceBFS(r, c, lst);
        int[][] ans = ms.getD();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.printf("%d ", ans[i][j]);
            }
            System.out.println();
        }

         */


        /*
        // 抓住那头牛
        String[] strings = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(strings[0]), k = Integer.parseInt(strings[1]);
        CatchThatCow ctc = new CatchThatCow(n, k);
        System.out.println(ctc.getAns());

         */


        /*
        // 武士风度的牛
        String[] strings = bufferedReader.readLine().split(" ");
        int c = Integer.parseInt(strings[0]), r = Integer.parseInt(strings[1]);
        String[] lst = new String[r];
        for (int i = 0; i < r; i++) {
            lst[i] = bufferedReader.readLine();
        }
        SamuraiStyleCow ssc = new SamuraiStyleCow(r, c, lst);
        System.out.println(ssc.bfs());

         */


        /*
        // 迷宫问题
        int n = Integer.parseInt(bufferedReader.readLine());
        int[][] lst = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] strings = bufferedReader.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                lst[i][j] = Integer.parseInt(strings[j]);
            }
        }
        MazeProblem mp = new MazeProblem(n, lst);
        mp.output();

         */


        /*
        // 山峰和山谷
        int n = Integer.parseInt(bufferedReader.readLine());
        int[][] lst = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] strings = bufferedReader.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                lst[i][j] = Integer.parseInt(strings[j]);
            }
        }
        PeaksAndValleys pv = new PeaksAndValleys(n, lst);
        System.out.printf("%d %d", pv.getPeaks(), pv.getValleys());

         */


        /*
        // 城堡问题
        String[] strings = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(strings[0]), m = Integer.parseInt(strings[1]);
        int[][] room = new int[n][m];
        for (int i = 0; i < n; i++) {
            strings = bufferedReader.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                room[i][j] = Integer.parseInt(strings[j]);
            }
        }
        CastleProblem cp = new CastleProblem(n, m, room);
        System.out.println(cp.getCnt());
        System.out.println(cp.getArea());

         */


        /*
        // 池塘计数
        String[] strings = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(strings[0]), m = Integer.parseInt(strings[1]);
        strings = new String[n];
        for (int i = 0; i < n; i++) {
            strings[i] = bufferedReader.readLine();
        }
        PondCount PC = new PondCount(strings, n, m);
        System.out.println(PC.getAns());

         */
    }
}
