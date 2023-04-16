package Algorithm.AlgorithmBasicCourse.SearchAndGraphTheory;

import java.util.Arrays;

public class Prim {
    // Prim算法求最小生成树 https://www.acwing.com/problem/content/860/
    int N = 510, inf = (int) 1e9 + 10;
    int[][] adjacencyMatrix = new int[N][N];
    int[] distance = new int[N];
    boolean[] state = new boolean[N];

    public void init() {
        Arrays.fill(distance, inf);
        Arrays.fill(state, false);
        for (int i = 0; i < N; i++) {
            Arrays.fill(adjacencyMatrix[i], inf);
        }
    }

    public void add(int start, int end, int length) {
        adjacencyMatrix[start][end] = adjacencyMatrix[end][start] = Math.min(adjacencyMatrix[start][end], length);
    }

    public int prim(int num) {
        distance[1] = 0;
        int ans = 0;
        for (int k = 0; k < num; k++) {
            int start = -1;
            for (int i = 1; i <= num; i++) {
                if ((!state[i]) && (start == -1 || distance[i] < distance[start])) {
                    start = i;
                }
            }

            if (distance[start] == inf) {
                return inf;
            }

            ans += distance[start];
            state[start] = true;

            for (int end = 1; end <= num; end++) {
                distance[end] = Math.min(distance[end], adjacencyMatrix[start][end]);
            }
        }
        return ans;
    }
}
