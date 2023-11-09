package Algorithm.AdvancedGuide.BasicAlgorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // 约数之和
        String[] s = bufferedReader.readLine().split(" ");
        int A = Integer.parseInt(s[0]), B = Integer.parseInt(s[1]);
        DivisorsSum ds = new DivisorsSum(A, B);
        System.out.println(ds.getAns());

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
