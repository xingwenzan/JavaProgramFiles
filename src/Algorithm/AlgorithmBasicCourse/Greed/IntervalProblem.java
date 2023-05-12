package Algorithm.AlgorithmBasicCourse.Greed;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class IntervalProblem {
    // 区间选点 https://www.acwing.com/problem/content/907/\
    // 最大不相交区间数量 https://www.acwing.com/problem/content/910/
    ArrayList<int[]> interregional = new ArrayList<>();

    // 区间选点、最大不相交区间数量、区间分组
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
}
