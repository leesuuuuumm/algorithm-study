import java.util.*;

class Solution {
    static int N;
    static boolean[] visit;
    
    public int solution(int[] cards) {
        init(cards);
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < N; i++) {
            if (visit[i])
                continue;
            pq.add(bfs(i, cards));
        }
        return pq.size() < 2 ? 0 : pq.poll() * pq.poll();
    }
    
    private int bfs(int s, int[] cards) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(s);
        visit[s] = true;
        
        int result = 0;
        while (!q.isEmpty()) {
            Integer cur = q.poll();
            
            result++;
            
            int next = cards[cur] - 1;
            
            if (visit[next])
                return result;
            
            q.add(next);
            visit[next] = true;
        }
        return -1;
    }
    
    private void init(int[] cards) {
        N = cards.length;
        visit = new boolean[N];
    }
}