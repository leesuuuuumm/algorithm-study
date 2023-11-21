import java.util.*;

class Solution {

    public int[] solution(int[] numbers) {
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[numbers.length];

        st.push(0);
        for (int i = 1; i < numbers.length; i++) {
            while (!st.isEmpty() && numbers[st.peek()] < numbers[i]) {
                ans[st.pop()] = numbers[i];
            }
            st.push(i);
        }

        for (int i = 0; i < ans.length; i++) {
            if (ans[i] == 0) ans[i] = -1;
        }

        return ans;
    }
}