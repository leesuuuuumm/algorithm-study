import java.util.*;

class Solution
{
    public int solution(String s)
    {
        Stack<Character> stack = new Stack<>();
        
        char[] ch = s.toCharArray();
        for(int i=0;i<s.length();i++){
            if(stack.isEmpty()){
                stack.push(ch[i]);
            }else{
                if(stack.peek()==ch[i]){
                    stack.pop();
                }
                else{
                    stack.push(ch[i]);
                }
            }
        }
        if(stack.size()==0) return 1;
       
        return 0;
    }
}
