import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        
        ArrayList<Integer> list = new ArrayList<>();
        
        for(int i=0;i<people.length;i++){
            list.add(people[i]);
        }
        while(list.size()>=2){
            if(limit >=list.get(0)+ list.get(list.size()-1)){
                list.remove(0);
            }
            list.remove(list.size()-1);
            answer++;
        }
        
        
        return answer+list.size();
    }
}
