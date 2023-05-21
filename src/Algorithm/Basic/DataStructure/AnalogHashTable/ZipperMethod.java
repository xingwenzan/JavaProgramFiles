package Algorithm.Basic.DataStructure.AnalogHashTable;

import java.util.Arrays;

public class ZipperMethod {
    // 模拟散列表 https://www.acwing.com/problem/content/842/
    // 拉链法
    int N = (int) 1e5 + 3;
    int[] hashTable = new int[N], value = new int[N], nextPointer = new int[N];
    int currentPointer = 0;

    public void init() {
        Arrays.fill(hashTable, -1);
        Arrays.fill(value, 0);
        Arrays.fill(nextPointer, -1);
    }

    public void insert(int num) {
        int position = (num % N + N) % N;
        value[currentPointer] = num;
        nextPointer[currentPointer] = hashTable[position];
        hashTable[position] = currentPointer;
        currentPointer++;
    }

    public boolean query(int num) {
        int position = hashTable[(num % N + N) % N];
        while (position != -1) {
            if (value[position] == num) {
                return true;
            }
            position = nextPointer[position];
        }
        return false;
    }
}
