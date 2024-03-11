import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        Queue<Integer> q = new LinkedList<>();
        ArrayList<Integer> days = new ArrayList<>();
        
        for(int i = 0; i < speeds.length; i++){
            int d = 0;
            int m = 100 -  progresses[i];
            if(m % speeds[i] > 0) d++;
            d += (m / speeds[i]);
            days.add(d);
        }
        
        while(!days.isEmpty()){
            int cnt = 1;
            int group = days.remove(0);
            while(!days.isEmpty() && group >= days.get(0)){
                cnt++;
                days.remove(0);
            }
            q.add(cnt);
        }
        
        answer = new int[q.size()];
        for(int i = 0; i < answer.length; i++){
            answer[i] = q.poll();
        }
        
        return answer;
    }
}
