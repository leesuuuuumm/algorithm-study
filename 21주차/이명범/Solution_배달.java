import java.util.*;

class Edge {
    int v;
    int w;
    
    public Edge(int v, int w) {
        this.v = v;
        this.w = w;
    }
}

class Solution {
    
    private static final int INF = 987_654_321;
    
    static int[] dist;
    static List<Edge>[] edges;
    
    public int solution(int N, int[][] road, int K) {
        init(N, road);
        dijkstra();
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] > K)
                continue;
            answer++;
        }
        return answer;
    }
    
    private void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(o -> o.w));
        pq.add(new Edge(1, 0));
        dist[1] = 0;
        
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            
            for (Edge next : edges[cur.v]) {
                if (dist[next.v] <= dist[cur.v] + next.w)
                    continue;
                
                dist[next.v] = dist[cur.v] + next.w;
                pq.add(new Edge(next.v, next.w));
            }
        }
    }
    
    private void init(int N, int[][] road) {
        dist = new int[N + 1];
        edges = new List[N + 1];
        
        Arrays.fill(dist, INF);
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int[] r : road) {
            int v1 = r[0];
            int v2 = r[1];
            int w = r[2];
            
            edges[v1].add(new Edge(v2, w));
            edges[v2].add(new Edge(v1, w));
        }
    }
}