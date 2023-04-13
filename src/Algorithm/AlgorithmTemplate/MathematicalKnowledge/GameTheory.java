package Algorithm.AlgorithmTemplate.MathematicalKnowledge;

public class GameTheory {
    // Nim游戏 https://www.acwing.com/problem/content/893/   a1^a2^……^an = 0 先手必败

    public boolean NimGame(int[] ints, int length) {   // a1^a2^……^an = 0 先手必败
        int ans = 0;
        for (int i = 0; i < length; i++) {
            ans ^= ints[i];
        }
        return ans != 0;
    }
}
