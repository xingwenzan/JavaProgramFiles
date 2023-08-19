package Algorithm.Improve.DynamicProgramming;

import java.util.Arrays;

public class TreeDP {
    /*---------------------** 注释部分 **---------------------*/

    // 树的最长路径 https://www.acwing.com/problem/content/1074/

    /*树的最长路径
    解析 https://blog.csdn.net/weixin_72060925/article/details/128791893
     */

    /*---------------------** 变量定义部分 **---------------------*/

    // 树的最长路径 1e4
    private final int N = (int) 1e4 + 10;
    // 数组模拟邻接表，无向图用 2N，有向图用 N
    private final int[] h = new int[N], e = new int[2 * N], ne = new int[2 * N], w = new int[2 * N];
    private int idx = 0;
    private int ans = 0;   // 树的最长路径

    /*---------------------** 私有函数部分 **---------------------*/

    /**
     * 通过 DFS 遍历所有节点获取其最大深度并更新树的直径
     *
     * @param u      起始节点
     * @param father 父节点
     * @return u 的最大深度
     * <p>
     * 应用   树的最长路径
     */
    private int dfs(int u, int father) {
        int dist = 0;   // 表示从当前点往下走的最大长度
        int d1 = 0, d2 = 0;   // 最大长度和次大长度
        for (int i = h[u]; i != -1; i = ne[i]) {
            int son = e[i];
            if (son == father) continue;
            int d = dfs(son, u) + w[i];
            dist = Math.max(dist, d);
            if (d >= d1) {
                d2 = d1;
                d1 = d;
            } else if (d > d2) {
                d2 = d;
            }
            ans = Math.max(ans, d1 + d2);
        }
        return dist;
    }

    /*---------------------** 外用非题目主体函数部分 **---------------------*/

    /**
     * 树/图初始化
     */
    public void treeInit() {
        Arrays.fill(h, -1);
    }

    /**
     * 有权有向图添加元素
     *
     * @param father 父节点
     * @param son    子节点
     * @param length 权重/距离
     */
    public void add1(int father, int son, int length) {
        w[idx] = length;
        e[idx] = son;
        ne[idx] = h[father];
        h[father] = idx++;
    }

    /**
     * 有权无向图添加元素
     *
     * @param p1     节点1
     * @param p2     节点2
     * @param length 权重/距离
     */
    public void add2(int p1, int p2, int length) {
        add1(p1, p2, length);
        add1(p2, p1, length);
    }

    /*---------------------** 题目主体函数部分 **---------------------*/

    public int TreeLongestPath() {
        dfs(1, -1);
        return ans;
    }
}
