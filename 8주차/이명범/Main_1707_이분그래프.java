package boj;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Main_1707_이분그래프 {
    static int K, V, E;
    static Set<Integer> A;
    static Set<Integer> B;
    static List<Integer>[] edges;
    static int[] visit;

    public static void main(String[] args) throws Exception {
        K = read();

        for (int tc = 0; tc < K; tc++) {
            input();
            boolean result = true;
            for (int i = 1; i <= V; i++) {
                if (visit[i] != 0)
                    continue;
                if (!bfs(i))
                    result = false;
            }
            System.out.println(result ? "YES" : "NO");
        }
    }

    private static boolean bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        visit[start] = 1;

        boolean flag = true;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Integer cur = q.poll();

                for (Integer next : edges[cur]) {
                    if (visit[cur] == visit[next])
                        return false;
                    if (visit[next] != 0)
                        continue;

                    q.add(next);
                    visit[next] = flag ? -1 : 1;
                }
            }
            flag = !flag;
        }
        return true;
    }

    private static void input() throws Exception {
        V = read();
        E = read();
        A = new HashSet<>();
        B = new HashSet<>();
        edges = new List[V + 1];
        visit = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            int u = read();
            int v = read();

            edges[u].add(v);
            edges[v].add(u);
        }
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}
