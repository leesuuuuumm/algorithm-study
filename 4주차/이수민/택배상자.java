import java.util.*;

class Solution {
    public int solution(int[] order) {
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        
        
     for(int i=0;i<order.length;i++){
         stack.push(i+1);
         while(!stack.isEmpty()){
             if(stack.peek()==order[result]){
                 result++;
                stack.pop();
             }else break;
         }
     }

        return result;
    }
}
