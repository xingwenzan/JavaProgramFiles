package Algorithm.Other.UninitializedWarning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class UninitializedWarningOfWayTwo {
    static Set<Integer> set = new HashSet<>();
    static int n, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");
        n = Integer.parseInt(strs[0]);
        k = Integer.parseInt(strs[1]);
        int res = 0;
        set.add(0);
        for (int i = 0; i < k; i++) {
            strs = br.readLine().split(" ");
            int l = Integer.parseInt(strs[0]);
            int r = Integer.parseInt(strs[1]);

            if (set.contains(r)) res++;
            set.add(l);
        }

        System.out.println(k - res);
        br.close();
    }

}
