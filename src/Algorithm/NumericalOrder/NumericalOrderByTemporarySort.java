package Algorithm.NumericalOrder;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;

public class NumericalOrderByTemporarySort { //临时排序，现排现用版
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        int m = scanner.nextInt();
        Integer[] w = new Integer[n];
        Integer[] s = new Integer[n];

        for (int i = 0; i < n; i++) {
            w[i] = i + 1;
            s[i] = 0;
            for (int j = i + 1; j > 0; j /= 10) {
                s[i] += j % 10;
            }
        }
        Arrays.sort(w, new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (!Objects.equals(s[o1 - 1], s[o2 - 1])) {
                    if (s[o1 - 1] < s[o2 - 1]) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
                return o1.compareTo(o2);
            }

            @Override
            public boolean equals(Object obj) {
                return false;
            }
        });

        System.out.println(w[m - 1]);
        System.out.println(Arrays.toString(w));
        //System.out.println(Arrays.toString(s));
    }
}
