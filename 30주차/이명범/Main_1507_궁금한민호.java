import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int w;

        public Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return w - o.w;
        }
    }

    static int N;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static List<Edge>[] edges;

    public static void main(String[] args) throws Exception {
        N = read();
        edges = new List[N];

        for (int i = 0; i < N; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cost = read();
                if (i >= j) continue;
                pq.add(new Edge(i, j, cost));
            }
        }

        int result = 0;

        while(!pq.isEmpty()) {
            Edge cur = pq.poll();

            int w = dijkstra(cur.from, cur.to);

            if (w == -1) {
                edges[cur.from].add(new Edge(cur.from, cur.to, cur.w));
                edges[cur.to].add(new Edge(cur.to, cur.from, cur.w));
                result += cur.w;
            } else if (cur.w < w) {
                edges[cur.from].add(new Edge(cur.from, cur.to, cur.w));
                edges[cur.to].add(new Edge(cur.to, cur.from, cur.w));
                result += cur.w;
            } else if (cur.w > w) {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(result);
    }

    private static int dijkstra(int start, int end) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visit = new boolean[N];
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);

        pq.add(new Edge(-1, start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (cur.to == end) return cur.w;
            if (visit[cur.to]) continue;

            visit[cur.to] = true;

            for (Edge next : edges[cur.to]) {
                if (dist[next.to] <= dist[cur.to] + next.w) continue;

                dist[next.to] = dist[cur.to] + next.w;
                pq.offer(new Edge(-1, next.to, dist[next.to]));
            }
        }

        return -1;
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}