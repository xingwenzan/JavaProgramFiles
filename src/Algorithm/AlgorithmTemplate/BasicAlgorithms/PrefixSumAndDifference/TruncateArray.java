package Algorithm.AlgorithmTemplate.BasicAlgorithms.PrefixSumAndDifference;

import Algorithm.AlgorithmTemplate.BasicAlgorithms.BasicAlgorithmsTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TruncateArray {
    // 截断数组 https://www.acwing.com/problem/content/3959/
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        String[] strings = bufferedReader.readLine().split(" ");
        int[] lst = BasicAlgorithmsTemplate.prefixSum(strings);
        long cnt = 0, ans = 0;
        if (lst[n] % 3 != 0) {
            System.out.println(0);
        } else {
            for (int i = 2; i < n; i++) {
                if (lst[i - 1] == lst[n] / 3) {
                    cnt++;
                }
                if (lst[i] == lst[n] / 3 * 2) {
                    ans += cnt;
                }
            }
            System.out.println(ans);
        }
    }
}
