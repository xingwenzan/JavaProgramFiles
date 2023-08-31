package Algorithm.Improve.DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


        /*
        // 数字游戏
        DigitalDP d = new DigitalDP();
        String str = "";
        while ((str = bufferedReader.readLine()) != null) {
            String[] arr = str.split(" ");
            int l = Integer.parseInt(arr[0]);
            int r = Integer.parseInt(arr[1]);
            System.out.println(d.NumbersGame(l, r));
        }

         */


        // 度的数量、Windy数
        DigitalDP d = new DigitalDP();
        String[] strings = bufferedReader.readLine().split(" ");
        int l = Integer.parseInt(strings[0]), r = Integer.parseInt(strings[1]);
        System.out.println(d.WindyNumber(l, r));   // Windy数
        int k = Integer.parseInt(bufferedReader.readLine());
        int b = Integer.parseInt(bufferedReader.readLine());
        System.out.println(d.NumberOfDegrees(l, r, b, k));


        /*
        // 皇宫看守
        TreeDP t = new TreeDP();
        t.treeInit();
        int n = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            String[] strings = bufferedReader.readLine().split(" ");
            t.addPalace(strings);
        }
        System.out.println(t.PalaceGuard());

         */


        /*
        // 二叉苹果树
        TreeDP t = new TreeDP();
        t.treeInit();
        String[] strings = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(strings[0]), v = Integer.parseInt(strings[1]);
        for (int i = 0; i < n - 1; i++) {
            strings = bufferedReader.readLine().split(" ");
            int a = Integer.parseInt(strings[0]), b = Integer.parseInt(strings[1]), c = Integer.parseInt(strings[2]);
            t.add2(a, b, c);
        }
        System.out.println(t.BinaryAppleTree(v));

         */


        /*
        // 树的最长路径、树的中心、数字转换
        TreeDP t = new TreeDP();
        t.treeInit();
        int n = Integer.parseInt(bufferedReader.readLine());
        System.out.println(t.DigitalConversion(n));   // 数字转换
        String[] strings;
        for (int i = 0; i < n - 1; i++) {
            strings = bufferedReader.readLine().split(" ");
            int a = Integer.parseInt(strings[0]), b = Integer.parseInt(strings[1]), c = Integer.parseInt(strings[2]);
            t.add2(a, b, c);
        }
        System.out.println(t.TreeLongestPath());   // 树的最长路径
        System.out.println(t.TreeCenter(n));   // 树的中心

         */


        /*
        // 环形石子合并、能量项链、加分二叉树、凸多边形的划分
        IntervalDP intervalDP = new IntervalDP();
        int n = Integer.parseInt(bufferedReader.readLine());
        String[] strings = bufferedReader.readLine().split(" ");
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = Integer.parseInt(strings[i]);
        }
        System.out.println(intervalDP.EnergyNecklace(w, n));   // 能量项链
        IntervalDP.PII Mm = intervalDP.RingPebblesMerge(w, n);   // 环形石子合并
        System.out.printf("%d\n%d", Mm.y, Mm.x);   // 环形石子合并
        // 加分二叉树
        HashMap<Integer, ArrayList<Integer>> BBT = intervalDP.BonusBinaryTree(w, n);
        for (int i : BBT.keySet()) {
            System.out.println(i);
            for (int j : BBT.get(i)) {
                System.out.printf("%d ", j);
            }
            System.out.print("\n");
        }
        // 凸多边形的划分
        IntervalDP.HighPrecisionNum num = intervalDP.ConvexPolygonsDivision(w, n);
        num.outputNum();

         */


        /*
        // 宝藏
        String[] strings = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(strings[0]), m = Integer.parseInt(strings[1]);
        StateCompressionDP sc = new StateCompressionDP();
        strings = new String[m];
        for (int i = 0; i < m; i++) {
            strings[i] = bufferedReader.readLine();
        }
        System.out.println(sc.PreciousDeposits(n, strings));

         */


        /*
        // 愤怒的小鸟
        int T = Integer.parseInt(bufferedReader.readLine());
        while (0 < T--) {
            String[] strings = bufferedReader.readLine().split(" ");
            int n = Integer.parseInt(strings[0]);
            strings = new String[n];
            for (int i = 0; i < n; i++) {
                strings[i] = bufferedReader.readLine();
            }
            StateCompressionDP sc = new StateCompressionDP();
            System.out.println(sc.AngryBirds(n, strings));
        }

         */


        /*
        // 小国王、玉米田、炮兵阵地
        String[] strings = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(strings[0]), k = Integer.parseInt(strings[1]);
        StateCompressionDP sc = new StateCompressionDP();
        // 以下三者并列
        // 小国王
        System.out.println(sc.LittleKing(n, k));
        // 玉米田
        String[][] s = new String[n][k];
        for (int i = 0; i < n; i++) {
            strings = bufferedReader.readLine().split(" ");
            s[i] = strings;
        }
        System.out.println(sc.Cornfield(n, k, s));
        // 炮兵阵地
        String[] ss = new String[n];
        for (int i = 0; i < n; i++) {
            ss[i] = bufferedReader.readLine();
        }
        System.out.println(sc.ArtilleryEmplacement(n, k, ss));

         */


        /*
        // 修复DNA
        StateMachineModel sm = new StateMachineModel();
        int t = 1;
        while (true) {
            sm.init();
            int n = Integer.parseInt(bufferedReader.readLine());
            if (n == 0) {
                break;
            }
            for (int i = 0; i < n; i++) {
                sm.add(bufferedReader.readLine());
            }
            String s = bufferedReader.readLine();
            System.out.printf("Case %d: %d\n", t++, sm.RepairDNA(s));
        }

         */


        /*
        // 设计密码
        int n = Integer.parseInt(bufferedReader.readLine());
        String s = bufferedReader.readLine();
        StateMachineModel sm = new StateMachineModel();
        System.out.println(sm.DesignPassword(s, n));

         */


        /*
        // 股票买卖 V
        bufferedReader.readLine();
        StateMachineModel sm = new StateMachineModel();
        String[] strings = bufferedReader.readLine().split(" ");
        System.out.println(sm.StockTradingV(strings));

         */


        /*
        // 股票买卖 IV
        String[] strings = bufferedReader.readLine().split(" ");
        int k = Integer.parseInt(strings[1]);
        strings = bufferedReader.readLine().split(" ");
        StateMachineModel sm = new StateMachineModel();
        System.out.println(sm.StockTradingIV(strings, k));

         */


        /*
        // 大盗阿福
        int t = Integer.parseInt(bufferedReader.readLine());
        StateMachineModel sm = new StateMachineModel();
        for (int i = 0; i < t; i++) {
            bufferedReader.readLine();
            String[] strings = bufferedReader.readLine().split(" ");
            System.out.println(sm.ThiefAlfred(strings));
        }

         */


        /*
        // 能量石
        BackpackModel bp = new BackpackModel();
        int T = Integer.parseInt(bufferedReader.readLine());
        for (int i = 1; i <= T; i++) {
            int N = Integer.parseInt(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                String[] strings = bufferedReader.readLine().split(" ");
                bp.add(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));
            }
            System.out.printf("Case #%d: %d\n", i, bp.EnergyStone());
        }

         */


        /*
        // 有依赖的背包问题、背包问题求方案数、背包问题求具体方案
        String[] strings = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(strings[0]), V = Integer.parseInt(strings[1]);
        BackpackModel bp = new BackpackModel();
//        bp.dependentInit();   // 有依赖的背包问题
        for (int i = 1; i <= N; i++) {
            strings = bufferedReader.readLine().split(" ");
            // 有依赖的背包问题、背包问题求方案数、背包问题求具体方案
            int v = Integer.parseInt(strings[0]), w = Integer.parseInt(strings[1]);
//            int p = Integer.parseInt(strings[2]);   // 有依赖的背包问题
//            bp.add(i, p, v, w);   // 有依赖的背包问题
            bp.add(v, w);   // 背包问题求方案数、背包问题求具体方案
        }
//        System.out.println(bp.Dependent(V));   // 有依赖的背包问题
//        System.out.println(bp.PlanNumber(V));   // 背包问题求方案数
        bp.SpecificPlanOut(V);   // 背包问题求具体方案

         */


        /*
        // 机器分配
        String[] strings = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(strings[0]), M = Integer.parseInt(strings[1]);
        BackpackModel bp = new BackpackModel();
        for (int i = 0; i < N; i++) {
            strings = bufferedReader.readLine().split(" ");
            bp.add(strings, M);
        }
        bp.MachineDistributionOut(N, M);

         */


        /*
        // 潜水员
        String[] strings = bufferedReader.readLine().split(" ");
        int M = Integer.parseInt(strings[0]), N = Integer.parseInt(strings[1]);
        int K = Integer.parseInt(bufferedReader.readLine());
        BackpackModel bp = new BackpackModel();
        for (int i = 0; i < K; i++) {
            strings = bufferedReader.readLine().split(" ");
            bp.add(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));
        }
        System.out.println(bp.Diver(M, N));

         */


        /*
        // 二维费用的背包问题
        String[] strings = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(strings[0]), V = Integer.parseInt(strings[1]), M = Integer.parseInt(strings[2]);
        BackpackModel bp = new BackpackModel();
        for (int i = 0; i < N; i++) {
            strings = bufferedReader.readLine().split(" ");
            bp.add(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));
        }
        System.out.println(bp.TwoDimensionalCost(V, M));

         */


        /*
        // 多重背包问题 III、庆功会、混合背包问题、二维费用的背包问题
        String[] strings = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(strings[0]), V = Integer.parseInt(strings[1]);
        BackpackModel bp = new BackpackModel();
        for (int i = 0; i < N; i++) {
            strings = bufferedReader.readLine().split(" ");
            bp.add(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));
        }
        System.out.println(bp.MultipleKnapsackIII(V));   // 多重背包问题 III
        System.out.println(bp.VictoryMeeting(V));   // 庆功会
        System.out.println(bp.MixedKnapsack(V));   // 混合背包问题

         */


        /*
        // 货币系统-NOPI
        int t = Integer.parseInt(bufferedReader.readLine());
        BackpackModel bp = new BackpackModel();
        for (int i = 0; i < t; i++) {
            bufferedReader.readLine();
//            int n = Integer.parseInt(bufferedReader.readLine());
            String[] strings = bufferedReader.readLine().split(" ");
            System.out.println(bp.MonetarySystemNOIP(strings));
        }

         */


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
