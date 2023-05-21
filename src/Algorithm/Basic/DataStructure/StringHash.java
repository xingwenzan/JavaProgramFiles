package Algorithm.Basic.DataStructure;

public class StringHash {
    // 字符串哈希 https://www.acwing.com/problem/content/843/
    // 字符串前缀哈希法
    // 假定人品够好，没有冲突（一般取 131或13331进制，用 2**64 取模）
    int N = (int) (1e5 + 10);
    int hexadecimal = 131;
    long[] hexadecimalPower = new long[N], stringValue = new long[N];

    public void init(String s) {
        hexadecimalPower[0] = 1;
        for (int i = 1; i <= s.length(); i++) {
            hexadecimalPower[i] = hexadecimalPower[i - 1] * hexadecimal;
            stringValue[i] = stringValue[i - 1] * hexadecimal + s.charAt(i - 1);
        }
    }

    public long stringToHashValue(int l, int r) {
        return stringValue[r] - stringValue[l - 1] * hexadecimalPower[r - l + 1];
    }
}
