package Algorithm.Improve.DynamicProgramming;

import java.util.ArrayList;
import java.util.HashMap;

public class StateCompressionDP {
    // 小国王 https://www.acwing.com/problem/content/1066/

    private boolean check(int state) {   // 判断该状态是否合法
        return (state & (state << 1)) == 0;
    }

    private int count1(int num) {   // 判断该状态有多少 1
        int ans = 0;
        while (num != 0) {
            ans += num & 1;
            num >>= 1;
        }
        return ans;
    }

    public long LittleKing(int Num, int King) {
        // f[i][j][k]   前 i 行摆好，已经摆放 j 给国王，第 i 行状态 k
        int N = 12, M = 110, S = 1 << 10;
        ArrayList<Integer> state = new ArrayList<>();   // 所有合法状态
        int[] cnt = new int[S];   // 各状态 1 的个数
        HashMap<Integer, ArrayList<Integer>> head = new HashMap<>();   // 各状态可以转移到的状态（a 可转移到 b，b 亦可转移到 a）
        long[][][] f = new long[N][M][S];

        // 预处理所有合法状态
        for (int i = 0; i < (1 << Num); i++) {
            if (check(i)) {
                state.add(i);
                cnt[i] = count1(i);
            }
        }

        // 预处理该状态所有可转移状态
        for (int a : state) {
            for (int b : state) {
                if (((a & b) == 0) && check(a | b)) {
                    if (!head.containsKey(a)) {
                        ArrayList<Integer> tmp = new ArrayList<>();
                        tmp.add(b);
                        head.put(a, tmp);
                    } else {
                        head.get(a).add(b);
                    }
                }
            }
        }

        // DP
        f[0][0][0] = 1;
        for (int i = 1; i <= Num + 1; i++) {
            for (int j = 0; j <= King; j++) {
                for (int a : state) {
                    for (int b : head.get(a)) {
                        if (j >= cnt[a]) {
                            f[i][j][a] += f[i - 1][j - cnt[a]][b];
                        }
                    }
                }
            }
        }
        return f[Num + 1][King][0];
    }
}
