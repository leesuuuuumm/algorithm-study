import java.util.*;
class Solution {
    
    public int solution(String s) {
        Stack<String> stack = new Stack<>();
        LinkedList<String> list = new LinkedList<>();
        int answer = 0;
        for(int i=0;i<s.length();i++){
            String[] str = s.split("");
            list.add(str[i]);
        }
        
        for(int i=0;i<s.length();i++){
            for(int j=0;j<list.size();j++){
                
                if(stack.isEmpty()){
                  stack.push(list.get(j)); 
                    continue;
                } 
                if((stack.peek().equals("("))&&(list.get(j).equals(")"))
                        ||(stack.peek().equals("{"))&&(list.get(j).equals("}"))
                        ||(stack.peek().equals("["))&&(list.get(j).equals("]"))){
                    stack.pop();
                }
                else stack.push(list.get(j));
                
            }
            
            if(stack.isEmpty()) answer++;
            
            list.add(list.get(0));
            list.remove(0);
           
            stack.clear();
        }
        
        
        return answer;
    }
}
