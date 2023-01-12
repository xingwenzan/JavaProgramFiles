package Algorithm.AlgorithmTemplate;

// 基础算法模板/快速调用   // 未调好
public class BasicAlgorithms {

    // 快速排序 O(nlogn) https://www.acwing.com/problem/content/787/
    public static void quickSort(int[] lst, int l, int r) {
        if (l >= r) {
            return;
        }
        int i = l - 1;
        int j = r + 1;
        int x = lst[(l + r) >> 1];
        while (i < j) {
            do {
                i++;
            } while (lst[i] < x);
            do {
                j--;
            } while (lst[j] > x);
            if (i < j) {
                int k = lst[i];
                lst[i] = lst[j];
                lst[j] = k;
            }

            quickSort(lst, l, j);
            quickSort(lst, j + 1, r);
        }
    }

    // 快速选择 O(n) https://www.acwing.com/problem/content/description/788/
    public static int quickChoose(int[] lst, int l, int r, int k) {
        if (l >= r) {
            return lst[l];
        }
        int i = l - 1;
        int j = r + 1;
        int x = lst[(l + r) >> 1];
        while (i < j) {
            do {
                i++;
            } while (lst[i] < x);
            do {
                j--;
            } while (lst[j] > x);
            if (i < j) {
                int z = lst[i];
                lst[i] = lst[j];
                lst[j] = z;
            }

            if (j - l + 1 >= k) {
                return quickChoose(lst, l, j, k);
            } else {
                return quickChoose(lst, j + 1, r, k - (j - l + 1));
            }
        }
        return 0;
    }
}
