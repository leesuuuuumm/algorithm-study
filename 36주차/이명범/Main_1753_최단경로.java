package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1753_최단경로 {

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

    static int V, E, K, dist[];
    static List<Edge>[] elist;

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        dist = new int[V + 1];
        elist = new List[V + 1];
        Arrays.fill(dist, INF);
        for (int i = 1; i <= V; i++) {
            elist[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            elist[from].add(new Edge(to, w));
        }

        dijkstra();

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= V; i++) {
            sb.append(dist[i] == INF ? "INF" : dist[i]).append("\n");
        }

        System.out.println(sb);
    }

    private static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visit = new boolean[V + 1];

        // 시작 지점 설정
        pq.offer(new Edge(K, 0));
        dist[K] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            // 이미 방문했으면 해당 지점은 이미 최소 비용일테니 다음 간선을 체크해본다.
            if (visit[cur.v]) continue;

            // 방문 체크
            visit[cur.v] = true;

            for (Edge next : elist[cur.v]) {
                // 이미 방문했던 간선이 더 낮은 비용이라면 현재 간선으로 가지 않아도 되므로 continue
                if (dist[next.v] <= dist[cur.v] + next.w) continue;

                dist[next.v] = dist[cur.v] + next.w;
                pq.add(new Edge(next.v, dist[next.v]));
            }
        }
    }
}
