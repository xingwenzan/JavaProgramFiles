package Algorithm.AlgorithmTemplate.SearchAndGraphTheory;

import java.util.Arrays;

public class Dijkstra {
    // Dijkstra求最短路 I https://www.acwing.com/problem/content/851/
    int NI = 510; // Dijkstra求最短路
    int inf = (int) 1e4 + 10;
    int[][] adjacencyMatrix = new int[NI][NI];
    int[] distance = new int[NI];
    boolean[] state = new boolean[NI];

    public void initI() {
        Arrays.fill(distance, inf);
        Arrays.fill(state, false);
        for (int i = 0; i < NI; i++) {
            Arrays.fill(adjacencyMatrix[i], inf);
        }
    }

    public void add(int start, int end, int length) {
        adjacencyMatrix[start][end] = Math.min(adjacencyMatrix[start][end], length);
    }

    public int dijkstraI(int num) {
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
        if (distance[num] > 1e4) {
            return -1;
        }
        return distance[num];
    }
}
