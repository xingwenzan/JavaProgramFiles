package Algorithm.AlgorithmTemplate.SearchAndGraphTheory;

public class DFS {
    // 排列数字 https://www.acwing.com/problem/content/844/
    int N = 10;
    boolean[] state = new boolean[N];
    int[] outArray = new int[N];

    public void enumOutput(int currentNum, int upperLimitNum) {
        if (currentNum > upperLimitNum) {
            for (int i = 1; i <= upperLimitNum; i++) {
                System.out.printf("%d ", outArray[i]);
            }
            System.out.println();
        }
        for (int i = 1; i <= upperLimitNum; i++) {
            if (!state[i]) {
                outArray[currentNum] = i;
                state[i] = true;
                enumOutput(currentNum + 1, upperLimitNum);
                state[i] = false;
            }
        }
    }
}
