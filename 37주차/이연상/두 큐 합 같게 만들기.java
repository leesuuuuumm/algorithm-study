// [PRG] 두 큐 합 같게 만들기

import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        long sum1 = 0, sum2 = 0;
        for(int i = 0; i < queue1.length; i++) {
            q1.add(queue1[i]);
            q2.add(queue2[i]);
            sum1 += queue1[i];
            sum2 += queue2[i];
        }
        
        while(true) {
            if(sum1 == sum2) 
                break;
            
            if(answer > (queue1.length + queue2.length) * 2)
                return -1;
            
            if(sum1 > sum2) {
                int temp = q1.poll();
                q2.add(temp);
                sum1 -= (long) temp;
                sum2 += (long) temp;
            }
            else {
                int temp = q2.poll();
                q1.add(temp);
                sum1 += (long) temp;
                sum2 -= (long) temp;
            }
            
            answer++;
        }
        
        return answer;
    }
}