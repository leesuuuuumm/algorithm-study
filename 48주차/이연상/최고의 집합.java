// [PRG] 최고의 집합

import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        int[] answer;
        
        if(n > s) return new int[] {-1};
        
        int init = s / n; 
        int mod = s % n; 
        
        answer = new int[n];
        for(int i = 0; i < n; i++) {
            answer[i] = init;
        }
        
        int idx = n - 1;
        for(int m = 0; m < mod; m++) {
            answer[idx]++;
            idx--;
        }
        System.out.println(Arrays.toString(answer));
        
        return answer;
    }
}