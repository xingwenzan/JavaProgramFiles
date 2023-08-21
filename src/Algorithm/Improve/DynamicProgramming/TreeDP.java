package Algorithm.Improve.DynamicProgramming;

import java.util.Arrays;

public class TreeDP {
    /*---------------------** 注释部分 **---------------------*/

    /*树的最长路径
    链接 https://www.acwing.com/problem/content/1074/
    解析 https://blog.csdn.net/weixin_72060925/article/details/128791893
     */

    /*树的中心
    链接 https://www.acwing.com/problem/content/1075/
    备注 本题长度 c>0，无负权边，可 d1、d2 直接初始化为 0，也无须特判叶子节点
     */

    /*---------------------** 变量定义部分 **---------------------*/

    // 树的最长路径、树的中心 1e4
    private final int N = (int) 1e4 + 10;
    // 数组模拟邻接表，无向图用 2N，有向图用 N
    private final int[] h = new int[N], e = new int[2 * N], ne = new int[2 * N], w = new int[2 * N];
    private int idx = 0;
    private int ans = 0;   // 树的最长路径
    private final TreeCenterClass TC = new TreeCenterClass(N);   // 树的中心

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

    /**
     * 通过遍历 u 的所有子节点，获取其向下到端点的最大长度和次大长度
     *
     * @param u      起始节点
     * @param father 父节点
     * @return u 的向下最大深度
     * <p>
     * 应用   树的中心
     */
    private int dfsDown(int u, int father) {
        for (int i = h[u]; i != -1; i = ne[i]) {
            int son = e[i];
            if (son == father) continue;
            int d = dfsDown(son, u) + w[i];
            if (d >= TC.downFirst[u]) {
                TC.downSecond[u] = TC.downFirst[u];
                TC.downFirst[u] = d;
                TC.sonPoint[u] = son;
            } else if (d > TC.downSecond[u]) {
                TC.downSecond[u] = d;
            }
        }
        return TC.downFirst[u];
    }

    /**
     * 通过遍历 u 的所有子节点，获取其子节点向上到端点的最大长度
     *
     * @param u      起始节点
     * @param father 父节点
     *               <p>
     *               应用   树的中心
     */
    private void dfsUp(int u, int father) {
        for (int i = h[u]; i != -1; i = ne[i]) {
            int son = e[i];
            if (son == father) continue;
            if (TC.sonPoint[u] == son) {
                TC.up[son] = Math.max(TC.up[u], TC.downSecond[u]) + w[i];
            } else {
                TC.up[son] = Math.max(TC.up[u], TC.downFirst[u]) + w[i];
            }
            dfsUp(son, u);
        }
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

    public int TreeCenter(int num) {
        TC.length = num;
        dfsDown(1, -1);
        dfsUp(1, -1);
        return TC.getTreeCenter();
    }

    /*---------------------** 内部类部分 **---------------------*/

    /**
     * 树的中心类
     * <p>
     * downFirst[i]   i 为起点的向下的最大长度
     * downSecond[i]   i 为起点的向下的次大长度
     * sonPoint[i]   i 为起点的向下的最大长度对应的子节点
     * up[i]   i 为起点的向上的最大长度 = 其父节点的不经过自己的最大长度(向上或向下) + 自己到父节点的距离
     */
    private static class TreeCenterClass {
        int length;   // 数组大小
        int[] downFirst, downSecond, sonPoint, up;

        public TreeCenterClass(int num) {
            downFirst = new int[num];
            downSecond = new int[num];
            sonPoint = new int[num];
            up = new int[num];
        }

        public int getTreeCenter() {
            int ans = 0x3f3f3f3f;
            for (int i = 1; i <= length; i++) {
                ans = Math.min(ans, Math.max(up[i], downFirst[i]));
            }
            return ans;
        }
    }
}
