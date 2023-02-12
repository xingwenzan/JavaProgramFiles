package Algorithm.AlgorithmTemplate.DataStructure;

public class MonotonicStack {
    // 单调栈 https://www.acwing.com/problem/content/832/
    int N = 100010;
    int[] values = new int[N];
    int pointer = 0;

    public void push(int x) {
        values[++pointer] = x;
    }

    public void pop() {
        pointer--;
    }

    public boolean empty() {
        return pointer == 0;
    }

    public int query() {
        return values[pointer];
    }

    public int monotonicPush(int x) {
        while ((!empty()) && x <= query()) {
            pop();
        }
        push(x);
        if (pointer == 1) {
            return -1;
        } else {
            return values[pointer - 1];
        }
    }
}
