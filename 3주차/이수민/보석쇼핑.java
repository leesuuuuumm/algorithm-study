import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        HashMap<String, Integer> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        
        for(int i=0;i<gems.length;i++){
            set.add(gems[i]);
        }
   
        int s = 0;
        int e = 0;
        int ms = 0;
        int me = Integer.MAX_VALUE;
        
       while(e<gems.length){
            map.put(gems[e],map.getOrDefault(gems[e],0)+1);
            if(map.size() == set.size()){
                k:while(true){
                    if(map.get(gems[s])>=2){
                        map.put(gems[s],map.get(gems[s])-1);
                        s++;
                        continue;
                    }else if(map.get(gems[s])<2){
                        if((me-ms)>(e-s)){
                            me = e;
                            ms = s;
                        }
                        break k;
                    }
                }
            }
            e++;
        }
        answer[0] = ms+1;
        answer[1] = me+1;
       
        return answer;
    }
}
