package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TreasureHuntingAdventure {
    // 题目：寻宝大冒险；链接：https://www.acwing.com/problem/content/description/4513/
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int L = Integer.parseInt(s[1]);
        int S = Integer.parseInt(s[2]);
        int[][] A = new int[n][2];
        for (int i = 0; i < n; i++) {
            s = bufferedReader.readLine().split(" ");
            A[i][0] = Integer.parseInt(s[0]);
            A[i][1] = Integer.parseInt(s[1]);
        }

        int num1 = 0;
        int[][] B = new int[S + 1][S + 1];
        for (int i = S; i >= 0; i--) {
            s = bufferedReader.readLine().split(" ");
            for (int j = 0; j <= S; j++) {
                B[i][j] = Integer.parseInt(s[j]);
                num1 += B[i][j];
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (A[i][0] + S > L || A[i][1] + S > L) {
                continue;
            }
            int num2 = 0;
            for (int j = 0; j < n; j++) {
                int x = A[j][0] - A[i][0], y = A[j][1] - A[i][1];
                if (x >= 0 && y >= 0 && x <= S && y <= S) {
                    if (B[x][y] == 1) {
                        num2 += 1;
                    } else {
                        num2 = -9999;
                    }
                }
            }
            if (num2 == num1) {
                ans += 1;
            }
        }
        System.out.println(ans);
    }
}
