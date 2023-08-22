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

    /*数字转换
    链接 https://www.acwing.com/problem/content/1077/
    树的最长路径的应用
    树的建立   反向枚举，枚举 i 是哪些数的约数，而非枚举 i 的约数有哪些，以减小复杂度
    树的遍历   一个约数和可以对应多个数，但一个数只会对应一个约数和，且约数和小于每一个对应它的数，故可建立以约数和为父的有向图，从小到大无重复的遍历，获取最长次长路径后相加比较
     */

    /*---------------------** 变量定义部分 **---------------------*/

    // 树的最长路径、树的中心 1e4   数字转换 5e4
    private final int N = (int) 5e4 + 10;
    // 数组模拟邻接表，无向图用 2N，有向图用 N
    private final int[] h = new int[N], e = new int[2 * N], ne = new int[2 * N], w = new int[2 * N];
    private int idx = 0;
    private int ans = 0;   // 树的最长路径、数字转换
    private final TreeCenterClass TC = new TreeCenterClass(N);   // 树的中心
    private DigitalConversionClass DC;   // 数字转换

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
     * 通过 DFS 遍历所有节点获取其最大深度并更新树的直径
     *
     * @param u 起始节点
     * @return u 的最大深度
     * <p>
     * 应用   数字转换
     */
    private int dfs(int u) {
        DC.st[u] = true;
        int dist = 0;   // 表示从当前点往下走的最大长度
        int d1 = 0, d2 = 0;   // 最大长度和次大长度
        for (int i = h[u]; i != -1; i = ne[i]) {
            int son = e[i];
            if (DC.st[son]) continue;
            int d = dfs(son) + 1;
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

    /*---------------------** 外/公用非题目主体函数部分 **---------------------*/

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
     * 无权有向图添加元素
     *
     * @param father 父节点
     * @param son    子节点
     */
    public void add1(int father, int son) {
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

    /**
     * 无权无向图添加元素
     *
     * @param p1 节点1
     * @param p2 节点2
     */
    public void add2(int p1, int p2) {
        add1(p1, p2);
        add1(p2, p1);
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

    public int DigitalConversion(int num) {
        DC = new DigitalConversionClass(num);
        for (int i = 2; i <= num; i++) {   // sum[1] = 0，本题数据无 0，故添加时无须遍历 1
            if (DC.sum[i] < i) {
                add1(DC.sum[i], i);
            }
        }
        for (int i = 1; i <= num; i++) {
            if (!DC.st[i]) {
                dfs(i);
            }
        }

        return ans;
    }

    /*---------------------** 内部类部分 **---------------------*/

    /**
     * 树的中心类
     * <p>
     * downFirst[i]   i 为起点的向下的最大长度
     * downSecond[i]   i 为起点的向下的次大长度
     * sonPoint[i]   i 为起点的向下的最大长度对应的子节点
     * up[i]   i 为起点的向上的最大长度 = 其父节点的不经过自己的最大长度(向上或向下) + 自己到父节点的距离
     * <p>
     * 用途   减少变量部分及变量间冲突
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

    /**
     * 数字转换类
     * <p>
     * 用途   减少变量部分及变量间冲突
     */
    private static class DigitalConversionClass {
        boolean[] st;   // 是否遍历过
        int[] sum;   // sum[i] 为 i 对应的约数和

        public DigitalConversionClass(int num) {
            sum = new int[num + 10];
            st = new boolean[num + 10];
            approximationSum(num);
        }

        private void approximationSum(int num) {
            for (int i = 1; i <= num; i++) {
                for (int j = 2; j <= num / i; j++) {   // j <= num/i 防爆
                    sum[i * j] += i;
                }
            }
        }
    }
}
