import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue <Integer> pq = new PriorityQueue<>();//오름차순
        for(int i=0;i<scoville.length;i++){
            pq.add(scoville[i]);
        }
        int first=pq.poll();
        int second=pq.poll();
        if(first==0&&second==0)
            return -1;
        int mix=0;
        while(first<K){
            answer++;
            mix=first+second*2;
            pq.add(mix);
            first=pq.poll();
            if(pq.isEmpty()){
                if(first>=K)
                    break;
                else{
                    answer=-1;
                    break;
                }
            }
            second=pq.poll();
        }
        return answer;
    }
}
