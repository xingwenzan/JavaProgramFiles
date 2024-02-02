package Algorithm.AdvancedGuide.BasicAlgorithms;

import java.util.ArrayList;

/**
 * 雷达设备
 * <a href="https://www.acwing.com/problem/content/114/">...</a>
 */
public class RadarEquipment {
    private final double eps = 1e-6;
    private final int n;
    private final ArrayList<double[]> intercept = new ArrayList<>();
    private int ans = 0;

    public RadarEquipment(int n, int d, int[][] coordinate) {
        this.n = n;
        for (int i = 0; i < n; i++) {
            double x = coordinate[i][0], y = coordinate[i][1];
            if (y > d) {
                ans = -1;
                break;
            }
            double tmp = Math.sqrt(d * d - y * y);
            intercept.add(new double[]{x - tmp, x + tmp});
        }
    }

    private void count() {
        if (ans == -1) return;
        intercept.sort((o1, o2) -> {
            int flag = 0;
            if (o1[1] - o2[1] > eps) flag = 1;
            else if (o2[1] - o1[1] > eps) flag = -1;
            return flag;
        });
        /*
        intercept.sort(new Comparator<double[]>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                int flag = 0;
                if (o1[1]-o2[1]>eps)flag = 1;
                else if (o2[1] - o1[1] > eps) flag = -1;
                return flag;
            }
        });

         */
        double last = -1e9;
        for (int i = 0; i < n; i++) {
            double up = intercept.get(i)[0], down = intercept.get(i)[1];
            if (last < up - eps) {
                last = down;
                ans++;
            }
        }
    }

    public int getAns() {
        count();
        return ans;
    }
}
