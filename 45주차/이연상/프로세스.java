// [PRG] 프로세스

import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 1;
        
        ArrayList<ArrayList<Integer>> q = new ArrayList<>();
        for(int i = 0; i < priorities.length; i++) {
            q.add(new ArrayList<>(Arrays.asList(i, priorities[i])));
        }
        
        while(!q.isEmpty()) {
            if(findMax(q) > q.get(0).get(1)) {
                q.add(q.get(0));
                q.remove(0);
                continue;
            }
            
            if(q.get(0).get(0) == location)
                break;
            
            q.remove(0);
            answer++;
        }
        
        return answer;
    }
    
    public static int findMax(ArrayList<ArrayList<Integer>> q) {
        int val = 0;
        for(int i = 0; i < q.size(); i++) {
            val = Math.max(q.get(i).get(1), val);
        }
        return val;
    }
}