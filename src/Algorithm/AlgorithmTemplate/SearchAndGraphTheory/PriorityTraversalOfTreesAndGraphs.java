package Algorithm.AlgorithmTemplate.SearchAndGraphTheory;

import java.util.Arrays;

public class PriorityTraversalOfTreesAndGraphs {
    // 树与图的深度优先遍历 树的重心 https://www.acwing.com/problem/content/848/

    int N = (int) 1e5 + 10;
    int M = N * 2;
    int[] head = new int[N], value = new int[M], nextPointer = new int[M];
    boolean[] state = new boolean[N];
    int pointer = 0;
    int ans = N;

    public void dfsInit() {
        Arrays.fill(head, -1);
    }

    public void add(int father, int son) {
        value[pointer] = son;
        nextPointer[pointer] = head[father];
        head[father] = pointer++;
    }

    public int dfs(int fatherValue, int allPointerNum) {
        state[fatherValue] = true;
        int maxConnectedBlockSize = 0, allChildNum = 0;
        for (int sonPointer = head[fatherValue]; sonPointer != -1; sonPointer = nextPointer[sonPointer]) {
            int sonValue = value[sonPointer];
            if (!state[sonValue]) {
                int childNum = dfs(sonValue, allPointerNum);
                maxConnectedBlockSize = Math.max(maxConnectedBlockSize, childNum);
                allChildNum += childNum;
            }

        }
        maxConnectedBlockSize = Math.max(maxConnectedBlockSize, allPointerNum - allChildNum - 1);
        ans = Math.min(ans, maxConnectedBlockSize);
        return allChildNum + 1;
    }

    public int centerOfGravityOfTree(int n) {
        dfs(1, n);
        return ans;
    }
}
