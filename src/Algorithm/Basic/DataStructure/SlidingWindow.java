package Algorithm.Basic.DataStructure;

public class SlidingWindow {
    // 滑动窗口 https://www.acwing.com/problem/content/description/156/
    int N = 1000010;
    int[] values = new int[N];
    int headPointer = 0;
    int tailPointer = -1;

    public void init() {
        headPointer = 0;
        tailPointer = -1;
    }

    public void push(int x) {
        values[++tailPointer] = x;
    }

    public void popHead() {
        headPointer++;
    }

    public void popTail() {
        tailPointer--;
    }

    public boolean empty() {
        return headPointer > tailPointer;
    }

    public int queryHead() {
        return values[headPointer];
    }

    public int queryTail() {
        return values[tailPointer];
    }

    public void windowMonotonicPush(int[] lst, int superscript, boolean ascending, int windowSize) {
        if ((!empty()) && (queryHead() < superscript - windowSize + 1)) {
            popHead();
        }
        while ((!empty()) && (ascending ? (lst[queryTail()] >= lst[superscript]) : (lst[queryTail()] <= lst[superscript]))) {
            popTail();
        }
        push(superscript);
    }

}
