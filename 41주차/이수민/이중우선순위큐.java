import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        PriorityQueue<Integer> pqre = new PriorityQueue<>(Collections.reverseOrder());

        for(int i=0;i<operations.length;i++){
            String[] s = operations[i].split(" ");
            
            if(s[0].equals("I")){
                pq.offer(Integer.parseInt(s[1]));
                pqre.offer(Integer.parseInt(s[1]));
    
            }
            else if(s[0].equals("D")){

                if(!pq.isEmpty()&& s[1].equals("-1")){
                    pqre.remove(pq.poll());
                }else if(!pqre.isEmpty()&&s[1].equals("1")){
                    pq.remove(pqre.poll());
                   }
            }
        }
        if(!pq.isEmpty() || !pqre.isEmpty()){
            answer[0] = pqre.poll();
            answer[1] = pq.poll();
        }


        return answer;
    }
}
