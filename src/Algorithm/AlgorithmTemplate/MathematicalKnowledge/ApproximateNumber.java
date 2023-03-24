package Algorithm.AlgorithmTemplate.MathematicalKnowledge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class ApproximateNumber {
    // 试除法求约数 https://www.acwing.com/problem/content/871/

    public List<Integer> approximationList(int x) {
        List<Integer> ans = new ArrayList<Integer>();
        for (int k = 1; k <= x / k; k++) {
            if (x % k == 0) {
                ans.add(k);
                if (k != x / k) {
                    ans.add(x / k);
                }
            }
        }
        Collections.sort(ans);
        return ans;
    }


    public PriorityQueue<Integer> approximationHeap(int x) {
        PriorityQueue<Integer> ans = new PriorityQueue<>();
        for (int k = 1; k <= x / k; k++) {
            if (x % k == 0) {
                ans.add(k);
                if (k != x / k) {
                    ans.add(x / k);
                }
            }
        }
        return ans;
    }
}
