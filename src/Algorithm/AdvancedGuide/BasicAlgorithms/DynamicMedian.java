package Algorithm.AdvancedGuide.BasicAlgorithms;

import java.util.PriorityQueue;

/**
 * 动态中位数
 * <a href="https://www.acwing.com/problem/content/108/">...</a>
 */
public class DynamicMedian {
    private final int m;
    private final int[] ints, ans;
    PriorityQueue<Integer> up = new PriorityQueue<>(), down = new PriorityQueue<>((o1, o2) -> o2 - o1);

    public DynamicMedian(int m, int[] ints) {
        this.m = m;
        this.ints = ints;
        ans = new int[(m + 1) / 2];
    }

    private void count() {
        int idx = 0;
        for (int i = 0; i < m; i++) {
            up.add(ints[i]);

            if (!down.isEmpty() && down.peek() > up.peek()) {
                int a = down.poll(), b = up.poll();
                down.add(b);
                up.add(a);
            }

            if (up.size() > down.size() + 1) {
                int a = up.poll();
                down.add(a);
            }

            if ((i & 1) == 0) {
                ans[idx++] = up.peek();
            }
        }
    }

    public int[] getAns() {
        count();
        return ans;
    }
}
