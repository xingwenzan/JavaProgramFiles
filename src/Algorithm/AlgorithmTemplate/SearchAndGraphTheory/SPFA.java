package Algorithm.AlgorithmTemplate.SearchAndGraphTheory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SPFA {
    // spfa求最短路 https://www.acwing.com/problem/content/853/
    // spfa判断负环 https://www.acwing.com/problem/content/854/
    int N = (int) 1e5 + 10, inf = (int) 1e9 + 10, pointer = 0;
    int[] head = new int[N], value = new int[N], nextPointer = new int[N], weight = new int[N], distance = new int[N], sizeNum = new int[N];
    boolean[] state = new boolean[N];
    Queue<Integer> queue = new LinkedList<Integer>();

    public void init() {
        Arrays.fill(distance, inf);
        Arrays.fill(state, false);
        Arrays.fill(head, -1);
        Arrays.fill(weight, inf);
    }

    public void add(int father, int son, int lenght) {
        value[pointer] = son;
        weight[pointer] = lenght;
        nextPointer[pointer] = head[father];
        head[father] = pointer++;
    }

    public boolean spfa(int start, int end) {
        distance[start] = 0;
        queue.offer(start);
        state[start] = true;
        Integer fatherValue;
        while (queue.peek() != null) {
            fatherValue = queue.poll();
            state[fatherValue] = false;
            for (int nextSonPointer = head[fatherValue]; nextSonPointer != -1; nextSonPointer = nextPointer[nextSonPointer]) {
                int sonValue = value[nextSonPointer];
                if (distance[sonValue] > distance[fatherValue] + weight[nextSonPointer]) {
                    distance[sonValue] = distance[fatherValue] + weight[nextSonPointer];
                    if (!state[sonValue]) {
                        state[sonValue] = true;
                        queue.offer(sonValue);
                    }
                }
            }
        }
        return distance[end] < 1e9;
    }

    public int findShortestPath(int num) {
        return distance[num];
    }

    public boolean JudgmentNegativeRing(int start, int end) {
        for (int i = start; i <= end; i++) {
            distance[i] = 0;
            queue.offer(i);
            state[i] = true;
        }
        Integer fatherValue;
        while (queue.peek() != null) {
            fatherValue = queue.poll();
            state[fatherValue] = false;
            for (int nextSonPointer = head[fatherValue]; nextSonPointer != -1; nextSonPointer = nextPointer[nextSonPointer]) {
                int sonValue = value[nextSonPointer];
                if (distance[sonValue] > distance[fatherValue] + weight[nextSonPointer]) {
                    distance[sonValue] = distance[fatherValue] + weight[nextSonPointer];
                    sizeNum[sonValue] = sizeNum[fatherValue] + 1;
                    if (sizeNum[sonValue] >= end - start + 1) {
                        return true;
                    }
                    if (!state[sonValue]) {
                        state[sonValue] = true;
                        queue.offer(sonValue);
                    }
                }
            }
        }
        return false;
    }
}
