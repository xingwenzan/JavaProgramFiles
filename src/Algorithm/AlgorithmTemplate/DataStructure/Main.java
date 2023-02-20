package Algorithm.AlgorithmTemplate.DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 用于测试数据结构的类
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        // 合并集合
        String[] strings = bufferedReader.readLine().split(" ");
        int m = Integer.parseInt(strings[1]);
        MergeCollection mergeCollection = new MergeCollection();
        mergeCollection.init();
        for (int i = 0; i < m; i++) {
            strings = bufferedReader.readLine().split(" ");
            int x = Integer.parseInt(strings[1]);
            int y = Integer.parseInt(strings[2]);
            if (strings[0].equals("M")) {
                mergeCollection.merge(x, y);
            } else {
                if (mergeCollection.DetermineSetSameOrNot(x, y)) {
                    System.out.println("Yes");
                } else {
                    System.out.println("No");
                }
            }
        }

        /*
        // 最大异或对
        int num = Integer.parseInt(bufferedReader.readLine());
        String[] strings = bufferedReader.readLine().split(" ");
        MaximumXorPair maximumXorPair = new MaximumXorPair();
        for (int i = 0; i < num; i++) {
            maximumXorPair.insert(Integer.parseInt(strings[i]));
        }
        int out = 0;
        for (int i = 0; i < num; i++) {
            out = Math.max(out, maximumXorPair.queryMaximumXorPair(Integer.parseInt(strings[i])));
        }
        System.out.println(out);

         */


        /*
        // Trie字符串统计
        Trie trie = new Trie();
        for (int i = 0; i < num; i++) {
            String[] strings = bufferedReader.readLine().split(" ");
            if (Objects.equals(strings[0], "I")) {
                trie.insert(strings[1]);
            } else {
                System.out.println(trie.query(strings[1]));
            }
        }

         */


        /*
        // KMP字符串
        KMP kmp = new KMP();
        int N = Integer.parseInt(bufferedReader.readLine());
        String P = bufferedReader.readLine();
        int M = Integer.parseInt(bufferedReader.readLine());
        String S = bufferedReader.readLine();
        kmp.gertNextArray(P);
        int[] ans = kmp.compare(S);
        for (int i = 0; i < ans.length; i++) {
            //System.out.printf("%d ",ans[i]);
            bufferedWriter.write(ans[i] + " ");
        }
        bufferedWriter.flush();

         */

        /*
        // 滑动窗口
        String[] nk = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);
        SlidingWindow slidingWindow = new SlidingWindow();
        String[] strings = bufferedReader.readLine().split(" ");
        int[] lst = new int[n];
        for (int i = 0; i < n; i++) {
            lst[i] = Integer.parseInt(strings[i]);
        }
        for (int i = 0; i < n; i++) {
            slidingWindow.windowMonotonicPush(lst, i, true, k);
            if (i >= k - 1) {
                System.out.printf("%d ", lst[slidingWindow.queryHead()]);
            }
        }
        slidingWindow.init();
        System.out.println();
        for (int i = 0; i < n; i++) {
            slidingWindow.windowMonotonicPush(lst, i, false, k);
            if (i >= k - 1) {
                System.out.printf("%d ", lst[slidingWindow.queryHead()]);
            }
        }

         */


        /*
        // 单调栈
        int N = Integer.parseInt(bufferedReader.readLine());
        String[] lst = bufferedReader.readLine().split(" ");
        MonotonicStack monotonicStack = new MonotonicStack();
        for (int i = 0; i < N; i++) {
            System.out.printf("%d ", monotonicStack.monotonicPush(Integer.parseInt(lst[i])));
        }

         */


        /*
        // 模拟队列
        int M = Integer.parseInt(bufferedReader.readLine());
        Queue queue = new Queue();
        String[] strings;
        for (int i = 0; i < M; i++) {
            strings = bufferedReader.readLine().split(" ");
            if (Objects.equals(strings[0], "push")) {
                int x = Integer.parseInt(strings[1]);
                queue.push(x);
            } else if (Objects.equals(strings[0], "pop")) {
                queue.pop();
            } else if (Objects.equals(strings[0], "empty")) {
                if (queue.empty()) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            } else {
                System.out.println(queue.query());
            }
        }

         */


        /*
        // 表达式求值
        String s = bufferedReader.readLine();
        SpecialStack specialStack = new SpecialStack();
        HashMap<Character, Integer> priority = new HashMap<Character, Integer>();
        priority.put('(', 0);
        priority.put('+', 1);
        priority.put('-', 1);
        priority.put('*', 2);
        priority.put('/', 2);
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                int j = i;
                int num = 0;
                while (j < s.length() && Character.isDigit(s.charAt(j))) {
                    num = num * 10 + (int) s.charAt(j++) - 48;
                }
                i = j - 1;
                specialStack.intStack.push(num);
            } else if (s.charAt(i) == '(') {
                specialStack.signStack.push(s.charAt(i));
            } else if (s.charAt(i) == ')') {
                while (specialStack.signStack.query() != '(') {
                    specialStack.eval();
                }
                specialStack.signStack.pop();
            } else {
                while ((!specialStack.signStack.empty()) && priority.get(specialStack.signStack.query()) >= priority.get(s.charAt(i))) {
                    specialStack.eval();
                }
                specialStack.signStack.push(s.charAt(i));
            }
        }
        while (!specialStack.signStack.empty()) {
            specialStack.eval();
        }
        System.out.println(specialStack.intStack.query());

         */


        /*
        // 模拟栈
        Stack stack = new Stack();
        String[] strings;
        for (int i = 0; i < M; i++) {
            strings = bufferedReader.readLine().split(" ");
            if (Objects.equals(strings[0], "push")) {
                int x = Integer.parseInt(strings[1]);
                stack.push(x);
            } else if (Objects.equals(strings[0], "pop")) {
                stack.pop();
            } else if (Objects.equals(strings[0], "empty")) {
                if (stack.empty()) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            } else {
                System.out.println(stack.query());
            }
        }

         */


        /*
        // 双链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.initialize();
        String[] strings;
        for (int i = 0; i < M; i++) {
            strings = bufferedReader.readLine().split(" ");
            if (Objects.equals(strings[0], "L")) {
                int k = 0;
                int x = Integer.parseInt(strings[1]);
                doubleLinkedList.add(k, x);
            } else if (Objects.equals(strings[0], "R")) {
                int k = doubleLinkedList.leftPointers[doubleLinkedList.N - 1];
                int x = Integer.parseInt(strings[1]);
                doubleLinkedList.add(k, x);
            } else if (Objects.equals(strings[0], "IR")) {
                int k = Integer.parseInt(strings[1]);
                int x = Integer.parseInt(strings[2]);
                doubleLinkedList.add(k, x);
            } else if (Objects.equals(strings[0], "IL")) {
                int k = doubleLinkedList.leftPointers[Integer.parseInt(strings[1])];
                int x = Integer.parseInt(strings[2]);
                doubleLinkedList.add(k, x);
            } else {
                int k = Integer.parseInt(strings[1]);
                doubleLinkedList.drop(k);
            }
        }

        int[] ans = doubleLinkedList.listLTR();
        for (int an : ans) {
            System.out.printf("%d ", an);
        }

         */


        /*
        // 单链表
        SingleList singleList = new SingleList();
        singleList.initialize();
        String[] strings;
        for (int i = 0; i < n; i++) {
            strings = bufferedReader.readLine().split(" ");
            if (Objects.equals(strings[0], "H")) {
                int x = Integer.parseInt(strings[1]);
                singleList.insert(0, x);
            } else if (Objects.equals(strings[0], "D")) {
                int x = Integer.parseInt(strings[1]);
                singleList.delete(x);
            } else {
                int k = Integer.parseInt(strings[1]);
                int x = Integer.parseInt(strings[2]);
                singleList.insert(k, x);
            }
        }
        int[] ans = singleList.listOut();
        for (int i=0;i<ans.length;i++){
            System.out.printf("%d ",ans[i]);
        }

         */
    }

}