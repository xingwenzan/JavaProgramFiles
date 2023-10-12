package Algorithm.AdvancedGuide;

import Algorithm.AdvancedGuide.BasicAlgorithms.BitOF64IntegerMultiplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // 64位整数乘法
        long a = Long.parseLong(bufferedReader.readLine()), b = Long.parseLong(bufferedReader.readLine()), c = Long.parseLong(bufferedReader.readLine());
        BitOF64IntegerMultiplication b64 = new BitOF64IntegerMultiplication(a, b, c);
        System.out.println(b64.getAns());

        /*
        // a^b
        String[] strings = bufferedReader.readLine().split(" ");
        int a = Integer.parseInt(strings[0]), b = Integer.parseInt(strings[1]), c = Integer.parseInt(strings[2]);
        PowerABC p = new PowerABC(a, b, c);
        System.out.println(p.getAns());

         */
    }
}
