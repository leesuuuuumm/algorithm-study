import java.util.*;

class Solution {
    public int solution(int[] arr1, int[] arr2) {
        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();
        
        long sum1 = 0;
        long sum2 = 0;
        
        for(int i = 0; i < arr1.length; i++) {
            int q1 = arr1[i];
            int q2 = arr2[i];
            
            sum1 += q1;
            sum2 += q2;
            
            queue1.offer(q1);
            queue2.offer(q2);
        }
        
        int answer = 0;
        while (sum1 != sum2) {
            
            if (answer > arr1.length * 3)
                return -1;
           
            if (sum1 > sum2) {
                int q1 = queue1.poll();
                sum1 -= q1;
                
                queue2.offer(q1);
                sum2 += q1;
            } else if (sum1 < sum2) {
                int q2= queue2.poll();
                sum2 -= q2;
                
                queue1.offer(q2);
                sum1 += q2;
            }
            answer++;
        }
        
        return answer;
    }
}