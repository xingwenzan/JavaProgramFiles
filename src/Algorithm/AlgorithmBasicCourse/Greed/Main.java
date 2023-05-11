package Algorithm.AlgorithmBasicCourse.Greed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // 区间选点、最大不相交区间数量
        IntervalProblem intervalProblem = new IntervalProblem();
        int n = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            String[] strings = bufferedReader.readLine().split(" ");
            intervalProblem.add(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]));
        }
        System.out.println(intervalProblem.intervalSelection());
    }
}