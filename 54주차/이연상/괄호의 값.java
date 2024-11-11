// [BOJ] 괄호의 값

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        
        int temp = 1;
        int res = 0;
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                temp *= 2;
                st.push(ch);
            } else if (ch == '[') {
                temp *= 3;
                st.push(ch);
            } else if (ch == ')') {
                if (st.isEmpty() || st.peek() != '(') {
                    res = 0;
                    break;
                }
                if (s.charAt(i - 1) == '(') {
                    res += temp;
                }
                temp /= 2;
                st.pop();
            } else if (ch == ']') {
                if (st.isEmpty() || st.peek() != '[') {
                    res = 0;
                    break;
                }
                if (s.charAt(i - 1) == '[') {
                    res += temp;
                }
                temp /= 3;
                st.pop();
            }
        }

        if (!st.isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(res);
        }

        sc.close();
    }
}