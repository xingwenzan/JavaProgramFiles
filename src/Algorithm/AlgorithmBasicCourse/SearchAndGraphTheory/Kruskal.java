package Algorithm.AlgorithmBasicCourse.SearchAndGraphTheory;

import java.util.Arrays;
import java.util.Comparator;

public class Kruskal {
    // Kruskal算法求最小生成树 https://www.acwing.com/problem/content/861/
    int N = (int) 1e5 + 10, inf = (int) 1e9 + 10, pointer = 0;
    int[] fatherNode = new int[N];
    int[][] edge = new int[2 * N][3];

    public void init() {
        for (int i = 0; i < N; i++) {
            fatherNode[i] = i;
        }
        for (int i = 0; i < N * 2; i++) {
            Arrays.fill(edge[i], inf);
        }
    }

    public void add(int a, int b, int weight) {
        edge[pointer][0] = a;
        edge[pointer][1] = b;
        edge[pointer][2] = weight;
        pointer++;
    }

    public int find(int x) {
        if (fatherNode[x] != x) {
            fatherNode[x] = find(fatherNode[x]);
        }
        return fatherNode[x];
    }

    public int kruskal(int pointNum, int edgeNum) {
        Arrays.sort(edge, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        int ans = 0, cnt = 0;

        for (int i = 0; i < edgeNum; i++) {
            int a = edge[i][0], b = edge[i][1], weight = edge[i][2];
            int aFather = find(a), bFather = find(b);
            if (aFather != bFather) {
                fatherNode[aFather] = bFather;
                ans += weight;
                cnt++;
            }
        }

        return cnt < (pointNum - 1) ? inf : ans;
    }
}
