package Algorithm.AlgorithmTemplate.MathematicalKnowledge;

public class GaussianElimination {
    // 高斯消元解线性方程组 https://www.acwing.com/problem/content/885/

    int N = 110, lenght;
    double[][] equationSet = new double[N][N];
    double eps = 1e-8;

    public void init(int row, int col, double num) {
        equationSet[row][col] = num;
    }

    public int SolvingSystemsOfLinearEquations(int lenght) {
        this.lenght = lenght;
        int col, row;
        for (col = 0, row = 0; col < lenght; col++) {
            int t = row;
            for (int i = row + 1; i < lenght; i++) {
                if (Math.abs(equationSet[t][col]) < Math.abs(equationSet[i][col])) {
                    t = i;
                }
            }
            if (Math.abs(equationSet[t][col]) < eps) {
                continue;
            }
            for (int i = col; i <= lenght; i++) {
                double tmp = equationSet[row][i];
                equationSet[row][i] = equationSet[t][i];
                equationSet[t][i] = tmp;
            }
            for (int i = lenght; i >= col; i--) {
                equationSet[row][i] /= equationSet[row][col];
            }
            for (int r = row + 1; r < lenght; r++) {
                if (Math.abs(equationSet[r][col]) > eps) {
                    for (int c = lenght; c >= col; c--) {
                        equationSet[r][c] -= equationSet[r][col] * equationSet[row][c];
                    }
                }
            }
            row++;
        }
        if (row < lenght) {
            for (int i = row; i < lenght; i++) {
                if (Math.abs(equationSet[i][lenght]) > eps) {
                    return 2;   // 无解
                }
            }
            return 1;   // 无数解
        }
        for (int r = lenght - 1; r >= 0; r--) {
            for (int c = r + 1; c < lenght; c++) {
                equationSet[r][lenght] -= equationSet[r][c] * equationSet[c][lenght];
            }
        }

        return 0;   // 唯一解
    }

    public double[] solution() {
        double[] ans = new double[lenght];
        for (int i = 0; i < lenght; i++) {
            if (Math.abs(equationSet[i][lenght]) < eps) {
                equationSet[i][lenght] = 0;
            }
            ans[i] = equationSet[i][lenght];
        }
        return ans;
    }
}
