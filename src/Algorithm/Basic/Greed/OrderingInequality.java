package Algorithm.Basic.Greed;


import java.util.Arrays;

public class OrderingInequality {
    // 排队打水 https://www.acwing.com/problem/content/description/915/

    private int[] ints;

    private void toInt(String[] strings, int n) {
        ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = Integer.parseInt(strings[i]);
        }
    }

    public long lineUpForWater(String[] strings, int n) {
        toInt(strings, n);
        Arrays.sort(ints);
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += (long) ints[i] * (n - 1 - i);
        }
        return ans;
    }
}
