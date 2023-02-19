package Algorithm.AlgorithmTemplate.DataStructure;

public class MaximumXorPair {
    // 最大异或对 https://www.acwing.com/problem/content/145/
    int N = (int) (1e5 + 10) * 31;
    int[][] sonNode = new int[N][2];
    int nodePointer = 0;

    public void insert(int num) {
        int rowPointer = 0;
        for (int i = 30; i >= 0; i--) {
            int colPointer = num >> i & 1;
            if (sonNode[rowPointer][colPointer] == 0) {
                sonNode[rowPointer][colPointer] = ++nodePointer;
            }
            rowPointer = sonNode[rowPointer][colPointer];
        }
    }

    public int queryMaximumXorPair(int num) {
        int rowPointer = 0;
        int ans = 0;
        for (int i = 30; i >= 0; i--) {
            int colPointer = num >> i & 1;
            if (sonNode[rowPointer][1 ^ colPointer] == 0) {
                rowPointer = sonNode[rowPointer][colPointer];
            } else {
                ans += (1 << i);
                rowPointer = sonNode[rowPointer][1 ^ colPointer];
            }

        }
        return ans;
    }
}
