package Algorithm.AlgorithmBasicCourse.Greed;

import java.util.ArrayList;
import java.util.Comparator;

public class PushFormula {
    /*
    耍杂技的牛 https://www.acwing.com/problem/content/127/
    https://www.acwing.com/video/122/
     */

    private final ArrayList<int[]> cowList = new ArrayList<>();

    public void add(String[] strings) {
        cowList.add(new int[]{Integer.parseInt(strings[0]), Integer.parseInt(strings[1])});
    }

    public int jugglingCow(int n) {
        cowList.sort(Comparator.comparingInt(o -> o[0] + o[1]));
        int[][] cowArray = new int[n][2];
        for (int i = 0; i < n; i++) {
            cowArray[i] = cowList.get(i);
        }
        int ans = -cowArray[0][1];
        for (int i = 1; i < n; i++) {
            cowArray[i][0] += cowArray[i - 1][0];
            ans = Math.max(ans, cowArray[i - 1][0] - cowArray[i][1]);
        }
        return ans;
    }
}
