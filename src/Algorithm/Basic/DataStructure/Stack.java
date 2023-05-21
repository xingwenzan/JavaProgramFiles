package Algorithm.Basic.DataStructure;

public class Stack {
    // 模拟栈 https://www.acwing.com/problem/content/830/
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
}
