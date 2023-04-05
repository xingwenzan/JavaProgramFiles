package Algorithm.AlgorithmTemplate.MathematicalKnowledge;

public class GaussianElimination {
    // 高斯消元解线性方程组 https://www.acwing.com/problem/content/885/
    // 高斯消元解异或线性方程组 https://www.acwing.com/problem/content/886/
    // 异或 = 不进位加法

    int N = 110, length;
    double[][] equationSet = new double[N][N];
    int[][] equationSetXOR = new int[N][N];
    double eps = 1e-8;

    public void init(int row, int col, double num) {
        equationSet[row][col] = num;
    }

    public void init(int row, int col, int num) {
        equationSetXOR[row][col] = num;
    }

    public int SolvingSystemsOfLinearEquations(int length) {
        this.length = length;
        int col, row;
        for (col = 0, row = 0; col < length; col++) {
            int t = row;
            for (int i = row + 1; i < length; i++) {
                if (Math.abs(equationSet[t][col]) < Math.abs(equationSet[i][col])) {
                    t = i;
                }
            }
            if (Math.abs(equationSet[t][col]) < eps) {
                continue;
            }
            for (int i = col; i <= length; i++) {
                double tmp = equationSet[row][i];
                equationSet[row][i] = equationSet[t][i];
                equationSet[t][i] = tmp;
            }
            for (int i = length; i >= col; i--) {
                equationSet[row][i] /= equationSet[row][col];
            }
            for (int r = row + 1; r < length; r++) {
                if (Math.abs(equationSet[r][col]) > eps) {
                    for (int c = length; c >= col; c--) {
                        equationSet[r][c] -= equationSet[r][col] * equationSet[row][c];
                    }
                }
            }
            row++;
        }
        if (row < length) {
            for (int i = row; i < length; i++) {
                if (Math.abs(equationSet[i][length]) > eps) {
                    return 2;   // 无解
                }
            }
            return 1;   // 无数解
        }
        for (int r = length - 1; r >= 0; r--) {
            for (int c = r + 1; c < length; c++) {
                equationSet[r][length] -= equationSet[r][c] * equationSet[c][length];
            }
        }

        return 0;   // 唯一解
    }

    public int SolvingXORLinearEquations(int length) {
        this.length = length;
        int col, row;
        for (col = 0, row = 0; col < length; col++) {
            int t = row;
            for (int i = row; i < length; i++) {
                if (equationSetXOR[i][col] == 1) {
                    t = i;
                    break;
                }
            }
            if (equationSetXOR[t][col] == 0) {
                continue;
            }
            for (int i = col; i <= length; i++) {
                int tmp = equationSetXOR[row][i];
                equationSetXOR[row][i] = equationSetXOR[t][i];
                equationSetXOR[t][i] = tmp;
            }
            for (int r = row + 1; r < length; r++) {
                if (equationSetXOR[r][col] == 1) {
                    for (int c = col; c <= length; c++) {
                        equationSetXOR[r][c] ^= equationSetXOR[row][c];
                    }
                }
            }
            row++;
        }
        if (row < length) {
            for (int i = row; i < length; i++) {
                if (equationSetXOR[i][length] != 0) {
                    return 2;
                }
            }
            return 1;
        }
        for (int r = length - 2; r >= 0; r--) {
            for (int c = r + 1; c < length; c++) {
                equationSetXOR[r][length] ^= equationSetXOR[r][c] & equationSetXOR[c][length];
            }
        }
        return 0;
    }

    public double[] solution() {
        double[] ans = new double[length];
        for (int i = 0; i < length; i++) {
            if (Math.abs(equationSet[i][length]) < eps) {
                equationSet[i][length] = 0;
            }
            ans[i] = equationSet[i][length];
        }
        return ans;
    }

    public int[] solutionXOR() {
        int[] ans = new int[length];
        for (int i = 0; i < length; i++) {
            ans[i] = equationSetXOR[i][length];
        }
        return ans;
    }
}
