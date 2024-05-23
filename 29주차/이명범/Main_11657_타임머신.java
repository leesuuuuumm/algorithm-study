import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

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
            return w - o.w;
        }
    }

    static int N, M;
    static List<Edge>[] elist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        elist = new List[N + 1];

        for (int i = 1; i <= N; i++) {
            elist[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            elist[from].add(new Edge(to, w));
        }

        long[] res = bellmanFord(1);

        StringBuilder sb = new StringBuilder();
        if (res == null) sb.append(-1);
        else {
            for (int i = 2; i <= N; i++) {
                if (res[i] == INF) sb.append(-1).append("\n");
                else sb.append(res[i]).append("\n");
            }
        }
        System.out.print(sb);
    }

    static int INF = 100_000_000;
    private static long[] bellmanFord(int start) {
        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (Edge next : elist[j]) {
                    if (dist[j] == INF || dist[next.v] <= dist[j] + next.w) continue;
                    if (i == N) return null;
                    dist[next.v] = dist[j] + next.w;
                }
            }
        }
        return dist;
    }
}