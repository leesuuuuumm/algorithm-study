import java.util.*;
class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        HashSet<Integer> set = new HashSet<>();
        int[] dp1 = new int[topping.length];
        int[] dp2 = new int[topping.length];
        for(int i=0;i<topping.length;i++){
            set.add(topping[i]);
            dp1[i] = set.size();
        }
        set.clear();
        for(int i=topping.length-1;i>=0;i--){
            set.add(topping[i]);
            dp2[i] = set.size();
        }
        
        for(int i=0;i<topping.length-1;i++){
            if(dp1[i] == dp2[i+1]){
                answer++;
            }
        }
        return answer;
    }
}
