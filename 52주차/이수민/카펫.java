import java.util.*;
class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        int all = brown + yellow;
        
        for(int r=2;r<=all/2 ;r++){
            if(all%r==0){
                if((r-2) * ((all/r)-2) == yellow){
                    answer[0] = r;
                    answer[1] = all/r;
                    
                }
            }
        }
        if(answer[0]<answer[1]){
            int tmp = answer[0];
            answer[0] = answer[1];
            answer[1] = tmp;
        }
        
        return answer;
    }
}
