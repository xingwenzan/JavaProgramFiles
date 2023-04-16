package Algorithm.AlgorithmBasicCourse.DataStructure;

public class Queue {
    // 模拟队列 https://www.acwing.com/problem/content/831/
    int N = 100010;
    int[] values = new int[N];
    int headPointer = 0;
    int tailPointer = 0;

    public void push(int x) {
        values[tailPointer++] = x;
    }

    public void pop() {
        headPointer++;
    }

    public boolean empty() {
        return headPointer >= tailPointer;
    }

    public int query() {
        return values[headPointer];
    }
}
