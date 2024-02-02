package boj;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main_1005_ACMCraft {
    static class Edge {
        int v;
        int w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    static int N, K, W;
    static int[] cost, degree;
    static List<Edge>[] edges;

    public static void main(String[] args) throws Exception {
        int T = read();
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            input();
            sb.append(topologySort()).append("\n");
        }
        System.out.print(sb);
    }

    private static int topologySort() {
        Queue<Edge> q = new ArrayDeque<>();
        int[] result = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            if (degree[i] > 0)
                continue;
            q.add(new Edge(i, 0));
        }

        while (!q.isEmpty()) {
            Edge cur = q.poll();

            if (cur.v == W)
                return cur.w + cost[cur.v];

            for (Edge next : edges[cur.v]) {
                --degree[next.v];
                result[next.v] = Math.max(result[next.v], result[cur.v] + next.w);
                if (degree[next.v] == 0)
                    q.add(new Edge(next.v, result[next.v]));
            }
        }
        return -1;
    }

    private static void input() throws Exception {
        N = read();
        K = read();
        cost = new int[N + 1];
        degree = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            cost[i] = read();
        }
        edges = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < K; i++) {
            int from = read();
            int to = read();
            edges[from].add(new Edge(to, cost[from]));
            degree[to]++;
        }
        W = read();
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}
