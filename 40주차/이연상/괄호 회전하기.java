// [PRG] 괄호 회전하기

import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        
        Stack<Character> stack = new Stack<>();
        
        for(int i = 0; i < s.length(); i++) {
            
            String temp = s.substring(i, s.length()) + s.substring(0, i);
            
            for(int j = 0; j < temp.length(); j++) {
                if(stack.empty()) {
                    stack.push(temp.charAt(j));
                    continue;
                }
                
                if(temp.charAt(j) == ']') {
                    if(stack.peek() == '[') stack.pop();
                }
                else if(temp.charAt(j) == '}') {
                    if(stack.peek() == '{') stack.pop();
                }
                else if(temp.charAt(j) == ')') {
                    if(stack.peek() == '(') stack.pop();
                }
                else stack.push(temp.charAt(j));
            }
            
            if(stack.empty()) answer += 1;
            else {
                while(!stack.empty()) {
                    stack.pop();
                }
            }
            
        }
        
        return answer;
    }
}