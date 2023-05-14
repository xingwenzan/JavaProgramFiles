package Algorithm.AlgorithmBasicCourse.Greed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // 区间覆盖
        String[] strings = bufferedReader.readLine().split(" ");
        int st = Integer.parseInt(strings[0]), ed = Integer.parseInt(strings[1]);
        // 区间选点、最大不相交区间数量、区间分组
        IntervalProblem intervalProblem = new IntervalProblem();
        int n = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            strings = bufferedReader.readLine().split(" ");
            intervalProblem.add(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]));
        }
        System.out.println(intervalProblem.intervalSelection());   // 区间选点、最大不相交区间数量
        System.out.println(intervalProblem.intervalGrouping());   // 区间分组
        System.out.println(intervalProblem.IntervalCoverage(st, ed));   // 区间覆盖
    }
}