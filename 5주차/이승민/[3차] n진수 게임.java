import java.util.*;
class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < t * m; i++) {
            tmp.append(change(n, i));
        }
        char[] s = tmp.toString().toCharArray();
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.length; i++) {
            if (i % m + 1 == p) {
                ans.append(s[i]);
                t--;
                if (t == 0) {
                    break;
                }
            }
        }

        return ans.toString();
    }

    // 10진수 -> n진수
    public String change(int n, int num) {
        Stack<Integer> st = new Stack<>();
        while (true) {
            if (num / n == 0) {
                st.push(num % n);
                break;
            }

            st.push(num % n);
            num /= n;
        }

        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty()) {
            int tmp = st.peek();
            switch (tmp) {
                case 10:
                    sb.append('A');
                    break;
                case 11:
                    sb.append('B');
                    break;
                case 12:
                    sb.append('C');
                    break;
                case 13:
                    sb.append('D');
                    break;
                case 14:
                    sb.append('E');
                    break;
                case 15:
                    sb.append('F');
                    break;
                default:
                    sb.append(tmp);
            }
            st.pop();
        }
        return sb.toString();
    }
}