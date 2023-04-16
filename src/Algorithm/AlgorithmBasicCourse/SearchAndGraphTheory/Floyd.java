package Algorithm.AlgorithmBasicCourse.SearchAndGraphTheory;

public class Floyd {
    // Floyd求最短路 https://www.acwing.com/problem/content/856/
    int N = 210, inf = (int) 1e9 + 10;
    int[][] distance = new int[N][N];

    public void init() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    distance[i][j] = 0;
                } else {
                    distance[i][j] = inf;
                }
            }
        }
    }

    public void add(int start, int end, int lenght) {
        distance[start][end] = Math.min(distance[start][end], lenght);
    }

    public int find(int start, int end) {
        return distance[start][end];
    }

    public void floyd(int num) {
        for (int k = 1; k <= num; k++) {
            for (int i = 1; i <= num; i++) {
                for (int j = 1; j <= num; j++) {
                    distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                }
            }
        }
    }


}
