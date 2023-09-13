package Algorithm.Improve.DynamicProgramming;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class MonotonicQueueOptimizingDP {
    /*---------------------** 注释部分 **---------------------*/

    // 最大子序和 https://www.acwing.com/problem/content/137/
    // 修剪草坪 https://www.acwing.com/problem/content/1089/
    // 烽火传递 https://www.acwing.com/problem/content/1091/
    // 绿色通道 https://www.acwing.com/problem/content/1092/

    /*---------------------** 变量定义部分 **---------------------*/

    private final int INF = 0x3f3f3f3f;

    /*---------------------** 私有函数部分 **---------------------*/

    /**
     * 前缀和
     *
     * @param lst 输入数组
     * @return 返回前缀和数组，且开头额外多个 0
     */
    @Contract(pure = true)
    private int @NotNull [] prefixSum(int @NotNull [] lst) {
        int n = lst.length;
        int[] ints = new int[n + 1];
        ints[0] = 0;
        for (int i = 0; i < n; i++) {
            ints[i + 1] = ints[i] + lst[i];
        }
        return ints;
    }

    /**
     * 使用单调队列判定是否合适
     *
     * @param lst  输入的 ai 数组
     * @param lim  判定该数是否符合结果
     * @param time 判定条件
     * @return 是否
     */
    private boolean checkDP(int @NotNull [] lst, int lim, int time) {
        int n = lst.length;
        MyQueue q = new MyQueue(n + 10);
        q.add(0);
        int[] f = new int[n + 10];
        for (int i = 1; i <= n; i++) {
            if (q.head() < i - lim - 1) q.pollHead();
            f[i] = f[q.head()] + lst[i - 1];
            while (q.notEmpty() && f[q.tail()] >= f[i]) q.pollTail();
            q.add(i);
        }
        for (int i = n - lim; i <= n; i++) {
            if (f[i] <= time) return true;
        }
        return false;
    }

    /*---------------------** 题目主体函数部分 **---------------------*/

    public int MaximumSubsequenceSum(int[] lst, int length) {
        int[] s = prefixSum(lst);
        MyQueue q = new MyQueue(300010);
        int ans = -INF;
        q.add(0);
        for (int i = 1; i < s.length; i++) {
            if (q.head() < i - length) q.pollHead();
            ans = Math.max(ans, s[i] - s[q.head()]);
            while (q.notEmpty() && s[q.tail()] >= s[i]) q.pollTail();
            q.add(i);
        }
        return ans;
    }

    public long MowingLawn(int @NotNull [] lst, int length) {
        int n = lst.length;
        MyQueue q = new MyQueue(100010);
        q.add(0);
        MowingLawnClass ml = new MowingLawnClass(lst);
        for (int i = 1; i <= n; i++) {
            if (q.head() < i - length) q.pollHead();
            ml.f[i] = Math.max(ml.f[i - 1], ml.g(q.head()) + ml.s[i]);
            while (q.notEmpty() && ml.g(q.tail()) <= ml.g(i)) q.pollTail();
            q.add(i);
        }
        return ml.ans();
    }

    public int BeaconRelay(int @NotNull [] lst, int length) {
        int n = lst.length;
        MyQueue q = new MyQueue(n + 10);
        q.add(0);
        int[] f = new int[n + 10];
        for (int i = 1; i <= n; i++) {
            if (q.head() < i - length) q.pollHead();
            f[i] = f[q.head()] + lst[i - 1];
            while (q.notEmpty() && f[q.tail()] >= f[i]) q.pollTail();
            q.add(i);
        }
        int ans = INF;
        for (int i = n; i >= n - length + 1; i--) {
            ans = Math.min(ans, f[i]);
        }
        return ans;
    }

    public int GreenChannel(int @NotNull [] lst, int time) {
        int l = 0, r = lst.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (checkDP(lst, mid, time)) r = mid;
            else l++;
        }
        return r;
    }

    /*---------------------** 内部类部分 **---------------------*/

    /**
     * 队列类
     * 可前后读
     */
    private static class MyQueue {
        private final int[] queue;
        private int tt = -1, hh = 0;

        public MyQueue(int length) {
            queue = new int[length];
        }

        public int length() {
            return tt - hh + 1;
        }

        public boolean notEmpty() {
            return tt >= hh;
        }

        public int head() {
            return queue[hh];
        }

        public int tail() {
            return queue[tt];
        }

        public void pollHead() {
            hh++;
        }

        public void pollTail() {
            tt--;
        }

        public void add(int x) {
            queue[++tt] = x;
        }
    }

    /**
     * 修剪草坪专用类
     */
    private static class MowingLawnClass {
        long[] f, s;

        public MowingLawnClass(int[] lst) {
            int n = lst.length;
            s = new long[n + 1];
            for (int i = 0; i < n; i++) {
                s[i + 1] = s[i] + lst[i];
            }
            f = new long[n + 1];
        }

        public long g(int x) {
            if (x == 0) return 0;
            return f[x - 1] - s[x];
        }

        public long ans() {
            return f[f.length - 1];
        }
    }
}
