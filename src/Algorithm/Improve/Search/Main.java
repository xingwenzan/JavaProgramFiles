package Algorithm.Improve.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // 木棒
        while (true) {
            int n = Integer.parseInt(bufferedReader.readLine());
            if (n == 0) break;
            String[] s = bufferedReader.readLine().split(" ");
            int[] w = new int[n];
            for (int i = 0; i < n; i++) w[i] = Integer.parseInt(s[i]);

            WoodenStick ws = new WoodenStick(n, w);
            System.out.println(ws.getL_one());
        }

        /*
        // 数独
        Sudoku sudoku = new Sudoku();
        while (true) {
            String s = bufferedReader.readLine();
            if (s.equals("end")) break;
            System.out.println(sudoku.getAns(s));
        }

         */

        /*
        // 小猫爬山
        String[] s = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(s[0]), w = Integer.parseInt(s[1]);
        int[] c = new int[n];
        for (int i = 0; i < n; i++) {
            c[i] = Integer.parseInt(bufferedReader.readLine().split(" ")[0]);
        }
        KittenClimbingMountain kcm = new KittenClimbingMountain(n, w, c);
        System.out.println(kcm.getAns());

         */

        /*
        // 单词接龙
        int n = Integer.parseInt(bufferedReader.readLine());
        String[] s = new String[n];
        for (int i = 0; i < n; i++) {
            s[i] = bufferedReader.readLine();
        }
        char st = bufferedReader.readLine().charAt(0);
        WordSolitaire ws = new WordSolitaire(n, st, s);
        System.out.println(ws.getAns());

         */

        /*
        // 马走日
        int t = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < t; i++) {
            String[] s = bufferedReader.readLine().split(" ");
            int n = Integer.parseInt(s[0]), m = Integer.parseInt(s[1]), x = Integer.parseInt(s[2]), y = Integer.parseInt(s[3]);
            HorseWalkingDay hwd = new HorseWalkingDay(n, m, x, y);
            System.out.println(hwd.getAns());
        }

         */

        /*
        // 红与黑
        while (true) {
            String[] wh = bufferedReader.readLine().split(" ");
            int w = Integer.parseInt(wh[0]), h = Integer.parseInt(wh[1]);
            if (w == 0 && h == 0) break;
            String[] s = new String[h];
            for (int i = 0; i < h; i++) {
                s[i] = bufferedReader.readLine();
            }
            RedAndBlack rab = new RedAndBlack(w, h, s);
            System.out.println(rab.getAns());
        }

         */

        /*
        // 迷宫
        int k = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < k; i++) {
            int n = Integer.parseInt(bufferedReader.readLine());
            String[] s = new String[n];
            for (int j = 0; j < n; j++) {
                s[j] = bufferedReader.readLine();
            }
            Maze maze = new Maze(n, s);
            s = bufferedReader.readLine().split(" ");
            int x1 = Integer.parseInt(s[0]), y1 = Integer.parseInt(s[1]), x2 = Integer.parseInt(s[2]), y2 = Integer.parseInt(s[3]);
            if (maze.dfs(x1, y1, x2, y2)) System.out.println("YES");
            else System.out.println("NO");
        }

         */

        /*
        // 八数码
        String s = String.join("", bufferedReader.readLine().split(" "));
        EightDigits ed = new EightDigits(s);
        System.out.println(ed.getAns());

         */

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
