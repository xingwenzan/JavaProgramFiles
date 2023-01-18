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
            while (lst[++i] < x) ;
            while (lst[--j] > x) ;
//            do {
//                i++;
//            } while (lst[i] < x);
//            do {
//                j--;
//            } while (lst[j] > x);
            if (i < j) {
                int k = lst[i];
                lst[i] = lst[j];
                lst[j] = k;
            }
        }
        quickSort(lst, l, j);
        quickSort(lst, j + 1, r);
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
            while (lst[++i] < x) ;
            while (lst[--j] > x) ;
            if (i < j) {
                int z = lst[i];
                lst[i] = lst[j];
                lst[j] = z;
            }
        }
        if (j - l + 1 >= k) {
            return quickChoose(lst, l, j, k);
        } else {
            return quickChoose(lst, j + 1, r, k - (j - l + 1));
        }
    }

    // 归并排序 0(nlogn) https://www.acwing.com/problem/content/789/
    public static void mergeSort(int[] lst, int l, int r) {
        if (l >= r) return;
        int mid = l + r >> 1;
        mergeSort(lst, l, mid);
        mergeSort(lst, mid + 1, r);

        int i = l, j = mid + 1, k = 0;
        int[] tmp = new int[r - l + 1];

        while (i <= mid && j <= r) {
            if (lst[i] <= lst[j]) {
                tmp[k++] = lst[i++];
            } else {
                tmp[k++] = lst[j++];
            }
        }
        while (i <= mid) {
            tmp[k++] = lst[i++];
        }
        while (j <= r) {
            tmp[k++] = lst[j++];
        }

        for (int a = l, b = 0; a <= r; a++, b++) {
            lst[a] = tmp[b];
        }
    }

    // 逆序对计数-归并应用 0(nlogn) https://www.acwing.com/problem/content/790/
    public static long numberOfReversedPairs(int[] lst, int l, int r) {
        if (l >= r) {
            return 0;
        }
        int mid = l + r >> 1;
        long res = numberOfReversedPairs(lst, l, mid) + numberOfReversedPairs(lst, mid + 1, r);
        int i = l, j = mid + 1, k = 0;
        int[] tmp = new int[r - l + 1];
        while (i <= mid && j <= r) {
            if (lst[i] <= lst[j]) {
                tmp[k++] = lst[i++];
            } else {
                tmp[k++] = lst[j++];
                res += mid - i + 1;
            }
        }
        while (i <= mid) {
            tmp[k++] = lst[i++];
        }
        while (j <= r) {
            tmp[k++] = lst[j++];
        }
        for (int a = l, b = 0; a <= r; a++, b++) {
            lst[a] = tmp[b];
        }
        return res;
    }

    // 二分 O() https://www.acwing.com/problem/content/description/791/
    // 无法调用，思路整理
    // 判断函数
    public static boolean chick(int l, int r) {
        return true;
    }

    // 二分 - 左边为 true 版本，判断右边界
    public static int dichotomizeLeftIsTrue(int l, int r, int[] lst) {
        int mid = l + r + 1 >> 1;
        while (l < r) {
            if (chick(l, r)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }

    // 二分 - 右边为 true 版本，判断左边界
    public static int dichotomizeRightIsTrue(int l, int r, int[] lst) {
        int mid = l + r >> 1;
        while (l < r) {
            if (chick(l, r)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
