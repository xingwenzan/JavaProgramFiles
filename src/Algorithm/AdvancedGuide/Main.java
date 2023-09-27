package Algorithm.AdvancedGuide;

import Algorithm.AdvancedGuide.BasicAlgorithms.PowerABC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // a^b
        String[] strings = bufferedReader.readLine().split(" ");
        int a = Integer.parseInt(strings[0]), b = Integer.parseInt(strings[1]), c = Integer.parseInt(strings[2]);
        PowerABC p = new PowerABC(a, b, c);
        System.out.println(p.getAns());
    }
}
