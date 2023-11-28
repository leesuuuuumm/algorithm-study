import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue <Integer> q_answer= new LinkedList<>();
        int pro_num=0;
        int day=0;
        int ans=0;
        //Queue <Integer> q_por=new LinkedList<>();
        while(pro_num!=progresses.length){
            ans=0;
            while(pro_num!=progresses.length){
                if(progresses[pro_num]+day*speeds[pro_num]>=100){
                    ans++;
                    pro_num++;
                }
                else{
                    day++;
                    break;
                }
            }
            if(ans!=0)
                q_answer.add(ans);
        }
        int size=q_answer.size();
        int[] answer =new int[size];
        for(int i=0;i<size;i++){
            answer[i]=q_answer.poll();
        }
        return answer;
    }
}
