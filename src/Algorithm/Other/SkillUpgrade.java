package Algorithm.Other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SkillUpgrade {
    // 题目：技能升级；链接：https://www.acwing.com/activity/content/code/content/5169429/
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);

        int[] a = new int[n];
        int[] b = new int[n];
        int amax = 0;
        for (int i = 0; i < n; i++) {
            s = bufferedReader.readLine().split(" ");
            a[i] = Integer.parseInt(s[0]);
            b[i] = Integer.parseInt(s[1]);
            amax = Math.max(amax, a[i]);
        }

        class decide {
            private boolean decide(long mid) {
                long num = 0;
                for (int i = 0; i < n; i++) {
                    if (mid <= a[i]) {
                        num += (a[i] - mid) / b[i] + 1;
                    }
                }
                return num >= m;
            }
        }

        long l = 0;
        long r = amax;

        while (l < r) {
            long mid = (l + r + 1) / 2;
            decide decide = new decide();
            if (decide.decide(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        long res = 0;
        long cnt = 0;
        for (int i = 0; i < n; i++) {
            if (r <= a[i]) {
                long c = (a[i] - r) / b[i] + 1;
                long end = a[i] - (c - 1) * b[i];
                cnt += c;
                res += (a[i] + end) * c / 2;
            }
        }

        System.out.println(res - (cnt - m) * r);
    }

}
