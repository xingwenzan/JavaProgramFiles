package Algorithm;

import java.util.Arrays;
import java.util.Scanner;

public class SkillUpgrade {
    // 题目：技能升级；链接：https://www.acwing.com/activity/content/code/content/5169429/
    // 提交结果仍有错误，草
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String delimeter = " ";
        String input1 = scanner.nextLine(); // 读取字符串
        String[] nAndmByStr = input1.split(delimeter); // 字符串分割
        int[] nAndmByInt = Arrays.stream(nAndmByStr)
                .mapToInt(Integer::parseInt)
                .toArray(); // 字符串数组转数字数组


        int[] a = new int[nAndmByInt[0]];
        int[] b = new int[nAndmByInt[0]];
        for (int i = 0; i < nAndmByInt[0]; i++) {
            String inputn = scanner.nextLine(); // 读取字符串
            String[] aAndbByStr = inputn.split(delimeter); // 字符串分割
            int[] aAndmbByInt = Arrays.stream(aAndbByStr)
                    .mapToInt(Integer::parseInt)
                    .toArray(); // 字符串数组转数字数组
            a[i] = aAndmbByInt[0];
            b[i] = aAndmbByInt[1];
        }

        class decide {
            private boolean decide(long mid) {
                long num = 0;
                for (int i = 0; i < nAndmByInt[0]; i++) {
                    if (mid <= a[i]) {
                        num += (a[i] - mid) / b[i] + 1;
                    }
                }
                return num >= nAndmByInt[1];
            }
        }

        long l = 0;
        long r = 100000;

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
        for (int i = 0; i < nAndmByInt[0]; i++) {
            if (r <= a[i]) {
                long c = (a[i] - r) / b[i] + 1;
                long end = a[i] - (c - 1) * b[i];
                cnt += c;
                res += (a[i] + end) * c / 2;
            }
        }

        System.out.println(res - (cnt - nAndmByInt[1]) * r);
    }

}
