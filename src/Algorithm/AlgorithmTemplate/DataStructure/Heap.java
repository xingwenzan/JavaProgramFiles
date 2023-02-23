package Algorithm.AlgorithmTemplate.DataStructure;

public class Heap {
    // 堆排序 https://www.acwing.com/problem/content/840/
    int[] heapArray = new int[(int) (1e5 + 10)];

    public void down(int pointer) {
        int tmpPointer = pointer;
        if (pointer * 2 <= heapArray[0] && heapArray[pointer * 2] < heapArray[tmpPointer]) {
            tmpPointer = pointer * 2;
        }
        if (pointer * 2 + 1 <= heapArray[0] && heapArray[pointer * 2 + 1] < heapArray[tmpPointer]) {
            tmpPointer = pointer * 2 + 1;
        }
        if (tmpPointer != pointer) {
            int tmp = heapArray[pointer];
            heapArray[pointer] = heapArray[tmpPointer];
            heapArray[tmpPointer] = tmp;
            down(tmpPointer);
        }
    }

    public void up(int pointer) {
        int tmpPointer = pointer >> 1;
        if (tmpPointer > 0 && heapArray[pointer] < heapArray[tmpPointer]) {
            int tmp = heapArray[pointer];
            heapArray[pointer] = heapArray[tmpPointer];
            heapArray[tmpPointer] = tmp;
            up(tmpPointer);
        }
    }

    public void init(String[] lst) {
        heapArray[0] = lst.length;
        for (int i = 1; i <= heapArray[0]; i++) {
            heapArray[i] = Integer.parseInt(lst[i - 1]);
        }
        for (int i = heapArray[0] / 2; i > 0; i--) {
            down(i);
        }
    }

    public void insert(int num) {
        heapArray[++heapArray[0]] = num;
        up(heapArray[0]);
    }

    public int minValue() {
        return heapArray[1];
    }

    public void deleteMinValue() {
        heapArray[1] = heapArray[heapArray[0]--];
        down(1);
    }

    public void deleteValueByPointer(int pointer) {
        heapArray[pointer] = heapArray[heapArray[0]--];
        down(pointer);
    }

    public void deleteValueByValue(int value) {
        int pointer = 0;
        for (int i = 1; i <= heapArray[0]; i++) {
            if (heapArray[i] == value) {
                pointer = i;
                break;
            }
        }
        deleteValueByPointer(pointer);
    }

    public void updataByPointer(int pointer, int value) {
        heapArray[pointer] = value;
        down(pointer);
        up(pointer);
    }

    public void updataByValue(int oldValue, int newValue, int changeNum) {
        int num = 0;
        for (int i = 1; i <= heapArray[0]; i++) {
            if (heapArray[i] == oldValue) {
                num++;
                int pointer = i;
                updataByPointer(pointer, newValue);
            }
            if (num == changeNum) {
                break;
            }
        }
    }
}
