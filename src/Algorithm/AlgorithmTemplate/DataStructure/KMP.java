package Algorithm.AlgorithmTemplate.DataStructure;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KMP {
    // KMP字符串 https://www.acwing.com/problem/content/833/
    int[] nextSuperscript = new int[(int) (1e5 + 10)];
    String patten;

    public void gertNextArray(String patternString) {
        Arrays.fill(nextSuperscript, -1);
        int N = patternString.length();
        for (int i = 1, j = -1; i < N; i++) {
            while (j != -1 && patternString.charAt(i) != patternString.charAt(j + 1)) {
                j = nextSuperscript[j];
            }
            if (patternString.charAt(i) == patternString.charAt(j + 1)) {
                j++;
            }
            nextSuperscript[i] = j;
        }
        this.patten = patternString;
    }

    public int[] compare(String string) {
        List<Integer> ans = new ArrayList<Integer>();
        String patternString = this.patten;
        int M = string.length();
        int N = patternString.length();
        for (int i = 0, j = -1; i < M; i++) {
            while (j != -1 && string.charAt(i) != patternString.charAt(j + 1)) {
                j = nextSuperscript[j];
            }
            if (string.charAt(i) == patternString.charAt(j + 1)) {
                j++;
            }
            if (j == N - 1) {
                ans.add(i - N + 1);
                j = nextSuperscript[j];
            }
        }
        int[] out = ans.stream().mapToInt(Integer::valueOf).toArray();
        return out;
    }
}
