package Algorithm.Improve.Search;

import Algorithm.Improve.Search.FloodFill.PondCount;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // 池塘计数
        String[] strings = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(strings[0]), m = Integer.parseInt(strings[1]);
        strings = new String[n];
        for (int i = 0; i < n; i++) {
            strings[i] = bufferedReader.readLine();
        }
        PondCount PC = new PondCount(strings, n, m);
        System.out.println(PC.getAns());
    }
}
