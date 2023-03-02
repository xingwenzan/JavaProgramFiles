package Algorithm.AlgorithmTemplate.SearchAndGraphTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        DFS dfs = new DFS();
        //dfs.enumOutput1D(1, n); // 排列数字
        dfs.init2D(n);
        dfs.enumOutput2D(0); // n-皇后问题
    }
}
