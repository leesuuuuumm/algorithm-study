import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        
        Queue<Character> q = new LinkedList<>();
        for(int i = 0; i < s.length(); i++){
            q.add(s.charAt(i));
        }
        
        for(int i = 0; i < s.length(); i++){
            char temp = q.poll();
            q.add(temp);
            Stack<Character> stack = new Stack<>();
            
            for(int j = 0; j < s.length(); j++){ //문자열 하나씩 확인
                char now = q.poll();
                q.add(now);
                
                if(stack.isEmpty()){ //스택이 비었으면
                    stack.push(now);
                }
                else if(now == ')' && stack.peek() == '('){
                    stack.pop();
                }
                else if(now == '}' && stack.peek() == '{'){
                    stack.pop();
                }
                else if(now == ']' && stack.peek() == '['){
                    stack.pop();
                }
                else { //여는 괄호이면 스택에 추가
                    stack.push(now);
                }
            }
            
            if(stack.isEmpty()){
                answer++;
            }
        }
        
        return answer;
    }
}
