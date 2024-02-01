import java.util.*;
class Solution {
    int solution(int[][] land) {
        int answer = 0;
        
        for(int r=1;r<land.length;r++){
           land[r][0]+= Math.max(land[r-1][1],Math.max(land[r-1][2], land[r-1][3]));
           land[r][1]+= Math.max(land[r-1][0],Math.max(land[r-1][2], land[r-1][3]));
           land[r][2]+= Math.max(land[r-1][0],Math.max(land[r-1][1], land[r-1][3]));
           land[r][3]+= Math.max(land[r-1][0],Math.max(land[r-1][1], land[r-1][2])); 
        }
        
        for(int c=0;c<4;c++){
            answer = Math.max(answer, land[land.length-1][c]);
        }
        return answer;
    }
}
