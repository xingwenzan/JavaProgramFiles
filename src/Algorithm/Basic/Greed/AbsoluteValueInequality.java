package Algorithm.Basic.Greed;

import java.util.Arrays;

public class AbsoluteValueInequality {
    /*
    货仓选址 https://www.acwing.com/problem/content/106/
    n 奇数-中位数 偶数-中间两数区间内任意值 找中位数
     */

    private int[] ints;

    private void toInt(String[] strings, int n) {
        ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = Integer.parseInt(strings[i]);
        }
    }

    public long warehouseLocation(String[] strings, int n) {
        toInt(strings, n);
        Arrays.sort(ints);
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += (long) ints[i] - ints[i / 2];
//            ans += (long) Math.abs(ints[i] - ints[n / 2]);  // 两种写法等价
        }
        return ans;
    }
}
