import java.util.*;

class Solution {
    public int solution(String s) {
        StringBuilder sb = new StringBuilder(s);
        int ans = 0;

        int len = s.length();
        while (len-- > 0) {
            Stack<Character> st = new Stack<>();
            boolean flag = true;
            sb.append(sb.charAt(0));
            sb.deleteCharAt(0);

            for (int i = 0; i < sb.length(); i++) {
                char ch = sb.charAt(i);

                if (ch == '[' || ch == '(' || ch == '{') {
                    st.push(ch);
                } else if ((ch == ')' && !st.isEmpty() && st.peek() == '(')
                        || (ch == ']' && !st.isEmpty() && st.peek() == '[')
                        || (ch == '}' && !st.isEmpty() && st.peek() == '{')) {
                    st.pop();
                } else if ((ch == ')' && st.isEmpty())
                        || (ch == ']' && st.isEmpty())
                        || (ch == '}' && st.isEmpty())) {
                    flag = false;
                    break;
                }
            }
            if (!st.isEmpty()) {
                flag = false;
            }
            if (flag) {
                ans++;
            }
        }

        return ans;
    }
}