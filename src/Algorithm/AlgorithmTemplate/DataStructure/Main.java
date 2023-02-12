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
        int N = Integer.parseInt(bufferedReader.readLine());
        String[] lst = bufferedReader.readLine().split(" ");
        MonotonicStack monotonicStack = new MonotonicStack();
        for (int i = 0; i < N; i++) {
            System.out.printf("%d ", monotonicStack.monotonicPush(Integer.parseInt(lst[i])));
        }


        /*
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
