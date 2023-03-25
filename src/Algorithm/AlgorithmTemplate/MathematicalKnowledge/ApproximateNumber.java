package Algorithm.AlgorithmTemplate.MathematicalKnowledge;

import java.util.*;

public class ApproximateNumber {
    // 试除法求约数 https://www.acwing.com/problem/content/871/
    // 约数个数 https://www.acwing.com/problem/content/872/
    // 约数之和 https://www.acwing.com/problem/content/873/

    int mod = (int) 1e9 + 7;

    public List<Integer> approximationList(int x) {   // 试除法求约数
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


    public PriorityQueue<Integer> approximationHeap(int x) {   // 试除法求约数
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


    public HashMap<Integer, Integer> primeFactor(int[] lst) {   // 约数个数、约数之和
        HashMap<Integer, Integer> prime = new HashMap<>();
        for (int num : lst) {
            for (int i = 2; i <= num / i; i++) {
                while (num % i == 0) {
                    num /= i;
                    if (prime.containsKey(i)) {
                        prime.put(i, prime.get(i) + 1);
                    } else {
                        prime.put(i, 1);
                    }
                }
            }

            if (num > 1) {
                if (prime.containsKey(num)) {
                    prime.put(num, prime.get(num) + 1);
                } else {
                    prime.put(num, 1);
                }
            }
        }
        return prime;
    }

    public long approximateCount(int[] lst) {   // 约数个数
        HashMap<Integer, Integer> prime = primeFactor(lst);
        Set<Integer> baseSet = prime.keySet();
        long ans = 1;
        int index;
        for (int base : baseSet) {
            index = prime.get(base);
            ans = ans * (index + 1) % mod;
        }
        return ans;
    }


    public long approximateSum(int[] lst) {   // 约数之和
        HashMap<Integer, Integer> prime = primeFactor(lst);
        Set<Integer> baseSet = prime.keySet();
        long ans = 1;
        long index, tmp;
        for (int base : baseSet) {
            tmp = 1;
            index = prime.get(base);
            for (int i = 0; i < index; i++) {
                tmp = (tmp * base + 1) % mod;
            }
            ans = ans * tmp % mod;
        }
        return ans;
    }
}
