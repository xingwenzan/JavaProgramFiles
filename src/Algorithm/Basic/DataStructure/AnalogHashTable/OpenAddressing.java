package Algorithm.Basic.DataStructure.AnalogHashTable;

import java.util.Arrays;

public class OpenAddressing {
    // 模拟散列表 https://www.acwing.com/problem/content/842/
    // 开放寻址法
    int N = 2 * ((int) 1e5) + 3;
    int[] hashTable = new int[N];
    int fullNum = (int) (1e9 + 10);

    public void init() {
        Arrays.fill(hashTable, fullNum);
    }

    public int queryPosition(int num) {
        int position = (num % N + N) % N;
        while (hashTable[position] != fullNum && hashTable[position] != num) {
            position++;
            if (position == N) {
                position = 0;
            }
        }
        return position;
    }

    public void insert(int num) {
        hashTable[queryPosition(num)] = num;
    }

    public boolean queryExist(int num) {
        int position = queryPosition(num);
        if (hashTable[position] == num) {
            return true;
        } else {
            return false;
        }
    }
}
