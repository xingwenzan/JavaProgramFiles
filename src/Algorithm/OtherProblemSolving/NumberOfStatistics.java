package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NumberOfStatistics {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int k = Integer.parseInt(strings[1]);
        int num = 0;
        for (int i = 1; i <= n; i++) {
            int x = i;
            while (x > 0) {
                if (x % 10 == k) {
                    num++;
                }
                x /= 10;
            }
        }
        System.out.println(num);
    }
}
