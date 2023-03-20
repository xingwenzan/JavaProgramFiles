package Algorithm.AlgorithmTemplate.SearchAndGraphTheory;

import java.util.Arrays;

public class HungarianAlgorithm {
    // 二分图的最大匹配 https://www.acwing.com/problem/content/863/
    // https://www.acwing.com/activity/content/code/content/2676803/
    int N = 510, M = (int) 1e5 + 10;
    int[] head = new int[N], value = new int[M], nextPointer = new int[M];   // 存男生看中的或者说可以询问的女生，head 索引是男生编号，其他是女生编号
    int pointer = 0;
    int[] match = new int[N];   // 存与该女生匹配的男生编号，索引是女生编号
    boolean[] query;   // 存本次匹配中该女生是否被询问过，索引是女生编号


    public void init() {
        Arrays.fill(head, -1);
        Arrays.fill(match, 0);
    }

    public void refresh() {
        query = new boolean[N];
        Arrays.fill(query, false);
    }

    public void add(int man, int woman) {
        value[pointer] = woman;
        nextPointer[pointer] = head[man];
        head[man] = pointer++;
    }

    public boolean find(int man) {   // 询问该男生是否找到对象或询问该男生是否可以换对象
        for (int i = head[man]; i != -1; i = nextPointer[i]) {
            int woman = value[i];
            if (!query[woman]) {
                query[woman] = true;
                if (match[woman] == 0 || find(match[woman])) {
                    match[woman] = man;
                    return true;
                }
            }
        }
        return false;
    }
}
