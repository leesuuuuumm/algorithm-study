import java.util.*;
class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<tangerine.length;i++){
            map.put(tangerine[i],map.getOrDefault(tangerine[i],0)+1);
        }
        
        ArrayList<Integer> list = new ArrayList<>();
        for(Integer i: map.keySet()){
            list.add(map.get(i));
        }
        
        Collections.sort(list, Collections.reverseOrder());
        
        for(Integer i: list){
            answer++;
            if(k<=i) break;
            k-=i;
        }
        return answer;
    }
}
