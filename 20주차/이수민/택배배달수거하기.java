import java.util.*;
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        for(int i=n-1;i>=0;i--){
            
            while(true){
            if(deliveries[i] >0 || pickups[i]>0){
                deliveries[i]-=cap;
                pickups[i]-=cap;
                
                answer+= (i+1)*2;
                }else if(deliveries[i]<=0 &&pickups[i]<=0) break; 
            }
    
                if(i == 0) break;
 
            
            if(deliveries[i]<0){
                deliveries[i-1]+= deliveries[i];
            }
            if(pickups[i]<0){
                pickups[i-1]+=pickups[i];
            }
        }
            
        return answer;
    }
}
    

