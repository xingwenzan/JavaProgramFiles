package Algorithm.AlgorithmTemplate.SearchAndGraphTheory;

import java.util.*;

public class BFS {
    // 走迷宫 https://www.acwing.com/problem/content/846/
    // 八数码 https://www.acwing.com/problem/content/847/
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

    public String swap(String string, int indexA, int indexB) {
        String[] strings = string.split("");
        String tmp = strings[indexA];
        strings[indexA] = strings[indexB];
        strings[indexB] = tmp;
        StringBuffer stringBuffer = new StringBuffer();
        for (String s : strings) {
            stringBuffer.append(s);
        }
        return stringBuffer.toString();
    }

    public int eightDigits(String start) {
        // 初始化
        int[] verticalChange = {-1, 0, 1, 0};
        int[] horizontalChange = {0, 1, 0, -1};
        String end = "12345678x";
        String tmp;
        Queue<String> pendingSituation = new LinkedList<String>();
        HashMap<String, Integer> stepCount = new HashMap<String, Integer>();
        stepCount.put(start, 0);
        pendingSituation.offer(start);

        // 正式开始
        while (pendingSituation.peek() != null) {
            String currentSituation = pendingSituation.poll();
            if (Objects.equals(currentSituation, end)) {
                return stepCount.get(currentSituation);
            }
            int currentDistance = stepCount.get(currentSituation);
            int xPoint = currentSituation.indexOf('x');
            int xRow = xPoint / 3;
            int xCol = xPoint % 3;
            for (int i = 0; i < 4; i++) {
                int nextRow = xRow + verticalChange[i];
                int nextCol = xCol + horizontalChange[i];
                if (nextCol < 3 && nextCol >= 0 && nextRow < 3 && nextRow >= 0) {
                    int nextPoint = nextRow * 3 + nextCol;
                    tmp = swap(currentSituation, xPoint, nextPoint);
                    if (!stepCount.containsKey(tmp)) {
                        stepCount.put(tmp, currentDistance + 1);
                        pendingSituation.offer(tmp);
                    }
                }
            }
        }

        return -1;
    }

}
