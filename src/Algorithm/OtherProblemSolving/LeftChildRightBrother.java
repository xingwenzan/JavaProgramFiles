package Algorithm.OtherProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LeftChildRightBrother {
    // 左孩子右兄弟 https://www.acwing.com/problem/content/3425/

    static int N = (int) 1e5 + 10;
    static int[] head = new int[N], value = new int[N], nextPointer = new int[N];
    static int pointer = 0;

    public static void init() {
        Arrays.fill(head, -1);
    }

    public static void addValue(int father, int son) {
        value[pointer] = son;
        nextPointer[pointer] = head[father];
        head[father] = pointer++;
    }

    public static int dfs(int fatherValue) {
        int maxHeight = 0, sonNum = 0;
        int sonValue;
        for (int i = head[fatherValue]; i != -1; i = nextPointer[i]) {
            sonValue = value[i];
            maxHeight = Math.max(maxHeight, dfs(sonValue));
            sonNum++;
        }
        return maxHeight + sonNum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        init();
        int n = Integer.parseInt(bufferedReader.readLine());
        for (int i = 2; i <= n; i++) {
            int fatherValue = Integer.parseInt(bufferedReader.readLine());
            addValue(fatherValue, i);
        }
        System.out.println(dfs(1));
    }
}
