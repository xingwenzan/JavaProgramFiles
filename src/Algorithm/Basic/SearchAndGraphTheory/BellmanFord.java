package Algorithm.Basic.SearchAndGraphTheory;

import java.util.Arrays;

public class BellmanFord {
    // 有边数限制的最短路 https://www.acwing.com/problem/content/855/
    int N = 510, M = (int) 1e4 + 10;
    int inf = (int) 1e9;
    int pointer = 0;
    int[] distance = new int[N];
    int[][] routes = new int[M][3];

    public void init() {
        Arrays.fill(distance, inf);
        for (int i = 0; i < M; i++) {
            routes[i] = new int[]{0, 0, inf};
        }
        distance[1] = 0;
    }

    public void add(int start, int end, int lenght) {
        routes[pointer++] = new int[]{start, end, lenght};
    }

    public boolean bellmanFord(int pointNum, int routeNum, int cycleNum) {
        for (int i = 0; i < cycleNum; i++) {
            int[] tmp = Arrays.copyOf(distance, distance.length);
            for (int j = 0; j < routeNum; j++) {
                int start = routes[j][0];
                int end = routes[j][1];
                int lenght = routes[j][2];
                distance[end] = Math.min(distance[end], tmp[start] + lenght);
            }
        }
        return distance[pointNum] < inf / 2;
    }

    public int edge(int pointNum) {
        return distance[pointNum];
    }
}
