package Algorithm.AdvancedGuide.BasicAlgorithms;

/**
 * 奇数码问题
 * <a href="https://www.acwing.com/problem/content/110/">...</a>
 */
public class OddNumbers {
    private final int n;
    private final int[] A, B, tmp = new int[510 * 510];

    public OddNumbers(int n, int[] a, int[] b) {
        this.n = n;
        A = a;
        B = b;
    }

    private int sort(int l, int r, int[] lst) {
        if (l >= r) return 0;
        int mid = l + r >> 1;
        int ans = sort(l, mid, lst) + sort(mid + 1, r, lst);

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

    public boolean judge() {
        return (sort(0, n * n - 1, A) & 1) == (sort(0, n * n - 1, B) & 1);
    }
}
