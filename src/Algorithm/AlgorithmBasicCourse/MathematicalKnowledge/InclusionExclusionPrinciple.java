package Algorithm.AlgorithmBasicCourse.MathematicalKnowledge;

public class InclusionExclusionPrinciple {
    // 容斥原理 能被整除的数 https://www.acwing.com/problem/content/892/
    public long divisibleNumber(int maxNum, int num, int[] lst) {
        long ans = 0;
        for (int i = 1; i < (1 << num); i++) {
            long t = 1, s = 0;   // t: s 个 pi 的乘积; s: 相乘 pi 的个数，根据其奇偶性判断加减
            for (int j = 0; j < num; j++) {
                if ((i >> j & 1) == 1) {
                    t *= lst[j];
                    if (t > maxNum) {
                        t = -1;
                        break;
                    } else {
                        s++;
                    }
                }
            }
            if (t != -1) {
                if (s % 2 != 0) {
                    ans += maxNum / t;
                } else {
                    ans -= maxNum / t;
                }
            }
        }
        return ans;
    }
}
