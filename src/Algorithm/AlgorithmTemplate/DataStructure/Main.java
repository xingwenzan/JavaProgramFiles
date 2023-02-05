package Algorithm.AlgorithmTemplate.DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * 用于测试数据结构的类
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        SingleList singleList = new SingleList();
        singleList.initialize();
        String[] strings;
        for (int i = 0; i < n; i++) {
            strings = bufferedReader.readLine().split(" ");
            if (Objects.equals(strings[0], "H")) {
                int x = Integer.parseInt(strings[1]);
                singleList.insert(0, x);
            } else if (Objects.equals(strings[0], "D")) {
                int x = Integer.parseInt(strings[1]);
                singleList.delete(x);
            } else {
                int k = Integer.parseInt(strings[1]);
                int x = Integer.parseInt(strings[2]);
                singleList.insert(k, x);
            }
        }
        singleList.out();
    }

}
