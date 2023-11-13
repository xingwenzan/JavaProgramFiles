package Algorithm.Improve.Search;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * 八数码
 * <a href="https://www.acwing.com/problem/content/181/">...</a>
 */
public class EightDigits {
    private final String start;

    public EightDigits(String s) {
        this.start = s;
    }

    public String getAns() {
        // 逆序对奇数 - 无解
        if (count() % 2 == 0) {
            return bfs();
        }
        return "unsolvable";
    }

    // 逆序对数量
    private int count() {
        int ans = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (start.charAt(i) == 'x' || start.charAt(j) == 'x') continue;
                if (start.charAt(i) - '1' > start.charAt(j) - '1') ans++;
            }
        }
        return ans;
    }

    private String bfs() {
        int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
        String[] strings = {"u", "l", "d", "r"};

        HashMap<String, Integer> dist = new HashMap<>();
        HashMap<String, String> op = new HashMap<>();
        PriorityQueue<Node> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o.d));

        dist.put(start, 0);
        op.put(start, "");
        heap.add(new Node(start, f(start)));

        while (!heap.isEmpty()) {
            Node cur = heap.poll();
            String end = "12345678x";
            if (Objects.equals(cur.s, end)) return op.get(end);
            int d = dist.get(cur.s);

            int tmp = cur.s.indexOf('x');
            int x = tmp / 3, y = tmp % 3;
            for (int i = 0; i < 4; i++) {
                int tx = x + dx[i], ty = y + dy[i];
                if (tx < 0 || ty < 0 || tx >= 3 || ty >= 3) continue;
                String son = swap(cur.s, tmp, tx * 3 + ty);
                if (!dist.containsKey(son) || dist.get(son) > d + 1) {
                    dist.put(son, d + 1);
                    op.put(son, op.get(cur.s) + strings[i]);
                    heap.add(new Node(son, dist.get(son) + f(son)));
                }
            }

        }

        return "unsolvable";
    }

    private String swap(String s, int a, int b) {
        char[] tmp = s.toCharArray();
        char c = tmp[a];
        tmp[a] = tmp[b];
        tmp[b] = c;
        return String.valueOf(tmp);
    }

    // 估价函数 - 曼哈顿距离
    private int f(String s) {
        int ans = 0;
        for (int i = 0; i < 9; i++) {
            if (s.charAt(i) == 'x') continue;
            int x = i / 3, y = i % 3;
            int cx = (s.charAt(i) - '1') / 3, cy = (s.charAt(i) - '1') % 3;
            ans += Math.abs(x - cx) + Math.abs(y - cy);
        }
        return ans;
    }


    public static class Node {
        private final String s;
        private final int d;

        public Node(String s, int d) {
            this.s = s;
            this.d = d;
        }
    }
}
