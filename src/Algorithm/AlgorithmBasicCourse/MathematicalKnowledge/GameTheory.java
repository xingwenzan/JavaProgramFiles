package Algorithm.AlgorithmBasicCourse.MathematicalKnowledge;

import java.util.ArrayList;
import java.util.Arrays;

public class GameTheory {
    // Nim游戏 https://www.acwing.com/problem/content/893/   a1^a2^……^an = 0 先手必败
    // 台阶-Nim游戏 https://www.acwing.com/problem/content/894/   a1 ^ a3 ^ a5 ^ …… ^ an(n 是奇数) = 0   先手必败
    // 集合-Nim游戏 https://www.acwing.com/problem/content/895/   https://www.acwing.com/video/314/
    // 拆分-Nim游戏 https://www.acwing.com/problem/content/896/

    public boolean NimGame(int[] ints, int length) {   // a1^a2^……^an = 0 先手必败
        int ans = 0;
        for (int i = 0; i < length; i++) {
            ans ^= ints[i];
        }
        return ans != 0;
    }

    public boolean StepsNimGame(int[] ints, int length) {   // a1^a2^……^an = 0 先手必败
        int ans = 0;
        for (int i = 0; i < length; i += 2) {
            ans ^= ints[i];
        }
        return ans != 0;
    }

    private final int NIII = (int) 1e4 + 10, NIV = 110;
    private final int[] sgH = new int[NIII], sgA = new int[NIV];

    public boolean CollectionNimGames(int[] ints, int[] setS) {
        Arrays.fill(sgH, -1);
        int ans = 0;
        for (int i : ints) {
            ans ^= SG(i, setS);
        }
        return ans != 0;
    }

    public boolean SplitNimGame(int[] ints) {
        Arrays.fill(sgA, -1);
        int ans = 0;
        for (int i : ints) {
            ans ^= SG(i);
        }
        return ans != 0;
    }

    public int SG(int x, int[] setS) {
        if (sgH[x] != -1) {
            return sgH[x];
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i : setS) {
            if (x >= i) {
                arrayList.add(SG(x - i, setS));
            }
        }
        for (int i = 0; ; i++) {
            if (!arrayList.contains(i)) {
                return sgH[x] = i;
            }
        }
    }

    public int SG(int x) {
        if (sgA[x] != -1) {
            return sgA[x];
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j <= i; j++) {
                arrayList.add(SG(i) ^ SG(j));
            }
        }
        for (int i = 0; ; i++) {
            if (!arrayList.contains(i)) {
                return sgA[x] = i;
            }
        }
    }
}
