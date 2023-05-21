package Algorithm.Basic.DataStructure;

public class SpecialStack {
    // 表达式求值 https://www.acwing.com/problem/content/3305/
    public class IntStack {
        int N = 100010;
        int[] values = new int[N];
        int pointer = 0;

        public void push(int x) {
            values[++pointer] = x;
        }

        public void pop() {
            pointer--;
        }

        public boolean empty() {
            return pointer == 0;
        }

        public int query() {
            return values[pointer];
        }
    }

    public class SignStack {
        int N = 100010;
        char[] values = new char[N];
        int pointer = 0;

        public void push(char x) {
            values[++pointer] = x;
        }

        public void pop() {
            pointer--;
        }

        public boolean empty() {
            return pointer == 0;
        }

        public char query() {
            return values[pointer];
        }
    }

    IntStack intStack = new IntStack();
    SignStack signStack = new SignStack();

    public void eval() {
        int a = intStack.query();
        intStack.pop();
        int b = intStack.query();
        intStack.pop();
        char c = signStack.query();
        signStack.pop();
        if (c == '+') {
            intStack.push(b + a);
        } else if (c == '-') {
            intStack.push(b - a);
        } else if (c == '*') {
            intStack.push(b * a);
        } else {
            intStack.push(b / a);
        }
    }
}
