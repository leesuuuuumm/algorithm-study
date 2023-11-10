import java.util.*;

class Solution {
    public int[] solution(String s) {
        int zero = 0;
        int change = 0;
        StringBuilder sb = new StringBuilder(s);
        while (true) {
            if (sb.toString().equals("1")) {
                break;
            }

            String tmp = sb.toString();
            sb.setLength(0);
            for (int i = 0; i < tmp.length(); i++) {
                char ch = tmp.charAt(i);

                if (ch == '0') {
                    zero++;
                } else {
                    sb.append('1');
                }
            }

            System.out.println(sb);

            int len = sb.length();
            sb.setLength(0);
            Stack<Integer> st = new Stack<>();
            while (len != 1) {
                st.push(len % 2);
                len /= 2;
            }
            sb.append(1);
            while (!st.isEmpty()) {
                sb.append(st.pop());
            }

            System.out.println(sb);

            change++;
        }

        return new int[]{change, zero};
    }
}