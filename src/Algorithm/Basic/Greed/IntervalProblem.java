package Algorithm.Basic.Greed;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class IntervalProblem {
    // 区间选点 https://www.acwing.com/problem/content/907/\
    // 最大不相交区间数量 https://www.acwing.com/problem/content/910/
    // 区间分组 https://www.acwing.com/problem/content/908/
    // 区间覆盖 https://www.acwing.com/problem/content/909/
    ArrayList<int[]> interregional = new ArrayList<>();

    // 区间选点、最大不相交区间数量、区间分组、区间覆盖
    public void add(int l, int r) {
        interregional.add(new int[]{l, r});
    }

    // 区间选点、最大不相交区间数量
    public int intervalSelection() {

        interregional.sort(Comparator.comparingInt(o -> o[1]));
        // 排序 2
//        interregional.sort((o1, o2) -> o1[1] - o2[1]);
        // 排序 1
//        interregional.sort(new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                return o1[1] - o2[1];
//            }
//        });
        int ans = 0, tmp = (int) -2e9;
        for (int[] ints : interregional) {
            if (ints[0] > tmp) {
                ans++;
                tmp = ints[1];
            }
        }
        return ans;
    }

    // 区间分组
    public int intervalGrouping() {
        interregional.sort(Comparator.comparingInt(o -> o[0]));
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int[] ints : interregional) {
            if (heap.isEmpty() || heap.peek() >= ints[0]) {
                heap.add(ints[1]);
            } else {
                heap.poll();
                heap.add(ints[1]);
            }
        }
        return heap.size();
    }

    // 区间覆盖
    public int IntervalCoverage(int start, int end) {
        interregional.sort(Comparator.comparingInt(o -> o[0]));
        int ans = 0, length = interregional.size();
        for (int i = 0; i < length; i++) {
            int j = i, mid = (int) -2e9;
            while (j < length && interregional.get(j)[0] <= start) {
                mid = Math.max(mid, interregional.get(j)[1]);
                j++;
            }
            if (mid < start) {
                return -1;
            }
            ans++;
            if (mid >= end) {
                return ans;
            }
            start = mid;
            i = j - 1;
        }
        return -1;
    }
}
