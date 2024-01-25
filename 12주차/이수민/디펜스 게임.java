import java.util.*;
 
class Solution {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        int nn = n;
        int nk = k;
        
        for(int i=0;i<enemy.length;i++){
            nn-=enemy[i];
            pq.offer(enemy[i]);
            if(nn<0){
                while(!pq.isEmpty()){
                    if(nk == 0) break;
                
                    nn+=  pq.poll();
                    nk--;
                    
                    if(nn>0){
                        break;
                    }
                }
            }
            if(nn<0){
                return i;
            }
        }
        
        

        return enemy.length; 
    }
}
