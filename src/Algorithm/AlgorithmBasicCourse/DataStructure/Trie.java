package Algorithm.AlgorithmBasicCourse.DataStructure;

public class Trie {
    int N = (int) (1e5 + 10);// 代表所有输入的字符串总长度，极限情况为所有输入字符串每个位置的字母都不一样，即代表树的节点总数
    int[][] sonNode = new int[N][26];
    int[] nodeNum = new int[N];
    int nodePointer = 0;

    public void insert(String str) {
        int rowPointer = 0;
        for (int i = 0; i < str.length(); i++) {
            int colPointer = (int) str.charAt(i) - (int) 'a';
            if (sonNode[rowPointer][colPointer] == 0) {
                sonNode[rowPointer][colPointer] = ++nodePointer;
            }
            rowPointer = sonNode[rowPointer][colPointer];
        }
        nodeNum[rowPointer]++;
    }

    public int query(String str) {
        int rowPointer = 0;
        for (int i = 0; i < str.length(); i++) {
            int colPointer = (int) str.charAt(i) - (int) 'a';
            if (sonNode[rowPointer][colPointer] == 0) {
                return 0;
            }
            rowPointer = sonNode[rowPointer][colPointer];
        }
        return nodeNum[rowPointer];
    }
}
