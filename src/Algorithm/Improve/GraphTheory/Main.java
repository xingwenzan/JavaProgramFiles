package Algorithm.Improve.GraphTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // 最小花费
        String[] strings = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(strings[0]), m = Integer.parseInt(strings[1]);
        MinimumCost mc = new MinimumCost(n);
        for (int i = 0; i < m; i++) {
            strings = bufferedReader.readLine().split(" ");
            int a = Integer.parseInt(strings[0]), b = Integer.parseInt(strings[1]), c = Integer.parseInt(strings[2]);
            mc.add(a, b, c);
        }
        strings = bufferedReader.readLine().split(" ");
        int s = Integer.parseInt(strings[0]), t = Integer.parseInt(strings[1]);
        mc.setStart(s);
        mc.setEnd(t);

        System.out.printf("%.8f", 100.0 / mc.getAns());

        /*
        // 香甜的黄油
        String[] strings = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(strings[0]), p = Integer.parseInt(strings[1]), c = Integer.parseInt(strings[2]);
        int[] cows = new int[n];
        for (int i = 0; i < n; i++) cows[i] = Integer.parseInt(bufferedReader.readLine());
        SweetButter sb = new SweetButter(p, c, cows);
        for (int i = 0; i < c; i++) {
            strings = bufferedReader.readLine().split(" ");
            int a = Integer.parseInt(strings[0]), b = Integer.parseInt(strings[1]), w = Integer.parseInt(strings[2]);
            sb.add2(a, b, w);
        }
        System.out.println(sb.getAns());

         */

        /*
        // 信使
        String[] strings = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(strings[0]), m = Integer.parseInt(strings[1]);
        Messenger messenger = new Messenger(n);
        for (int i = 0; i < m; i++) {
            strings = bufferedReader.readLine().split(" ");
            int a = Integer.parseInt(strings[0]), b = Integer.parseInt(strings[1]), c = Integer.parseInt(strings[2]);
            messenger.add(a, b, c);
        }
        System.out.println(messenger.getAns());

         */

        /*
        // 热浪
        String[] strings = bufferedReader.readLine().split(" ");
        int t = Integer.parseInt(strings[0]), c = Integer.parseInt(strings[1]), st = Integer.parseInt(strings[2]), ed = Integer.parseInt(strings[3]);
        HeatWave hw = new HeatWave(t, c, st, ed);
        for (int i = 0; i < c; i++) {
            strings = bufferedReader.readLine().split(" ");
            int a = Integer.parseInt(strings[0]), b = Integer.parseInt(strings[1]), w = Integer.parseInt(strings[2]);
            hw.add2(a, b, w);
        }
        System.out.println(hw.getAns());

         */
    }
}
