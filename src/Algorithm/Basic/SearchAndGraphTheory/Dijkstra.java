package Algorithm.Basic.SearchAndGraphTheory;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra {
    // Dijkstra求最短路 I 朴素版 https://www.acwing.com/problem/content/851/
    // Dijkstra求最短路 II 堆优化版 https://www.acwing.com/problem/content/852/
    int NI = 510; // Dijkstra求最短路 I
    int NII = 2 * ((int) 1e5 + 10); // Dijkstra求最短路 II

    int inf = (int) 1e9 + 10;
    int[][] adjacencyMatrix = new int[NI][NI]; // Dijkstra求最短路 I
    int[] distance;
    boolean[] state;
    int[] head = new int[NII], value = new int[NII], nextPointer = new int[NII], weight = new int[NII]; // Dijkstra求最短路 II
    int pointer = 0; // Dijkstra求最短路 II
    PriorityQueue<int[]> heap = new PriorityQueue<>(((o1, o2) -> {
        return o1[1] - o2[1];
    })); // [value,distance[value]]

    public void initI() { // Dijkstra求最短路 I
        distance = new int[NI];
        state = new boolean[NI];
        Arrays.fill(distance, inf);
        Arrays.fill(state, false);
        for (int i = 0; i < NI; i++) {
            Arrays.fill(adjacencyMatrix[i], inf);
        }
    }

    public void initII() { // Dijkstra求最短路 II
        distance = new int[NII];
        state = new boolean[NII];
        Arrays.fill(distance, inf);
        Arrays.fill(state, false);
        Arrays.fill(head, -1);
        Arrays.fill(weight, inf);
        distance[1] = 0;
    }

    public void addI(int start, int end, int length) { // Dijkstra求最短路 I
        adjacencyMatrix[start][end] = Math.min(adjacencyMatrix[start][end], length);
    }

    public void addII(int father, int son, int lenght) {
        value[pointer] = son;
        weight[pointer] = lenght;
        nextPointer[pointer] = head[father];
        head[father] = pointer++;
    }

    public int dijkstraI(int num) { // Dijkstra求最短路 I
        distance[1] = 0;
        for (int i = 1; i < num; i++) {
            int start = -1;
            for (int j = 1; j <= num; j++) {
                if ((!state[j]) && (start == -1 || distance[start] > distance[j])) {
                    start = j;
                }
            }
            state[start] = true;
            for (int end = 1; end <= num; end++) {
                distance[end] = Math.min(distance[end], distance[start] + adjacencyMatrix[start][end]);
            }
        }

        return distance[num] > 1e4 ? -1 : distance[num];
    }


    public int dijkstraII(int num) { // Dijkstra求最短路 II
        heap.offer(new int[]{1, distance[1]});
        while (!heap.isEmpty()) {
            int fatherValue = heap.poll()[0];
            if (!state[fatherValue]) {
                for (int nextSonPointer = head[fatherValue]; nextSonPointer != -1; nextSonPointer = nextPointer[nextSonPointer]) {
                    int sonValue = value[nextSonPointer];
                    if (distance[sonValue] > distance[fatherValue] + weight[nextSonPointer]) {
                        distance[sonValue] = distance[fatherValue] + weight[nextSonPointer];
                        heap.offer(new int[]{sonValue, distance[sonValue]});
                    }
                }
            }
        }
        return distance[num] > 1e9 ? -1 : distance[num];
    }
}
