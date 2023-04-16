package Algorithm.AlgorithmBasicCourse.SearchAndGraphTheory;

import java.util.Arrays;

public class DFS {
    // 排列数字 https://www.acwing.com/problem/content/844/
    // n-皇后问题 https://www.acwing.com/problem/content/845/
    int N = 20;
    boolean[] state = new boolean[N];
    boolean[] col = new boolean[N], diagonal = new boolean[N], reverseDiagonal = new boolean[N];
    int[] outArray = new int[N];
    char[][] chessBoard;
    int allNum;

    public void init2D(int n) {
        this.chessBoard = new char[n][n];
        this.allNum = n;
        for (int i = 0; i < n; i++) {
            Arrays.fill(chessBoard[i], '.');
        }
    }

    public void enumOutput1D(int currentNum, int upperLimitNum) {
        if (currentNum > upperLimitNum) {
            for (int i = 1; i <= upperLimitNum; i++) {
                System.out.printf("%d ", outArray[i]);
            }
            System.out.println();
        }
        for (int i = 1; i <= upperLimitNum; i++) {
            if (!state[i]) {
                outArray[currentNum] = i;
                state[i] = true;
                enumOutput1D(currentNum + 1, upperLimitNum);
                state[i] = false;
            }
        }
    }

    public void enumOutput2D(int rowNum) { // allNum 代表总行数、总列数、总最大棋子数
        if (rowNum >= allNum) {
            for (int i = 0; i < allNum; i++) {
                System.out.println(new String(chessBoard[i]));
            }
            System.out.println();
        }
        for (int colNum = 0; colNum < allNum; colNum++) {
            if (!(col[colNum] || diagonal[rowNum - colNum + allNum] || reverseDiagonal[rowNum + colNum])) {
                chessBoard[rowNum][colNum] = 'Q';
                col[colNum] = diagonal[rowNum - colNum + allNum] = reverseDiagonal[rowNum + colNum] = true;
                enumOutput2D(rowNum + 1);
                chessBoard[rowNum][colNum] = '.';
                col[colNum] = diagonal[rowNum - colNum + allNum] = reverseDiagonal[rowNum + colNum] = false;
            }
        }
    }
}
