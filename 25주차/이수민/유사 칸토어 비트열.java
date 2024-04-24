import java.util.*;
class Solution {
    public int solution(int n, long l, long r) {
        int answer = 0;
        
        e:for(long i=l;i<=r;i++){
            
            if(i%5 ==3) continue;
            
      
            long tmp = i;
            
            while(true){
                if(tmp%5 == 0){
                    tmp/=5;
                }else {
                    tmp = (tmp/5)+1;
                }
                
                if(tmp%5==3) continue e;
                
            else if(tmp<=5){
                answer++;
                break;
            }
            }
       
        }
       
        
        return answer;
    }
}
