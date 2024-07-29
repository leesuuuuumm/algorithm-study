// [PRG] 올바른 괄호

import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;

        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++) {  
            if(s.charAt(i) == ')') {
                if(!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                    continue;
                }
            }
            stack.push(s.charAt(i));
        }
        if(!stack.isEmpty()) answer = false;
        
        return answer;
    }
}