package Algorithm.AlgorithmBasicCourse.DynamicProgramming;

import java.util.Arrays;

public class LinearDP {
    /*
    数字三角形 https://www.acwing.com/problem/content/900/
    f 更新前存三角形输入值，更新后存 到该点的路径最大
     */

    /*
    最长上升子序列 https://www.acwing.com/problem/content/897/
     */

    // 数字三角形 510; 最长上升子序列 1010
    private final int N = 1010;

    // 数字三角形
    private final int[][] numberTriangle = new int[N][N];
    private int row = 0, col = 0;

    // 最长上升子序列
    private final int[] lst = new int[N];
    private int idx = 0;

    // 数字三角形
    public void addNT(int num) {
        numberTriangle[row][col++] = num;
        if (col > row) {
            col = 0;
            row++;
        }
    }

    // 最长上升子序列
    public void addLAS(int num) {
        lst[idx++] = num;
    }

    // 数字三角形
    public int numberTriangleDP(int num) {   // 最上面的是 (0,0)，num 是行数，故最下面一行是 num-1，从倒数第二行更新
        for (int i = num - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                numberTriangle[i][j] += Math.max(numberTriangle[i + 1][j], numberTriangle[i + 1][j + 1]);
            }
        }
        return numberTriangle[0][0];
    }

    // 最长上升子序列
    public int longestAscendingSubsequenceDP() {
        int[] length = new int[idx];
        Arrays.fill(length, 1);
        for (int i = 0; i < idx; i++) {
            for (int j = 0; j < i; j++) {
                if (lst[i] > lst[j]) {
                    length[i] = Math.max(length[i], length[j] + 1);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < idx; i++) {
            ans = Math.max(ans, length[i]);
        }
        return ans;
    }
}
