import java.util.*;

class Solution {
    
    static List<Integer>[] edges;
    
    public int solution(int n, int[][] edge) {
        init(n, edge);
        return bfs(n);
    }
    
    private int bfs(int n) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visit = new boolean[n + 1];
        q.add(1);
        visit[1] = true;
        
        int size = 0;
        while (!q.isEmpty()) {
            size = q.size();
            for (int i = 0; i < size; i++) {
                Integer cur = q.poll();
            
                for (Integer next : edges[cur]) {
                    if (visit[next])
                        continue;
                    q.add(next);
                    visit[next] = true;
                }
            }
        }
        return size;
    }
    
    private void init(int n, int[][] edge) {
        edges = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int[] e : edge) {
            int v1 = e[0];
            int v2 = e[1];
            edges[v1].add(v2);
            edges[v2].add(v1);
        }
    }
}