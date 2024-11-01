import java.util.*;
class Solution {
    public int[] solution(int n, int s) {
        int[] ans = {-1};
        if(n>s){
            return ans;
        }
        int[] answer =new int[n];
        int num = s/n;
        int div = s%n;
        for(int i=0;i<n;i++){
            answer[i] = num;
        }
        for(int i=0;i<div;i++){
            answer[i]+=1;
        }
            Arrays.sort(answer);
        return answer;
    }
}
