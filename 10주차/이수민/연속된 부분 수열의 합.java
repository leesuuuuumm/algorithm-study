import java.util.*;
class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        int s = 0;
        int e = 0;
        int sum = sequence[0];
        int min = Integer.MAX_VALUE; 
        while(true){
            if(s >= sequence.length)break;
            if(sum == k){
                if(min> (e-s)){
                    min = e - s;
                    answer[0] = s;
                    answer[1] = e;
                }
                if(min == 0) break;
               
                    sum-=sequence[s];
                    s++;
                
                if(e<sequence.length-1){
                    e++;
                    sum+=sequence[e];
                }
            }else if(sum <k) {
                if(e < sequence.length-1){
                e++;
                sum+=sequence[e];
                }else if(e == sequence.length-1){
                    sum-=sequence[s];
                    s++;
                }
            }else if(sum>k){
                sum-=sequence[s];
                s++;
            }
            
        }
        return answer;
    }
}
