package Algorithm.AlgorithmTemplate;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

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


    // 高精度算法
    //判断大小
    public static boolean ratioOfHighPrecisionAlgorithm(@NotNull String a, @NotNull String b) {
        if (a.length() != b.length()) {
            return a.length() > b.length();
        }
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                return a.charAt(i) > b.charAt(i);
            }
        }
        return true;
    }


    // 高精度算法
    // 高精度加法 O() https://www.acwing.com/problem/content/793/
    public static String highPrecisionAddition(@NotNull String A, @NotNull String B) {
        if (A.length() < B.length()) {
            return highPrecisionAddition(B, A);
        }
        String a = new StringBuffer(A).reverse().toString();
        String b = new StringBuffer(B).reverse().toString();
        String[] c = new String[a.length() + 1];
        int t = 0;
        for (int i = 0; i < b.length(); i++) {
            t += (int) a.charAt(i) + (int) b.charAt(i) - 48 * 2; // String 直接转 int 是 ASCII 码
            c[i] = String.valueOf(t % 10);
            t /= 10;
        }
        if (a.length() > b.length()) {
            for (int i = b.length(); i < a.length(); i++) {
                t += (int) a.charAt(i) - 48;
                c[i] = String.valueOf(t % 10);
                t /= 10;
            }
        }
        if (t != 0) {
            c[a.length()] = String.valueOf(t);
        }
//        Collections.reverse(Collections.singletonList(c));
//        String ans = String.join("",c);
        String ans = "";
        for (int i = c.length - 1; i >= 0; i--) {
            if (c[i] != null) {
                ans += c[i];
            }
        }
        return ans;
    }


    // 高精度算法
    // 高精度减法 O() https://www.acwing.com/problem/content/description/794/
    public static @NotNull String highPrecisionSubtraction(String A, String B) {
        if (!ratioOfHighPrecisionAlgorithm(A, B)) {
            return "-" + highPrecisionSubtraction(B, A);
        }
        String a = new StringBuffer(A).reverse().toString();
        String b = new StringBuffer(B).reverse().toString();
        String[] c = new String[a.length()];
        int t = 0;
        for (int i = 0; i < b.length(); i++) {
            if ((int) a.charAt(i) - 48 - t >= (int) b.charAt(i) - 48) {
                c[i] = String.valueOf((int) a.charAt(i) - (int) b.charAt(i) - t);
                t = 0;
            } else {
                c[i] = String.valueOf((int) a.charAt(i) - (int) b.charAt(i) - t + 10);
                t = 1;
            }
        }
        if (a.length() > b.length()) {
            for (int i = b.length(); i < a.length(); i++) {
                if ((int) a.charAt(i) - 48 - t >= 0) {
                    c[i] = String.valueOf((int) a.charAt(i) - 48 - t);
                    t = 0;
                } else {
                    c[i] = String.valueOf((int) a.charAt(i) - 48 - t + 10);
                    t = 1;
                }
            }
        }
//        Collections.reverse(Collections.singletonList(c));
//        String ans = String.join("",c);
        StringBuilder ans = new StringBuilder();
        int n = c.length - 1;
        for (int i = c.length - 1; i >= 0; i--) {
            if (c[i].equals("0")) {
                n = i;
            } else {
                n = i;
                break;
            }
        }
        for (int i = n; i >= 0; i--) {
            ans.append(c[i]);
        }
        return ans.toString();
    }


    // 高精度算法
    // 高精度乘法 O() https://www.acwing.com/problem/content/795/
    public static String highPrecisionMultiplication(@NotNull String A, @NotNull String B) {
        if (A.length() < B.length()) {
            return highPrecisionMultiplication(B, A);
        }
        String a = new StringBuffer(A).reverse().toString();
        String b = new StringBuffer(B).reverse().toString();
        String[] c = new String[a.length() + b.length()];
        Arrays.fill(c, "0");
        for (int i = 0; i < b.length(); i++) {
            int t = 0;
            for (int j = 0; j < a.length(); j++) {
                t += ((int) a.charAt(j) - 48) * ((int) b.charAt(i) - 48) + Integer.parseInt(c[i + j]); // String 直接转 int 是 ASCII 码
                c[i + j] = String.valueOf(t % 10);
                t /= 10;
            }
            t += Integer.parseInt(c[i + a.length()]);
            c[i + a.length()] = String.valueOf(t);
        }

        StringBuilder ans = new StringBuilder();
        int n = c.length - 1;
        for (int i = c.length - 1; i >= 0; i--) {
            if (c[i].equals("0")) {
                n = i;
            } else {
                n = i;
                break;
            }
        }
        for (int i = n; i >= 0; i--) {
            ans.append(c[i]);
        }
        return ans.toString();
    }


    // 高精度算法
    // 高精度除法 O() https://www.acwing.com/problem/content/796/
    public static String[] highPrecisionDivision(String A, String B) {
        String a = A;
        int b = Integer.parseInt(B);
        String[] c = new String[a.length()];
        int t = 0;
        for (int i = 0; i < a.length(); i++) {
            int tmp = (int) a.charAt(i) - 48 + t * 10;
            c[i] = String.valueOf(tmp / b);
            t = tmp % b;
        }
        StringBuilder ans = new StringBuilder();
        int n = c.length - 1;
        for (int i = 0; i < c.length; i++) {
            if (c[i].equals("0")) {
                n = i;
            } else {
                n = i;
                break;
            }
        }
        for (int i = n; i < c.length; i++) {
            ans.append(c[i]);
        }
        String[] k = {ans.toString(), String.valueOf(t)};
        return k;
    }
}
