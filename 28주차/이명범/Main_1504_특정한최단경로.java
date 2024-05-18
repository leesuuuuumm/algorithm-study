import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge> {
        int v;
        int w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(w, o.w);
        }
    }

    static int N, E, v1, v2;
    static List<Edge>[] elist;
    static final int INF = 100_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        elist = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            elist[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            elist[from].add(new Edge(to, w));
            elist[to].add(new Edge(from, w));
        }

        st = new StringTokenizer(br.readLine());

        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        int result = Math.min(
                dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, N),
                dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, N)
        );

        System.out.println(result >= INF ? -1 : result);
    }

    private static int dijkstra(int start, int end) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visit = new boolean[N + 1];

        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);

        pq.offer(new Edge(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (cur.v == end) return cur.w;
            if (visit[cur.v]) continue;

            visit[cur.v] = true;

            for (Edge next : elist[cur.v]) {
                if (dist[next.v] <= dist[cur.v] + next.w) continue;

                dist[next.v] = dist[cur.v] + next.w;
                pq.offer(new Edge(next.v, dist[next.v]));
            }
        }

        return INF;
    }
}