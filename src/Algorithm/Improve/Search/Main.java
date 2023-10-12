package Algorithm.Improve.Search;

import Algorithm.Improve.Search.FloodFill.PeaksAndValleys;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
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
