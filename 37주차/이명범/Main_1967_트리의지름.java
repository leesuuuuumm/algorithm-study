import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Edge {
        int dest;
        int weight;

        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    static int n;
    static List<Edge>[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        edges = new List[n + 1];

        for (int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges[parent].add(new Edge(child, weight));
            edges[child].add(new Edge(parent, weight));
        }

        dfs(1, 0, new boolean[n + 1]);

        dfs(mi, 0, new boolean[n + 1]);

        System.out.println(mw);
    }

    // 최대 인덱스
    static int mi = Integer.MIN_VALUE;
    // 최대 가중치
    static int mw = Integer.MIN_VALUE;
    private static void dfs(int cur, int weight, boolean[] visit) {
        List<Edge> edge = edges[cur];
        visit[cur] = true;

        for (int i = 0; i < edge.size(); i++) {
            Edge ce = edge.get(i);

            if (visit[ce.dest]) continue;

            dfs(ce.dest, weight + ce.weight, visit);
        }

        if (mw < weight) {
            mi = cur;
            mw = weight;
        }
    }
}