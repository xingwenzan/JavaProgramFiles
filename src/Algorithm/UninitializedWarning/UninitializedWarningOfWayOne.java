package Algorithm.UninitializedWarning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UninitializedWarningOfWayOne {
    //题目：未初始化警告；链接：https://www.acwing.com/problem/content/description/4457/
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int k = Integer.parseInt(strings[1]);
        int num = 0;
        boolean[] array = new boolean[n + 1];
        array[0] = true;
        while (k > 0) {
            strings = bufferedReader.readLine().split(" ");
            int x = Integer.parseInt(strings[0]);
            int y = Integer.parseInt(strings[1]);
            if (!array[y]) {
                num++;
            }
            array[x] = true;
            k--;
        }
        System.out.println(num);
    }
}
