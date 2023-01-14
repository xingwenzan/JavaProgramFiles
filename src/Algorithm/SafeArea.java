package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SafeArea {
    // 题目：安全区域；链接：https://www.acwing.com/problem/content/description/4798/
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(s[0] + "");
        int m = Integer.parseInt(s[1] + "");
        long[] out = new long[m];
        boolean[] row = new boolean[n];
        boolean[] col = new boolean[n];
        int r = 0;
        int c = 0;
        for (int i = 0; i < m; i++) {
            s = bufferedReader.readLine().split(" ");
            int x = Integer.parseInt(s[0] + "");
            int y = Integer.parseInt(s[1] + "");
            if (!row[x - 1]) {
                r++;
                row[x - 1] = true;
            }
            if (!col[y - 1]) {
                c++;
                col[y - 1] = true;
            }
            out[i] = (long) (n - r) * (n - c);
        }
        for (int i = 0; i < m; i++) {
            System.out.print(out[i] + " ");
        }
    }
}
