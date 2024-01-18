package Algorithm.AdvancedGuide.BasicAlgorithms;

/**
 * 超快速排序
 * <a href="https://www.acwing.com/problem/content/description/109/">...</a>
 */
public class SuperQuickSort {
    private final int length;
    private final int[] lst, tmp;

    public SuperQuickSort(int[] lst, int length) {
        this.length = length;
        this.lst = lst;
        this.tmp = new int[length];
    }

    private long sort(int l, int r) {
        if (l >= r) return 0;
        int mid = (l + r) / 2;
        long ans = sort(l, mid) + sort(mid + 1, r);

        int i = l, j = mid + 1, k = 0;
        while (i <= mid && j <= r) {
            if (lst[i] <= lst[j]) tmp[k++] = lst[i++];
            else {
                tmp[k++] = lst[j++];
                ans += mid - i + 1;
            }
        }
        while (i <= mid) tmp[k++] = lst[i++];
        while (j <= r) tmp[k++] = lst[j++];

        for (int a = l, b = 0; a <= r; a++, b++) lst[a] = tmp[b];

        return ans;
    }

    public long getAns() {
        return sort(0, length - 1);
    }
}
