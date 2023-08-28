package Algorithm.Improve.DynamicProgramming;

import org.jetbrains.annotations.NotNull;

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

    /*二叉苹果树
    链接   https://www.acwing.com/problem/content/1076/
    简化版有依赖背包问题   有依赖的背包问题 https://www.acwing.com/problem/content/10/
    注   本题输入格式中无法确定方向，故使用无向图
     */

    /*皇宫看守
    链接 https://www.acwing.com/problem/content/1079/
    DP 图   https://cdn.acwing.com/media/article/image/2022/10/29/197723_510b605257-Screenshot-2022-10-29-133343.jpg
    由于采用 DFS 从下往上更新，故 dfs(root) 即可
    每个点都只需要赋一次权，故不在 add 里赋权
     */

    /*---------------------** 变量定义部分 **---------------------*/

    // 树的最长路径、树的中心 1e4   数字转换 5e4   二叉苹果树 100   皇宫看守 1500
    private final int N = 1510;
    // 数组模拟邻接表，无向图用 2N，有向图用 N
    private final int[] h = new int[N], e = new int[2 * N], ne = new int[2 * N], w = new int[2 * N];
    private int idx = 0;
    private int ans = 0;   // 树的最长路径、数字转换
    private final int INF = 0x3f3f3f3f;
    private final TreeCenterClass TC = new TreeCenterClass(N);   // 树的中心
    private final PalaceGuardClass PG = new PalaceGuardClass();   // 皇宫看守
    private DigitalConversionClass DC;   // 数字转换
    private BinaryAppleTreeClass BAT;   // 二叉苹果树

    /*---------------------** 私有函数部分 **---------------------*/

    /**
     * 通过 DFS 遍历所有节点获取其最大深度并更新树的直径
     * 应用   树的最长路径
     *
     * @param u      起始/当前节点
     * @param father 父节点
     * @return u 的最大深度
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
     * 应用   数字转换
     *
     * @param u 起始/当前节点
     * @return u 的最大深度
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
     * 通过 DFS 对有依赖的背包问题（分组背包）的 f 的更新
     * 应用   二叉苹果树
     *
     * @param u      起始/当前节点
     * @param father 父节点
     */
    private void dfsDepend(int u, int father) {
        for (int i = h[u]; i != -1; i = ne[i]) {
            int son = e[i];
            if (son == father) continue;
            dfsDepend(son, u);
            for (int j = BAT.V; j > 0; j--) {
                for (int k = 0; k < j; k++) {
                    BAT.f[u][j] = Math.max(BAT.f[u][j], BAT.f[u][j - k - 1] + BAT.f[son][k] + w[i]);
                }
            }
        }
    }

    /**
     * 通过遍历 u 的所有子节点，获取其向下到端点的最大长度和次大长度
     * 应用   树的中心
     *
     * @param u      起始/当前节点
     * @param father 父节点
     * @return u 的向下最大深度
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
     * 应用   树的中心
     *
     * @param u      起始/当前节点
     * @param father 父节点
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

    /**
     * 通过 DFS 遍历更新状态机
     * 应用   皇宫看守
     *
     * @param u 起始/当前节点
     */
    private void dfsState(int u) {
        int sum = 0;
        PG.f[u][2] = w[u];
        for (int i = h[u]; i != -1; i = ne[i]) {
            int son = e[i];
            dfsState(son);
            PG.f[u][0] += Math.min(PG.f[son][1], PG.f[son][2]);
            PG.f[u][2] += Math.min(PG.f[son][0], Math.min(PG.f[son][1], PG.f[son][2]));
            sum += Math.min(PG.f[son][1], PG.f[son][2]);
        }
        PG.f[u][1] = INF;
        for (int i = h[u]; i != -1; i = ne[i]) {
            int son = e[i];
            PG.f[u][1] = Math.min(PG.f[u][1], sum - Math.min(PG.f[son][1], PG.f[son][2]) + PG.f[son][2]);
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

    /**
     * 皇宫看守专用元素添加
     *
     * @param strings 输入数组
     */
    public void addPalace(String @NotNull [] strings) {
        int father = Integer.parseInt(strings[0]);
        w[father] = Integer.parseInt(strings[1]);
        int num = Integer.parseInt(strings[2]);
        if (num > 0) {
            for (int i = 3; i < 3 + num; i++) {
                int son = Integer.parseInt(strings[i]);
                add1(father, son);
                PG.notRoot[son] = true;
            }
        }
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

    public int BinaryAppleTree(int V) {
        BAT = new BinaryAppleTreeClass(V);
        dfsDepend(1, -1);
        return BAT.getMaxAppleNum();
    }

    public int PalaceGuard() {
        int root = PG.getRoot();
        dfsState(root);
        return PG.getPalaceGuard();
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

    /**
     * 二叉苹果树类
     * <p>
     * 用途   减少变量部分及变量间冲突
     */
    private static class BinaryAppleTreeClass {
        int V;   // 剩余树枝数
        int[][] f = new int[110][110];

        public BinaryAppleTreeClass(int V) {
            this.V = V;
        }

        public int getMaxAppleNum() {
            return f[1][V];
        }
    }

    /**
     * 皇宫看守类
     * <p>
     * 用途   减少变量部分及变量间冲突
     */
    private static class PalaceGuardClass {
        boolean[] notRoot = new boolean[1510];   // 不是根，默认否
        int[][] f = new int[1510][3];

        public int getRoot() {
            int x = 1;
            while (notRoot[x]) {
                x++;
            }
            return x;
        }

        public int getPalaceGuard() {
            int root = getRoot();
            return Math.min(f[root][1], f[root][2]);
        }

    }
}
