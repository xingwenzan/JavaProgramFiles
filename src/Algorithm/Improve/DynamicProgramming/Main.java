package Algorithm.Improve.DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
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
    }
}
