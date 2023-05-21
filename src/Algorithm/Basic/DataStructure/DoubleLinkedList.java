package Algorithm.Basic.DataStructure;

import java.util.ArrayList;
import java.util.List;

public class DoubleLinkedList {
    // 双链表 https://www.acwing.com/problem/content/829/
    int N = 100011;
    int[] values = new int[N]; // 头、尾节点无值，0 号位为头，1 号位为尾
    int[] leftPointers = new int[N]; // 指向左的指针，指针指向 -1 意为该指针为头节点，不能更左了
    int[] rightPointers = new int[N]; // 指向右的指针，指针指向 -1 意为该指针为尾节点，不能更右了
    int idx = 1;

    public void initialize() {
        leftPointers[0] = -1;
        leftPointers[N - 1] = 0;
        rightPointers[0] = N - 1;
        rightPointers[N - 1] = -1;
    }

    public void add(int k, int x) {
        values[idx] = x;
        leftPointers[idx] = k;
        rightPointers[idx] = rightPointers[k];
        leftPointers[rightPointers[k]] = idx;
        rightPointers[k] = idx;
        idx++;
    }

    public void drop(int k) {
        rightPointers[leftPointers[k]] = rightPointers[k];
        leftPointers[rightPointers[k]] = leftPointers[k];
    }

    public int[] listLTR() { // list left to right
        List<Integer> ans = new ArrayList<Integer>();
        int pointer = rightPointers[0];
        while (rightPointers[pointer] != -1) {
            ans.add(values[pointer]);
            pointer = rightPointers[pointer];
        }
        return ans.stream().mapToInt(Integer::valueOf).toArray();
    }

    public int[] listRTL() { // list right to left
        List<Integer> ans = new ArrayList<Integer>();
        int pointer = leftPointers[N - 1];
        while (leftPointers[pointer] != -1) {
            ans.add(values[pointer]);
            pointer = leftPointers[pointer];
        }
        return ans.stream().mapToInt(Integer::valueOf).toArray();
    }
}
