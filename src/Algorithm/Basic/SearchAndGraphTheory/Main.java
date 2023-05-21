package Algorithm.Basic.SearchAndGraphTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = bufferedReader.readLine().split(" ");
        int manNum = Integer.parseInt(strings[0]);
        int womanNum = Integer.parseInt(strings[1]);
        int m = Integer.parseInt(strings[2]);
        HungarianAlgorithm hungarianAlgorithm = new HungarianAlgorithm();
        hungarianAlgorithm.init();
        for (int i = 0; i < m; i++) {
            strings = bufferedReader.readLine().split(" ");
            int start = Integer.parseInt(strings[0]);
            int end = Integer.parseInt(strings[1]);
            hungarianAlgorithm.add(start, end);
        }
        int ans = 0;
        for (int i = 1; i <= manNum; i++) {
            hungarianAlgorithm.refresh();
            if (hungarianAlgorithm.find(i)) {
                ans++;
            }
        }
        System.out.println(ans);


        /*
        // 染色法判定二分图
        String[] strings = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);
        StainingMethod stainingMethod = new StainingMethod();
        stainingMethod.init();
        for (int i = 0; i < m; i++) {
            strings = bufferedReader.readLine().split(" ");
            int start = Integer.parseInt(strings[0]);
            int end = Integer.parseInt(strings[1]);
            stainingMethod.add(start, end);
            stainingMethod.add(end, start);
        }
        boolean judge = stainingMethod.judge(n);
        if (judge) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

         */


        /*
        // Kruskal算法求最小生成树
        String[] strings = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);
        Kruskal kruskal = new Kruskal();
        kruskal.init();
        for (int i = 0; i < m; i++) {
            strings = bufferedReader.readLine().split(" ");
            int start = Integer.parseInt(strings[0]);
            int end = Integer.parseInt(strings[1]);
            int length = Integer.parseInt(strings[2]);
            kruskal.add(start, end, length);
        }
        int out = kruskal.kruskal(n, m);
        System.out.println(out != kruskal.inf ? out : "impossible");

         */


        /*
        // Prim算法求最小生成树
        String[] strings = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);
        Prim prim = new Prim();
        prim.init();
        for (int i = 0; i < m; i++) {
            strings = bufferedReader.readLine().split(" ");
            int start = Integer.parseInt(strings[0]);
            int end = Integer.parseInt(strings[1]);
            int length = Integer.parseInt(strings[2]);
            prim.add(start, end, length);
        }
        int out = prim.prim(n);
        if (out == prim.inf) {
            System.out.println("impossible");
        } else {
            System.out.println(out);
        }

         */


        /*
        // Floyd求最短路
        String[] strings = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);
        int k = Integer.parseInt(strings[2]);
        Floyd floyd = new Floyd();
        floyd.init();
        for (int i = 0; i < m; i++) {
            strings = bufferedReader.readLine().split(" ");
            int start = Integer.parseInt(strings[0]);
            int end = Integer.parseInt(strings[1]);
            int lenght = Integer.parseInt(strings[2]);
            floyd.add(start, end, lenght);
        }
        floyd.floyd(n);
        for (int i = 0; i < k; i++) {
            strings = bufferedReader.readLine().split(" ");
            int start = Integer.parseInt(strings[0]);
            int end = Integer.parseInt(strings[1]);
            int ans = floyd.find(start, end);
            if (ans > 1e9 / 2) {
                System.out.println("impossible");
            } else {
                System.out.println(ans);
            }
        }

         */


        /*
        // 有边数限制的最短路\spfa求最短路\spfa判断负环
        String[] strings = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);
//        int k = Integer.parseInt(strings[2]);
        SPFA spfa = new SPFA(); // spfa求最短路\spfa判断负环
        spfa.init(); // spfa求最短路
//        BellmanFord bellmanFord = new BellmanFord(); // 有边数限制的最短路
//        bellmanFord.init(); // 有边数限制的最短路
        for (int i = 0; i < m; i++) {
            strings = bufferedReader.readLine().split(" ");
            int start = Integer.parseInt(strings[0]);
            int end = Integer.parseInt(strings[1]);
            int lenght = Integer.parseInt(strings[2]);
//            bellmanFord.add(start, end, lenght); // 有边数限制的最短路
            spfa.add(start, end, lenght); // spfa求最短路\spfa判断负环
        }
//        if (bellmanFord.bellmanFord(n, m, k)) { // 有边数限制的最短路
//            System.out.println(bellmanFord.edge(n));
//        if (spfa.spfa(1, n)) { // spfa求最短路
//            System.out.println(spfa.findShortestPath(n));
//        } else { // 有边数限制的最短路\spfa求最短路
//            System.out.println("impossible");
//        }
        if (spfa.JudgmentNegativeRing(1, n)) { // spfa判断负环
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

         */


        /*
        // Dijkstra求最短路 I\II
        String[] strings = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);
        Dijkstra dijkstra = new Dijkstra();
        //dijkstra.initI(); // Dijkstra求最短路 I
        dijkstra.initII(); // Dijkstra求最短路 II
        for (int i = 0; i < m; i++) {
            strings = bufferedReader.readLine().split(" ");
            int start = Integer.parseInt(strings[0]);
            int end = Integer.parseInt(strings[1]);
            int length = Integer.parseInt(strings[2]);
            //dijkstra.addI(start, end, length); // Dijkstra求最短路 I
            dijkstra.addII(start, end, length); // Dijkstra求最短路 II
        }
        //int ans = dijkstra.dijkstraI(n); // Dijkstra求最短路 I
        int ans = dijkstra.dijkstraII(n); // Dijkstra求最短路 II
        System.out.println(ans);

         */


        /*
        // 有向图的拓扑序列
        String[] strings = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);
        TopologicalSort topologicalSort = new TopologicalSort();
        topologicalSort.init();
        for (int i = 0; i < m; i++) {
            strings = bufferedReader.readLine().split(" ");
            int father = Integer.parseInt(strings[0]);
            int son = Integer.parseInt(strings[1]);
            topologicalSort.add(father, son);
        }
        if (topologicalSort.topology(n)) {
            for (int i = 0; i < n; i++) {
                System.out.printf("%d ", topologicalSort.queue[i]);
            }
        } else {
            System.out.println(-1);
        }

         */



        /*
        // 树与图的广度优先遍历 图中点的层次
        String[] strings = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);
        PriorityTraversalOfTreesAndGraphs p = new PriorityTraversalOfTreesAndGraphs();
        p.init();
        for (int i = 0; i < m; i++) {
            strings = bufferedReader.readLine().split(" ");
            int father = Integer.parseInt(strings[0]);
            int son = Integer.parseInt(strings[1]);
            p.add(father, son);
        }
        System.out.println(p.bfs(1, n));

         */

        /*
        // 树与图的深度优先遍历 树的重心
        int n = Integer.parseInt(bufferedReader.readLine());
        PriorityTraversalOfTreesAndGraphs p = new PriorityTraversalOfTreesAndGraphs();
        p.init();
        String[] strings;
        for (int i = 0; i < n - 1; i++) {
            strings = bufferedReader.readLine().split(" ");
            int father = Integer.parseInt(strings[0]);
            int son = Integer.parseInt(strings[1]);
            p.add(father, son);
            p.add(son, father);
        }
        System.out.println(p.centerOfGravityOfTree(n));

         */


        /*
        // BFS - 八数码
        String[] strings = bufferedReader.readLine().split(" ");
        StringBuffer stringBuffer = new StringBuffer();
        for (String s : strings) {
            stringBuffer.append(s);
        }
        String start = stringBuffer.toString();
        BFS bfs = new BFS();
        System.out.println(bfs.eightDigits(start));

         */


        /*
        // BFS - 走迷宫
        String[] strings = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);
        int[][] mazeMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            strings = bufferedReader.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                mazeMap[i][j] = Integer.parseInt(strings[j]);
            }
        }
        BFS bfs = new BFS();
        System.out.println(bfs.walkMaze(mazeMap));

         */


        /*
        // DFS
        int n = Integer.parseInt(bufferedReader.readLine());
        DFS dfs = new DFS();
        //dfs.enumOutput1D(1, n); // 排列数字
        dfs.init2D(n);
        dfs.enumOutput2D(0); // n-皇后问题

         */
    }
}
