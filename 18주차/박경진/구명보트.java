import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);
        
        int idx = 0;
        for(int i = people.length-1; i >= idx; i--){
            if(limit - people[i] - people[idx] >= 0){
                idx++;
            }
            answer++;
        }
        
        return answer;
    }
}
