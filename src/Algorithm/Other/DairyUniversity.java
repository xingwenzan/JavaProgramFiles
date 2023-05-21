package Algorithm.Other;

import java.util.Arrays;
import java.util.Scanner;

public class DairyUniversity {
    // 题目: 奶牛大学; 链接: https://www.acwing.com/problem/content/4821/
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); // 输入读取器
        int totleNum = scan.nextInt(); // 读取数字
        scan.nextLine(); // 防止下一行读不到东西，https://blog.csdn.net/u014584274/article/details/75089058
        String price = scan.nextLine(); // 读取字符串
        String delimeter = " "; // 分隔符设为空格（使用空格分割）
        String[] maxPriceOfEveryDairyByStr = price.split(delimeter); // 字符串分割
        int[] maxPriceOfEveryDairyByInt = Arrays.stream(maxPriceOfEveryDairyByStr)
                .mapToInt(Integer::parseInt)
                .toArray(); // 字符串数组转数字数组
        Arrays.sort(maxPriceOfEveryDairyByInt); // 数组排序 - 升序
        long minSingleMoney = 0;
        long maxTotalMoney = 0;
        for (int i = 0; i < totleNum; i++) {
            long ans = (long) maxPriceOfEveryDairyByInt[i] * (totleNum - i);
            if (ans > maxTotalMoney) {
                maxTotalMoney = ans;
                minSingleMoney = maxPriceOfEveryDairyByInt[i];
            }
        }
//        System.out.print(maxTotalMoney);
//        System.out.print(" ");
//        System.out.print(minSingleMoney);
        System.out.printf("%d %d\n", maxTotalMoney, minSingleMoney);

    }
}
