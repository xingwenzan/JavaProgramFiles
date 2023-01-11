package Algorithm.AlgorithmTemplate;

// 基础算法模板/快速调用
public class BasicAlgorithms {
    public static void quickSort(int[] lst, int l, int r) {
        // 快速排序，https://www.acwing.com/problem/content/787/
        if (l >= r) {
            return;
        }
        int i = l;
        int j = r;
        int x = lst[(l + r) >> 1];
        while (i < j) {
            while (lst[i] < x) {
                i++;
            }
            while (lst[j] > x) {
                j--;
            }
            if (i < j) {
                int k = lst[i];
                lst[i] = lst[j];
                lst[j] = k;
            }

            quickSort(lst, l, j);
            quickSort(lst, j + 1, r);
        }
    }
}
