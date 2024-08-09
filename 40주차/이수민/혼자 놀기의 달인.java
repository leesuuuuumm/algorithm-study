import java.util.*;

class Solution {
    public int solution(int[] cards) {
        int N = cards.length;
        
        boolean[] v = new boolean [N];
        
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<N;i++){
            Queue<Integer> que = new LinkedList<>();
            if(!v[cards[i]-1]){
                int cnt = 0;
                que.add(cards[i]-1);
                v[cards[i]-1] = true;
                while(!que.isEmpty()){
                    int now = que.poll();
                    cnt++;
                    
                    int next = cards[now]-1;
                    
                    if(v[next]) continue;
                    que.add(next);
                    v[next]=true;
                }
                list.add(cnt);
                
            }
            
        }
       
        Collections.sort(list,Comparator.reverseOrder());
        int answer = 0;
        if(list.size()!=1)
            answer = list.get(0)*list.get(1);
        return answer;
    }
}
