import java.util.*;
class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int)(right-left)+1];
        // 행: (i/N)+1 열: (i%N)+1
        
        int k = 0;
        for(long i = left; i<=right;i++){
            int r = (int)(i/n)+1;
            int c = (int)(i%n)+1;
            answer[k++] = Math.max(r,c);
        }
        
        
        return answer;
    }
}
