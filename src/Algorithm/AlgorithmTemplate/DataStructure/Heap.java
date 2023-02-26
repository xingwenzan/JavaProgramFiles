package Algorithm.AlgorithmTemplate.DataStructure;

public class Heap {
    // 堆排序 https://www.acwing.com/problem/content/840/
    // 模拟堆 https://www.acwing.com/problem/content/841/
    int arraySize = (int) (1e5 + 10);
    int[] heapArray = new int[arraySize]; // 0 号位代表堆大小
    int[] positionOfInputElement = new int[arraySize];     // 第 下标 个输入元素在堆中的位置，0 号位代表最后一次是第几个输入
    int[] inputNumberOfHeapElement = new int[arraySize];   // 堆中 下标 位置的元素是第几个输入的

    public void swap(int pointerA, int pointerB) {
        int tmp = positionOfInputElement[inputNumberOfHeapElement[pointerA]];
        positionOfInputElement[inputNumberOfHeapElement[pointerA]] = positionOfInputElement[inputNumberOfHeapElement[pointerB]];
        positionOfInputElement[inputNumberOfHeapElement[pointerB]] = tmp;

        tmp = inputNumberOfHeapElement[pointerA];
        inputNumberOfHeapElement[pointerA] = inputNumberOfHeapElement[pointerB];
        inputNumberOfHeapElement[pointerB] = tmp;

        tmp = heapArray[pointerA];
        heapArray[pointerA] = heapArray[pointerB];
        heapArray[pointerB] = tmp;
    }

    public void down(int pointer) {
        int tmpPointer = pointer;
        if (pointer * 2 <= heapArray[0] && heapArray[pointer * 2] < heapArray[tmpPointer]) {
            tmpPointer = pointer * 2;
        }
        if (pointer * 2 + 1 <= heapArray[0] && heapArray[pointer * 2 + 1] < heapArray[tmpPointer]) {
            tmpPointer = pointer * 2 + 1;
        }
        if (tmpPointer != pointer) {
            swap(pointer, tmpPointer);
            down(tmpPointer);
        }
    }

    public void up(int pointer) {
        while ((pointer >> 1) > 0 && heapArray[pointer] < heapArray[pointer >> 1]) {
            swap(pointer, pointer >> 1);
            pointer = pointer >> 1;
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
        positionOfInputElement[0] = lst.length;
    }

    public void insert(int num) {
        heapArray[0]++;
        positionOfInputElement[0]++;
        positionOfInputElement[positionOfInputElement[0]] = heapArray[0];
        inputNumberOfHeapElement[heapArray[0]] = positionOfInputElement[0];
        heapArray[heapArray[0]] = num;
        up(heapArray[0]);
    }

    public int minValue() {
        return heapArray[1];
    }

    public void deleteMinValue() {
        swap(1, heapArray[0]);
        heapArray[0]--;
        down(1);
    }

    public void deleteValueByPointer(int pointer) {
        swap(pointer, heapArray[0]);
        heapArray[0]--;
        down(pointer);
        up(pointer);
    }

    public void deleteValueByInputNumber(int number) {
        int pointer = positionOfInputElement[number];
        deleteValueByPointer(pointer);
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

    public void updataByInputNumber(int number, int value) {
        int pointer = positionOfInputElement[number];
        updataByPointer(pointer, value);
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
