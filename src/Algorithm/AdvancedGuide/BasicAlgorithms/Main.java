package Algorithm.AdvancedGuide.BasicAlgorithms;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(System.in);
        // 雷达设备
        int n = scanner.nextInt(), d = scanner.nextInt();
        int[][] lst = new int[n][2];
        for (int i = 0; i < n; i++) {
            lst[i][0] = scanner.nextInt();
            lst[i][1] = scanner.nextInt();
        }
        RadarEquipment re = new RadarEquipment(n, d, lst);
        System.out.println(re.getAns());

        /*
        // 畜栏预定
        int n = scanner.nextInt();
        int[][] lst = new int[n][2];
        for (int i = 0; i < n; i++) {
            lst[i][0] = scanner.nextInt();
            lst[i][1] = scanner.nextInt();
        }
        CorralBooking cb = new CorralBooking(n, lst);
        System.out.println(cb.getCnt());
        int[] ans = cb.getAns();
        for (int i = 0; i < n; i++) System.out.println(ans[i]);

         */

        /*
        // 防晒
        int c = scanner.nextInt(), l = scanner.nextInt();
        ArrayList<int[]> cows = new ArrayList<>();
        for (int i = 0; i < c; i++) {
            int m = scanner.nextInt(), M = scanner.nextInt();
            cows.add(new int[]{m, M});
        }
        int[] spf = new int[1005];
        for (int i = 0; i < l; i++) {
            int spfNum = scanner.nextInt(), cover = scanner.nextInt();
            spf[spfNum] += cover;
        }
        SunProtection sp = new SunProtection(c, cows, spf);
        System.out.println(sp.getAns());

         */

        /*
        // 股票买卖 II
        int n = scanner.nextInt();
        int[] lst = new int[n];
        for (int i = 0; i < n; i++) lst[i] = scanner.nextInt();
        StockJobbingII sj = new StockJobbingII(n, lst);
        System.out.println(sj.getAns());

         */

        /*
        // 倍增
        int N = scanner.nextInt();
        for (int i = 0; i < N; i++) {
            int n = scanner.nextInt(), m = scanner.nextInt();
            long t = scanner.nextLong();
            int[] lst = new int[n];
            for (int j = 0; j < n; j++) lst[j] = scanner.nextInt();
            Multiply my = new Multiply(n, m, t, lst);
            System.out.println(my.getAns());
        }

         */

        /*
        // 奇数码问题
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int[] A = new int[n * n], B = new int[n * n];
            A[0] = B[0] = 0;
            int i = 1;
            for (int j = 0; j < n * n; j++) {
                int tmp = scanner.nextInt();
                if (tmp != 0) A[i++] = tmp;
            }
            i = 1;
            for (int j = 0; j < n * n; j++) {
                int tmp = scanner.nextInt();
                if (tmp != 0) B[i++] = tmp;
            }
            OddNumbers on = new OddNumbers(n, A, B);
            if (on.judge()) System.out.println("TAK");
            else System.out.println("NIE");
        }

         */

        /*
        // 超快速排序
        while (true) {
            int n = scanner.nextInt();
            if (n == 0) break;
            int[] ints = new int[n];
            for (int i = 0; i < n; i++) ints[i] = scanner.nextInt();
            SuperQuickSort sqs = new SuperQuickSort(ints, n);
            System.out.println(sqs.getAns());
        }

         */

        /*
        // 动态中位数
        int N = scanner.nextInt();
        for (int i = 0; i < N; i++) {
            int n = scanner.nextInt(), m = scanner.nextInt();
            int mm = (m + 1) / 2;
            System.out.printf("%d %d\n", n, mm);

            int[] ints = new int[m];
            for (int j = 0; j < m; j++) {
                ints[j] = scanner.nextInt();
            }
            DynamicMedian dm = new DynamicMedian(m, ints);
            int[] ans = dm.getAns();
            for (int j = 0; j < mm; j++) {
                System.out.printf("%d ", ans[j]);
                if ((j + 1) % 10 == 0 || j == mm - 1) System.out.print("\n");
            }
        }

         */

        /*
        // 七夕祭
        String[] s = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(s[0]), m = Integer.parseInt(s[1]), t = Integer.parseInt(s[2]);
        TanabataFestival tf = new TanabataFestival(n, m, t);
        for (int i = 0; i < t; i++) {
            s = bufferedReader.readLine().split(" ");
            n = Integer.parseInt(s[0]);
            m = Integer.parseInt(s[1]);
            tf.add(n, m);
        }
        HashMap<String, Long> ans = tf.getAns();
        for (String string : ans.keySet()) {
            if (ans.get(string) == -1) System.out.println(string);
            else System.out.printf("%s %d", string, ans.get(string));
        }

         */

        /*
        // 货仓选址
        int n = Integer.parseInt(bufferedReader.readLine());
        String[] s = bufferedReader.readLine().split(" ");
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(s[i]);
        }
        WarehouseLocationSelection wls = new WarehouseLocationSelection(n, a);
        System.out.println(wls.getAns());

         */

        /*
        // 电影
        int n = Integer.parseInt(bufferedReader.readLine());
        String[] strings = bufferedReader.readLine().split(" ");
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = Integer.parseInt(strings[i]);
        }
        int m = Integer.parseInt(bufferedReader.readLine());
        int[] b = new int[m], c = new int[m];
        strings = bufferedReader.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(strings[i]);
        }
        strings = bufferedReader.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            c[i] = Integer.parseInt(strings[i]);
        }
        Movie movie = new Movie(p, b, c, m);
        System.out.println(movie.getAns());

         */

        /*
        // 最佳牛围栏
        String[] s = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(s[0]), m = Integer.parseInt(s[1]);
        int[] lst = new int[n];
        for (int i = 0; i < n; i++) {
            lst[i] = Integer.parseInt(bufferedReader.readLine());
        }
        BestCattleFence bcf = new BestCattleFence(n, m, lst);
        System.out.println((int) (bcf.getAns() * 1000));

         */

        /*
        // 最高的牛
        String[] s = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(s[0]), h = Integer.parseInt(s[2]), m = Integer.parseInt(s[3]);
        TallestCow tc = new TallestCow(n, h);
        for (int i = 0; i < m; i++) {
            s = bufferedReader.readLine().split(" ");
            int a = Integer.parseInt(s[0]), b = Integer.parseInt(s[1]);
            if (a > b) tc.add(b, a);
            else tc.add(a, b);
        }
        int[] ans = tc.getCow();
        for (int i = 1; i <= n; i++) {
            System.out.println(ans[i]);
        }

         */

        /*
        // 增减序列
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = Integer.parseInt(bufferedReader.readLine());
        }
        IncDec idc = new IncDec(n, s);
        System.out.println(idc.getCnt());
        System.out.println(idc.getAns());

         */

        /*
        // 激光炸弹
        String[] s = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(s[0]), r = Integer.parseInt(s[1]);
        LaserBomb lb = new LaserBomb(r);
        for (int i = 0; i < n; i++) {
            s = bufferedReader.readLine().split(" ");
            int x = Integer.parseInt(s[0]), y = Integer.parseInt(s[1]), w = Integer.parseInt(s[2]);
            lb.add(x, y, w);
        }
        System.out.println(lb.count());

         */

        /*
        // 分形之城
        int n = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            String[] s = bufferedReader.readLine().split(" ");
            long N = Long.parseLong(s[0]), A = Long.parseLong(s[1]), B = Long.parseLong(s[2]);
            FractalsCity fc = new FractalsCity(N, A, B);
            System.out.println(fc.getAns());
        }

         */

        /*
        // 约数之和
        String[] s = bufferedReader.readLine().split(" ");
        int A = Integer.parseInt(s[0]), B = Integer.parseInt(s[1]);
        DivisorsSum ds = new DivisorsSum(A, B);
        System.out.println(ds.getAns());

         */

        /*
        // 奇怪的汉诺塔
        WeirdHanoiTower wht = new WeirdHanoiTower(12);
        int[] ans = wht.getF();
        for (int i = 1; i <= 12; i++) {
            System.out.println(ans[i]);
        }

         */

        /*
        // 费解的开关
        int n = Integer.parseInt(bufferedReader.readLine());
        char[][] state = new char[5][5];
        for (int i = 0; i < n; i++) {
            if (i != 0) bufferedReader.readLine();
            for (int j = 0; j < 5; j++) {
                String s = bufferedReader.readLine();
                for (int k = 0; k < 5; k++) {
                    state[j][k] = s.charAt(k);
                }
            }

            ConfusingSwitch cs = new ConfusingSwitch(state);
            int ans = cs.getMinStep();
            if (ans > 6) System.out.println(-1);
            else System.out.println(ans);
        }

         */

        /*
        // 递归实现排列型枚举
        int n = Integer.parseInt(bufferedReader.readLine());
        ArrangedEnumeration ae = new ArrangedEnumeration(n);
        ae.allOutput();

         */

        /*
        // 递归实现组合型枚举
        String[] strings = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(strings[0]), m = Integer.parseInt(strings[1]);
        CombinationEnumeration ce = new CombinationEnumeration(n, m);
        ce.allOutput();

         */

        /*
        // 递归实现指数型枚举
        int n = Integer.parseInt(bufferedReader.readLine());
        ExponentialEnumeration ee = new ExponentialEnumeration(n);
        ee.allOutput();

         */

        /*
        // 起床困难综合症
        String[] strings = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(strings[0]), m = Integer.parseInt(strings[1]);
        String[] op = new String[n];
        int[] num = new int[n];
        for (int i = 0; i < n; i++) {
            strings = bufferedReader.readLine().split(" ");
            op[i] = strings[0];
            num[i] = Integer.parseInt(strings[1]);
        }
        DTOS dtos = new DTOS(n, m, op, num);
        System.out.println(dtos.getHurt());

         */

        /*
        // 最短Hamilton路径
        int n = Integer.parseInt(bufferedReader.readLine());
        int[][] ints = new int[n][n];
        String[] strings;
        for (int i = 0; i < n; i++) {
            strings = bufferedReader.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                ints[i][j] = Integer.parseInt(strings[j]);
            }
        }
        ShortestHamiltonPath shp = new ShortestHamiltonPath(n, ints);
        System.out.println(shp.getAns());

         */

        /*
        // 64位整数乘法
        long a = Long.parseLong(bufferedReader.readLine()), b = Long.parseLong(bufferedReader.readLine()), c = Long.parseLong(bufferedReader.readLine());
        BitOF64IntegerMultiplication b64 = new BitOF64IntegerMultiplication(a, b, c);
        System.out.println(b64.getAns());

         */

        /*
        // a^b
        String[] strings = bufferedReader.readLine().split(" ");
        int a = Integer.parseInt(strings[0]), b = Integer.parseInt(strings[1]), c = Integer.parseInt(strings[2]);
        PowerABC p = new PowerABC(a, b, c);
        System.out.println(p.getAns());

         */
    }
}
