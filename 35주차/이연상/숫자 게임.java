// [PRG] 숫자 게임 

import java.util.Arrays;

class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        
        int answer = 0;
        int aIdx = 0;
        int bIdx = 0;
        
        for(int i=0; i<A.length; i++){
            if(A[aIdx]>B[bIdx]){
                bIdx++;
            }else if(A[aIdx]==B[bIdx]){
                bIdx++;
            }else{
                aIdx++;
                bIdx++;
                answer++;
            }
        }
        
        return answer;
    }
}