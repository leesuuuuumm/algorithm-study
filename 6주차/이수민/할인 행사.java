import java.util.*;
class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        HashMap<String, Integer> map = new HashMap<>();
        for(int i=0;i<number.length;i++){
            map.put(want[i],number[i]);
        }
        //System.out.println(map);
    
        a:for(int i=0;i<discount.length;i++){
            if(i+10> discount.length) break;
            
            HashMap<String, Integer> hm = new HashMap<>();
            for(int j=i;j<i+10;j++){
                hm.put(discount[j],hm.getOrDefault(discount[j],0)+1);
            }

            // System.out.println(tm);
            if(hm.size() == map.size()){
                for(String s: hm.keySet()){
                    if(!map.containsKey(s) || map.get(s)!= hm.get(s)){
                        continue a;
                    }
                }
                answer++;
            }
                
           
        }
        
        return answer;
    }
}
