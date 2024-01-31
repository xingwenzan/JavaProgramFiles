package Algorithm.AdvancedGuide.BasicAlgorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 畜栏预定
 * <a href="https://www.acwing.com/problem/content/113/">...</a>
 */
public class CorralBooking {
    private final int n;
    private final ArrayList<int[]> cow;
    private final int[] ans;
    private int cnt = 0;

    public CorralBooking(int n, int[][] cow) {
        this.n = n;
        this.cow = new ArrayList<>();
        for (int i = 0; i < n; i++) this.cow.add(new int[]{cow[i][0], cow[i][1], i});
        ans = new int[n];
        count();
    }

    private void count() {
        // (o1, o2) -> o1[0] - o2[0]
        cow.sort((Comparator.comparingInt(o -> o[0])));
        PriorityQueue<int[]> heap = new PriorityQueue<>((Comparator.comparingInt(o -> o[0])));
        for (int i = 0; i < n; i++) {
            if (heap.isEmpty() || heap.peek()[0] >= cow.get(i)[0]) {
                cnt++;
                heap.add(new int[]{cow.get(i)[1], cnt});
                ans[cow.get(i)[2]] = cnt;
            } else {
                int[] tmp = heap.poll();
                tmp[0] = cow.get(i)[1];
                heap.add(tmp);
                ans[cow.get(i)[2]] = tmp[1];
            }
        }
    }

    public int getCnt() {
        return cnt;
    }

    public int[] getAns() {
        return ans;
    }
}
