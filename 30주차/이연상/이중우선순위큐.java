// [PRG] 이중우선순위큐

import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        
        PriorityQueue<Integer> minq = new PriorityQueue<>();
        PriorityQueue<Integer> maxq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(String op : operations) {
            String[] ops = op.split(" ");
            String cal = ops[0];
            int num = Integer.parseInt(ops[1]);
            
            if(cal.equals("I")) {
                minq.add(num);
                maxq.add(num);
            }
            else {
                if(minq.size() == 0 || maxq.size() == 0)
                    continue;
                if(num == -1) {
                    int temp = minq.poll();
                    maxq.remove(temp);
                }
                else {
                    int temp = maxq.poll();
                    minq.remove(temp);
                }
            }
        }
        
        if(minq.size() == 0 || maxq.size() == 0) return new int[] {0, 0};
        answer[1] = minq.poll();
        answer[0] = maxq.poll();
        
        return answer;
    }
}