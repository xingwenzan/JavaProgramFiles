package Algorithm.Basic.DynamicProgramming;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class LinearDP {
    /*
    数字三角形 https://www.acwing.com/problem/content/900/
    f 更新前存三角形输入值，更新后存 到该点的路径最大
     */

    /*
    最长上升子序列 https://www.acwing.com/problem/content/897/
    最长上升子序列 II https://www.acwing.com/problem/content/898/
    最长公共子序列 https://www.acwing.com/problem/content/899/
     */

    /*
    最短编辑距离 https://www.acwing.com/activity/content/problem/content/1094/
    https://www.acwing.com/video/334/
    编辑距离 https://www.acwing.com/problem/content/901/
     */


    // 数字三角形 510; 最长上升子序列、最长公共子序列、最短编辑距离、编辑距离 1010; 最长上升子序列 II 1e5+10
    private final int N = 1010;

    // 数字三角形
    private final int[][] numberTriangle = new int[N][N];
    private int row = 0, col = 0;

    // 最长上升子序列; 最长上升子序列 II
    private final int[] lst = new int[N];
    private int idx = 0;

    // 最长上升子序列 II
    private final int[] monotony = new int[N];
    private int length = 0;


    // 数字三角形
    public void addNT(int num) {
        numberTriangle[row][col++] = num;
        if (col > row) {
            col = 0;
            row++;
        }
    }

    // 最长上升子序列; 最长上升子序列 II
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

    // 最长上升子序列 II 二分查找 monotony 中小于 x 的最大值的索引
    private int find(int x) {
        int l = 0, r = length;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (monotony[mid] < x) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    public int longestAscendingSubsequenceII() {
        int tmp;
        for (int i : lst) {
            tmp = find(i);
            length = Math.max(length, tmp + 1);
            monotony[tmp + 1] = i;
        }
        return length;
    }


    // 最长公共子序列
    public int LongestCommonSubsequence(@NotNull String strA, @NotNull String strB) {
        int[][] sub = new int[N][N];
        int lenA = strA.length(), lenB = strB.length();
        strA = " " + strA;
        strB = " " + strB;
        for (int i = 1; i <= lenA; i++) {
            for (int j = 1; j <= lenB; j++) {
                sub[i][j] = Math.max(sub[i - 1][j], sub[i][j - 1]);
                if (strA.charAt(i) == strB.charAt(j)) {
                    sub[i][j] = Math.max(sub[i][j], sub[i - 1][j - 1] + 1);
//                    sub[i][j] = sub[i-1][j-1]+1;
                }
            }
        }
        return sub[lenA][lenB];
    }

    // 最短编辑距离
    public int ShortestEditDistance(@NotNull String strA, @NotNull String strB) {
        int[][] sub = new int[N][N];   // 最短编辑距离 N; 编辑距离 15
        int lenA = strA.length(), lenB = strB.length();
        strA = " " + strA;
        strB = " " + strB;

        for (int i = 0; i <= lenA; i++) {
            sub[i][0] = i;
        }
        for (int i = 0; i <= lenB; i++) {
            sub[0][i] = i;
        }

        for (int i = 1; i <= lenA; i++) {
            for (int j = 1; j <= lenB; j++) {
                sub[i][j] = Math.min(sub[i - 1][j] + 1, sub[i][j - 1] + 1);
                if (strA.charAt(i) == strB.charAt(j)) {
                    sub[i][j] = Math.min(sub[i][j], sub[i - 1][j - 1]);
                } else {
                    sub[i][j] = Math.min(sub[i][j], sub[i - 1][j - 1] + 1);
                }
            }
        }
        return sub[lenA][lenB];
    }


    // 编辑距离
    public int EditDistance(String str, int length, String[] strings, int num) {
        int ans = 0;
        for (int i = 0; i < num; i++) {
            if (ShortestEditDistance(str, strings[i]) <= length) {
                ans++;
            }
        }
        return ans;
    }
}
