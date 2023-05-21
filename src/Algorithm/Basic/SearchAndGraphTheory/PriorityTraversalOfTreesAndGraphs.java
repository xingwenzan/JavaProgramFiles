package Algorithm.Basic.SearchAndGraphTheory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class PriorityTraversalOfTreesAndGraphs {
    // 树与图的深度优先遍历 树的重心 https://www.acwing.com/problem/content/848/
    // 树与图的广度优先遍历 图中点的层次 https://www.acwing.com/problem/content/849/

    int N = (int) 1e5 + 10;
    int M = N * 2; // dfs
    int[] head = new int[N], value = new int[M], nextPointer = new int[M];
    boolean[] state = new boolean[N]; // dfs
    int pointer = 0;
    int ans = N; // dfs
    int[] distance = new int[N]; // bfs
    Queue<Integer> queue = new LinkedList<Integer>(); // bfs

    public void init() {
        Arrays.fill(head, -1);
        Arrays.fill(distance, -1);
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

    public int bfs(int start, int end) {
        distance[start] = 0;
        queue.offer(start);
        while (queue.peek() != null) {
            int fatherValue = queue.peek();
            queue.poll();
            int sonValue;
            for (int nextSonPointer = head[fatherValue]; nextSonPointer != -1; nextSonPointer = nextPointer[nextSonPointer]) {
                sonValue = value[nextSonPointer];
                if (distance[sonValue] == -1) {
                    queue.offer(sonValue);
                    distance[sonValue] = distance[fatherValue] + 1;
                }
            }
        }
        return distance[end];
    }
}
