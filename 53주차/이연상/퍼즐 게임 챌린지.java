// [PRG] 퍼즐 게임 챌린지

import java.lang.Math;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int left = 1;
        int right = getMax(diffs);
        
        while(left < right) {
            int mid = (left + right) / 2;
            long limit_test = calLimit(diffs, times, mid);
            
            if(limit_test <= limit)
                right = mid;
            else
                left = mid + 1;
        }
        
        return left;
    }
    
    static long calLimit(int[] diffs, int[] times, int level) {
        long limit_test = times[0];
        
        for(int i = 1; i < diffs.length; i++) {
            int diff = diffs[i];
            int time_cur = times[i];
            int time_prev = times[i - 1];
                
            if(level >= diff) 
                limit_test += time_cur;
            else 
                limit_test += (diff - level) * (time_cur + time_prev) + time_cur; 
        }
        
        return limit_test;
    }
    
    static int getMax(int[] arr) {
        int maxVal = 0;
        for(int x : arr) maxVal = Math.max(maxVal, x);
        return maxVal;
    }
}