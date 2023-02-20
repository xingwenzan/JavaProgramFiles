package Algorithm.AlgorithmTemplate.DataStructure;

public class MergeCollection {
    // 合并集合 https://www.acwing.com/problem/content/838/
    int N = (int) 1e5 + 10;
    int[] fatherNode = new int[N];

    public void init() {
        for (int i = 0; i < N; i++) {
            fatherNode[i] = i;
        }
    }

    public int quickForefatherNode(int x) {
        if (fatherNode[x] != x) {
            fatherNode[x] = quickForefatherNode(fatherNode[x]);
        }
        return fatherNode[x];
    }

    public void merge(int x, int y) {
        fatherNode[quickForefatherNode(x)] = quickForefatherNode(y);
    }

    public boolean DetermineSetSameOrNot(int x, int y) {
        return quickForefatherNode(x) == quickForefatherNode(y);
    }
}
