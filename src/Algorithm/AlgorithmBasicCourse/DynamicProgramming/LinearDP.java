package Algorithm.AlgorithmBasicCourse.DynamicProgramming;

public class LinearDP {
    /*
    数字三角形 https://www.acwing.com/problem/content/900/
    f 更新前存三角形输入值，更新后存 到该点的路径最大
     */
    private final int N = 510;   // 数字三角形 510;
    private final int[][] numberTriangle = new int[N][N];
    private int row = 0, col = 0;

    public void addNT(int num) {
        numberTriangle[row][col++] = num;
        if (col > row) {
            col = 0;
            row++;
        }
    }

    public int numberTriangleDP(int num) {   // 最上面的是 (0,0)，num 是行数，故最下面一行是 num-1，从倒数第二行更新
        for (int i = num - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                numberTriangle[i][j] += Math.max(numberTriangle[i + 1][j], numberTriangle[i + 1][j + 1]);
            }
        }
        return numberTriangle[0][0];
    }
}
