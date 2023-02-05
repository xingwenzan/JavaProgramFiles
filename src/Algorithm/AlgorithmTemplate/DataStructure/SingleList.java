package Algorithm.AlgorithmTemplate.DataStructure;

import java.util.Arrays;

public class SingleList {
    // 单链表 https://www.acwing.com/problem/content/828/
    int N = 100010;
    int[] values = new int[N];
    int[] pointers = new int[N];
    int idx = 1;

    public void initialize() {
        Arrays.fill(values, 0);
        Arrays.fill(pointers, 0);
    }

    public void insert(int k, int x) {
        values[idx] = x;
        pointers[idx] = pointers[k];
        pointers[k] = idx;
        idx++;
    }

    public void delete(int k) {
        pointers[k] = pointers[pointers[k]];
    }

    public void out() {
        int i = pointers[0];
        while (i != 0) {
            System.out.printf("%d ", values[i]);
            i = pointers[i];
        }
    }
}
