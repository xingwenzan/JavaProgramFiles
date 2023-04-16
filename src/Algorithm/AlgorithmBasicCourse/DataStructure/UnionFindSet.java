package Algorithm.AlgorithmBasicCourse.DataStructure;

import java.util.Arrays;

public class UnionFindSet {
    // 合并集合 https://www.acwing.com/problem/content/838/
    // 连通块中点的数量 https://www.acwing.com/problem/content/839/
    // 食物链 https://www.acwing.com/problem/content/242/

    int dataRange = (int) 1e5 + 10;
    int[] fatherNode = new int[dataRange];
    int[] childrenNodeNum = new int[dataRange];
    int[] childrenToRootDistance = new int[dataRange];

    public void init() {
        for (int i = 0; i < dataRange; i++) {
            fatherNode[i] = i;
        }
        Arrays.fill(childrenNodeNum, 1);
        Arrays.fill(childrenToRootDistance, 0);
    }

    public int queryForefatherNode(int num) {
        if (fatherNode[num] != num) {
            int tmp = queryForefatherNode(fatherNode[num]);
            childrenToRootDistance[num] += childrenToRootDistance[fatherNode[num]];
            fatherNode[num] = tmp;
        }
        return fatherNode[num];
    }


    public void merge(int x, int y) { // 合并集合、连通块中点的数量
        int forefatherX = queryForefatherNode(x);
        int forefatherY = queryForefatherNode(y);
        if (forefatherY != forefatherX) {
            fatherNode[forefatherX] = forefatherY;
            childrenNodeNum[forefatherY] += childrenNodeNum[forefatherX];
        }
    }

    public void merge(int x, int y, int mode) { // 合并集合、食物链
        int forefatherX = queryForefatherNode(x);
        int forefatherY = queryForefatherNode(y);
        int weight = mode - 1;
        if (forefatherY != forefatherX) {
            fatherNode[forefatherX] = forefatherY;
            childrenToRootDistance[forefatherX] = childrenToRootDistance[y] + weight - childrenToRootDistance[x];
        }
    }


    public int misjudgmentOfFoodChain(int x, int y, int mode) {
        int forefatherX = queryForefatherNode(x);
        int forefatherY = queryForefatherNode(y);
        int weight = mode - 1;
        if (forefatherY == forefatherX && ((childrenToRootDistance[x] - childrenToRootDistance[y] - weight) % 3 != 0)) {
            return 1;
        } else if (forefatherY != forefatherX) {
            merge(x, y, mode);
            return 0;
        } else {
            return 0;
        }
    }

    public boolean determineSetSameOrNot(int x, int y) { // 连通块中点的数量
        return queryForefatherNode(x) == queryForefatherNode(y);
    }

    public int NumberOfRendezvousPoints(int x) { // 连通块中点的数量
        return childrenNodeNum[queryForefatherNode(x)];
    }
}
