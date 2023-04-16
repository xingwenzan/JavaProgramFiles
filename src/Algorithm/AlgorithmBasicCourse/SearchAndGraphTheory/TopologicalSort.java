package Algorithm.AlgorithmBasicCourse.SearchAndGraphTheory;

import java.util.Arrays;

public class TopologicalSort {
    // 有向图的拓扑序列 https://www.acwing.com/problem/content/850/
    // 有向图才有拓扑序列
    // 入度：多少点指向它；出度：它指向多少点
    int N = (int) 1e5 + 10;
    int[] head = new int[N], value = new int[N], nextPointer = new int[N];
    int[] score = new int[N]; // 入度
    int pointer = 0;
    int[] queue = new int[N];
    int headPointer = 0, tailPointer = -1;

    public void init() {
        Arrays.fill(head, -1);
        Arrays.fill(score, 0);
    }

    public void add(int father, int son) {
        value[pointer] = son;
        nextPointer[pointer] = head[father];
        head[father] = pointer++;
        score[son]++;
    }

    public boolean topology(int input) {
        for (int i = 1; i <= input; i++) {
            if (score[i] == 0) {
                queue[++tailPointer] = i;
            }
        }
        while (headPointer <= tailPointer) {
            int fatherValue = queue[headPointer++];
            for (int i = head[fatherValue]; i != -1; i = nextPointer[i]) {
                int sonValue = value[i];
                if (--score[sonValue] == 0) {
                    queue[++tailPointer] = sonValue;
                }
            }
        }
        return tailPointer == input - 1;
    }

}
