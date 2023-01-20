package Algorithm.OtherProblemSolving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Decrypt {
    // 题目：解密；链接：https://www.acwing.com/problem/content/4732/
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(bufferedReader.readLine());
        while (k-- > 0) {
            String[] strings = bufferedReader.readLine().split(" ");
            long n = Long.parseLong(strings[0]);
            long d = Long.parseLong(strings[1]);
            long e = Long.parseLong(strings[2]);
            long m = n - e * d + 2;
            long tmp1 = m * m - 4 * n;

            long tmp2 = (long) Math.sqrt(tmp1);
            if (tmp2 * tmp2 != tmp1) {
                System.out.println("NO");
            } else {
                long p = m - tmp2 >> 1;
                long q = m + tmp2 >> 1;
                System.out.printf("%d %d\n", p, q);
            }


//            if (tmp1<0){
//                System.out.println("NO");
//                continue;
//            }
//            long tmp2 = (long) Math.sqrt(tmp1);
//            if (tmp2*tmp2 != tmp1){
//                System.out.println("NO");
//                continue;
//            }
//            long p = m-tmp2>>1;
//            long q = m+tmp2>>1;
//            System.out.printf("%d %d\n",p,q);
        }

    }
}
