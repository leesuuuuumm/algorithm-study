import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Edge {
        int v;
        int w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    static int N, M, W;
    static List<Edge>[] elist;
    static int[] parent;
    static boolean[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            elist = new List[N + 1];

            for (int i = 1; i <= N; i++) {
                elist[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());

                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());

                elist[S].add(new Edge(E, T));
                elist[E].add(new Edge(S, T));
            }

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());

                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = -Integer.parseInt(st.nextToken());

                elist[S].add(new Edge(E, T));
            }
            sb.append(bellmanFord() ? "YES" : "NO").append("\n");
        }
        System.out.println(sb);
    }

    static final int INF = 10_000_000;
    private static boolean bellmanFord() {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);

        for (int i = 0; i < N; i++) {
            boolean isUpdated = false;
            for (int cur = 1; cur <= N; cur++) {
                for (Edge next : elist[cur]) {
                    if (dist[next.v] <= dist[cur] + next.w) continue;
                    isUpdated = true;
                    if (i == N - 1) return true;
                    dist[next.v] = dist[cur] + next.w;
                }
            }
            if (!isUpdated) return false;
        }
        return false;
    }
}