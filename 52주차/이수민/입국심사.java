import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long L = 1;
        long R = 0;
        for(int i=0;i<times.length;i++){
            R = Math.max(times[i],R);
        }
        R=R*n;
        while(L<=R){
            long mid = (L+R)/2;
            
            long cnt = 0;
            
            for(int i=0;i<times.length;i++){
                cnt+= (mid/times[i]);
            }
            
            if(cnt>=n){
                R = mid-1;
            }else{
                L = mid+1;
            }
        }
        return L;
    }
}
