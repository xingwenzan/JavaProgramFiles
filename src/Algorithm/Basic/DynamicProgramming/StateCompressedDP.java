package Algorithm.Basic.DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;

public class StateCompressedDP {
    // 蒙德里安的梦想 https://www.acwing.com/problem/content/293/
    // 最短Hamilton路径 https://www.acwing.com/problem/content/93/
    private final int N = 20;   // 蒙德里安的梦想 12 最多 12 列(0 - 11 号); 最短Hamilton路径 20
    private final int M = 1 << N;   // 单列最多 M 钟状态
    // 蒙德里安的梦想 f[i][j] 代表到 i 号列之前(不含 i 号)所有列已经摆好，且按 j 的二进制表示凸出(1 凸出 0 不凸出)到 i 号列的状态
    // 最短Hamilton路径 f[i][j] 表示 i 对应状态且最后一个点为 j 的状态的最短路径
    private long[][] f;

    public long MondrianDream(int row, int col) {   // row 行 col 列棋盘
        // 初始化
        // 单列状态可行性，st[j] 是 j 号状态下是否不存在连续奇数个空格，true 为不存在 - 状态合法
        boolean[] st = new boolean[M];
        // 记录状态可行性，state[j] 代表 i 号列状态为 j 时所有合法的 i-1 号列的合法状态
        ArrayList<Integer>[] state = new ArrayList[M];
        for (int i = 0; i < state.length; i++) {
            state[i] = new ArrayList<>();
        }
        f = new long[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(f[i], 0);
        }
        // 预处理单列所有合法状态
        // 合法：不存在连续奇数个空格
        for (int i = 0; i < (1 << row); i++) {   // 遍历该列所有可能的状态
            int cnt = 0;   // 记录连续的0的个数
            boolean isValid = true;   // 该状态没有奇数个连续的 0 则标记为 true
            for (int j = 0; j < row; j++) {   // 遍历该列所有位置
                if (((i >> j) & 1) == 1) {   // 判断第 j 位是否位 1
                    if ((cnt & 1) == 1) {   // 如果是奇数
                        isValid = false;
                        break;
                    }
                } else {
                    cnt++;
                }
            }
            if ((cnt & 1) == 1) {   // 如果是走到了最后，判断一下
                isValid = false;
            }
            st[i] = isValid;
        }
        // 预处理两列间状态合法
        // 合法：不存在冲突摆放且摆放后不存在连续奇数空格
        for (int j = 0; j < (1 << row); j++) {   // i 号列所有状态
            for (int k = 0; k < (1 << row); k++) {   // i-1 号列所有状态
                if ((j & k) == 0 && st[j | k]) {
                    state[j].add(k);
                }
            }
        }
        // dp
        f[0][0] = 1;
        // 这里需要回忆状态表示的定义
        // 按定义这里是：前第-1列都摆好，且从-1列到第0列伸出来的状态为0的方案数。
        // 首先，这里没有-1列，最少也是0列。
        // 其次，没有伸出来，即没有横着摆的。即这里第0列只有竖着摆这1种状态。
        for (int i = 1; i <= col; i++) {   // 遍历每一列:第i列合法范围是(0~m-1列)
            for (int j = 0; j < (1 << row); j++) {   // 遍历当前列（第i列）所有状态j
                for (int k : state[j]) {   // 遍历第i-1列的状态k，如果“真正”可行，就转移
                    f[i][j] += f[i - 1][k];   // 当前列的方案数就等于之前的第i-1列所有状态k的累加
                }
            }
        }
        // 最后答案是什么呢？
        // f[m][0]表示 前m-1列都处理完，并且第m-1列没有伸出来的所有方案数。
        // 即整个棋盘处理完的方案数
        return f[col][0];
    }

    public long ShortestHamiltonPath(int num, String[][] weight) {
        f = new long[M][N];
        for (int i = 0; i < M; i++) {
            Arrays.fill(f[i], (int) 1e9);
        }
        f[1][0] = 0;   // 只经过 0 号位置且终点为 0 的距离一定为 0
        for (int i = 1; i < (1 << num); i += 2) {   // 从 0 号位开始且无论如何必须经过 0 号位
            for (int j = 0; j < num; j++) {   // 遍历所有点做终点
                if (((i >> j) & 1) == 1) {   // 该状态下经过 j 号点
                    for (int k = 0; k < num; k++) {   // 遍历所有点做倒数第二点
                        if (((i >> k) & 1) == 1) {   // 该状态下经过 k 号点
                            f[i][j] = Math.min(f[i][j], f[i - (1 << j)][k] + Integer.parseInt(weight[k][j]));
                        }
                    }
                }
            }
        }
        return f[(1 << num) - 1][num - 1];
    }
}
