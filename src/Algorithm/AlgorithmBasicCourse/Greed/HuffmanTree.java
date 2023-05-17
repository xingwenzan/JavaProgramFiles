package Algorithm.AlgorithmBasicCourse.Greed;

import java.util.PriorityQueue;

public class HuffmanTree {
    // 合并果子 https://www.acwing.com/problem/content/150/
    PriorityQueue<Integer> heap = new PriorityQueue<>();

    private void toHeap(String[] strings) {
        for (String s : strings) {
            heap.add(Integer.parseInt(s));
        }
    }

    public int mergeFruit(String[] strings) {
        toHeap(strings);
        int ans = 0;
        while (heap.size() > 1) {
            int a = heap.poll();
            int b = heap.poll();
            ans += (a + b);
            heap.add(a + b);
        }
        return ans;
    }
}
