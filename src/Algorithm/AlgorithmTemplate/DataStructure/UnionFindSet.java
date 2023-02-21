package Algorithm.AlgorithmTemplate.DataStructure;

import java.util.Arrays;

public class UnionFindSet {
    // 合并集合 https://www.acwing.com/problem/content/838/
    // 连通块中点的数量 https://www.acwing.com/problem/content/839/
    int N = (int) 1e5 + 10;
    int[] fatherNode = new int[N];
    int[] childrenNodeNum = new int[N];

    public void init() {
        for (int i = 0; i < N; i++) {
            fatherNode[i] = i;
        }
        Arrays.fill(childrenNodeNum, 1);
    }

    public int quickForefatherNode(int x) {
        if (fatherNode[x] != x) {
            fatherNode[x] = quickForefatherNode(fatherNode[x]);
        }
        return fatherNode[x];
    }

    public void merge(int x, int y) {
        int forefatherX = quickForefatherNode(x);
        int forefatherY = quickForefatherNode(y);
        if (forefatherY != forefatherX) {
            fatherNode[forefatherX] = forefatherY;
            childrenNodeNum[forefatherY] += childrenNodeNum[forefatherX];
        }
    }

    public boolean determineSetSameOrNot(int x, int y) {
        return quickForefatherNode(x) == quickForefatherNode(y);
    }

    public int NumberOfRendezvousPoints(int x) {
        return childrenNodeNum[quickForefatherNode(x)];
    }
}
