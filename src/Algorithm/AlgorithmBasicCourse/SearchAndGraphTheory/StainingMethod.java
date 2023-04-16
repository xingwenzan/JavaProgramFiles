package Algorithm.AlgorithmBasicCourse.SearchAndGraphTheory;

import java.util.Arrays;

public class StainingMethod {
    // 染色法判定二分图 https://www.acwing.com/problem/content/description/862/
    int N = (int) 1e5 + 10;
    int[] head = new int[N], value = new int[N * 2], nextPointer = new int[N * 2];
    int pointer = 0;
    int[] color = new int[N];

    public void init() {
        Arrays.fill(head, -1);
        Arrays.fill(color, 0);
    }

    public void add(int start, int end) {
        value[pointer] = end;
        nextPointer[pointer] = head[start];
        head[start] = pointer++;
    }

    public boolean dfs(int father, int colour) {
        color[father] = colour;
        for (int i = head[father]; i != -1; i = nextPointer[i]) {
            int son = value[i];
            if (color[son] == 0) {
                if (!dfs(son, -colour)) {
                    return false;
                }
            } else if (color[son] == colour) {
                return false;
            }
        }
        return true;
    }

    public boolean judge(int num) {
        for (int i = 1; i <= num; i++) {
            if (color[i] == 0) {
                if (!dfs(i, 1)) {
                    return false;
                }
            }
        }
        return true;
    }
}
