package Algorithm.Improve.GraphTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
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
    }
}
