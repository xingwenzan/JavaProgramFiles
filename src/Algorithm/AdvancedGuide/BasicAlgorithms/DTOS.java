package Algorithm.AdvancedGuide.BasicAlgorithms;

import java.util.Objects;

/**
 * 起床困难综合症
 * <a href="https://www.acwing.com/problem/content/1000/">...</a>
 */
public class DTOS {
    private final int range, opNum;
    private final String[] op;
    private final int[] num;

    private int attack = 0, hurt = 0;

    public DTOS(int opNum, int range, String[] op, int[] num) {
        this.opNum = opNum;
        this.range = range;
        this.op = op;
        this.num = num;
        countAns();
    }

    private int OP(int x, int bit) {
        for (int i = 0; i < opNum; i++) {
            String s = op[i];
            int t = num[i];
            if (Objects.equals(s, "AND")) {
                x &= (t >> bit) & 1;
            } else if (Objects.equals(s, "OR")) {
                x |= (t >> bit) & 1;
            } else if (Objects.equals(s, "XOR")) {
                x ^= (t >> bit) & 1;
            }
        }
        return x;
    }

    private void countAns() {
        for (int i = 29; i >= 0; i--) {
            int x1 = OP(1, i), x0 = OP(0, i);
            if (attack + (1 << i) <= range && x1 > x0) {
                attack += (1 << i);
                hurt += (x1 << i);
            } else {
                hurt += (x0 << i);
            }
        }
    }

    public int getHurt() {
        return hurt;
    }
}