import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        int start = 0;
        int end=0;
        Arrays.sort(people);
        for(int i=people.length-1;i>=0;i--){
            if(i<start){
                System.out.println("다 태움");
                break;
            }
            if(people[start]+people[i]<=limit){
                start++;
            }
            answer++;
        }
        return answer;
    }
}
