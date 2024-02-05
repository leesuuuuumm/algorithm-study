import java.util.*;
class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        HashMap<Double, Integer> map = new HashMap<>();
        Arrays.sort(weights);
        for(int i=0;i<weights.length;i++){
            double p1 = weights[i]*1.0;
            double p2 = (weights[i]*2.0)/3.0;
            double p3 = (weights[i]*2.0)/4.0;
            double p4 = (weights[i]*3.0)/4.0;

            if(map.containsKey(p1)) answer+= map.get(p1);
            if(map.containsKey(p2)) answer+= map.get(p2);
            if(map.containsKey(p3)) answer+= map.get(p3);
            if(map.containsKey(p4)) answer+= map.get(p4);
            
            map.put(p1,map.getOrDefault(p1,0)+1);
        }
        
        return answer;
    }
}
