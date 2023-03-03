package Algorithm.AlgorithmTemplate.SearchAndGraphTheory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    // 走迷宫 https://www.acwing.com/problem/content/846/
    public int walkMaze(int[][] mazeMap) {
        // 初始化
        int height = mazeMap.length;
        int width = mazeMap[0].length;
        int[] nextHeightNumChange = {-1, 0, 1, 0};
        int[] nextWidthNumChange = {0, 1, 0, -1};
        int nextHeightNum, nextWidthNum;
        Queue<Integer[]> queue = new LinkedList<Integer[]>();
        Integer[] currentPoint = {0, 0};
        Integer[] addPoint;
        queue.offer(currentPoint);
        int[][] schedule = new int[height][width];
        for (int i = 0; i < height; i++) {
            Arrays.fill(schedule[i], -1);
        }
        schedule[0][0] = 0;

        // 正式开始
        while (queue.peek() != null) {
            currentPoint = queue.poll();
            for (int i = 0; i < 4; i++) {
                nextHeightNum = currentPoint[0] + nextHeightNumChange[i];
                nextWidthNum = currentPoint[1] + nextWidthNumChange[i];
                if (nextHeightNum >= 0 && nextHeightNum < height && nextWidthNum >= 0 && nextWidthNum < width && mazeMap[nextHeightNum][nextWidthNum] == 0 && schedule[nextHeightNum][nextWidthNum] == -1) {
                    schedule[nextHeightNum][nextWidthNum] = schedule[currentPoint[0]][currentPoint[1]] + 1;
                    addPoint = new Integer[]{nextHeightNum, nextWidthNum};
                    queue.offer(addPoint);
                }
            }

        }
        return schedule[height - 1][width - 1];

    }
}
