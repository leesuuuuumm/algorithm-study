import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    static class Edge implements Comparable<Edge> {
        int v1;
        int v2;
        int w;

        public Edge(int v1, int v2, int w) {
            this.v1 = v1;
            this.v2 = v2;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return w - o.w;
        }
    }
    static int n, m;
    static int[] parents;
    static List<Edge> list;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        while (true) {
            m = read(); n = read();
            if (m == 0 && n == 0) break;

            parents = new int[m];
            list = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                parents[i] = i;
            }

            long total = 0;
            for (int i = 0; i < n; i++) {
                int v1 = read();
                int v2 = read();
                int w = read();
                list.add(new Edge(v1, v2, w));
                total += w;
            }
            Collections.sort(list);

            long result = 0;
            for (Edge cur : list) {
                if (!union(cur.v1, cur.v2)) continue;
                result += cur.w;
            }
            sb.append(total - result).append("\n");
        }
        System.out.println(sb);
    }

    private static int find(int a) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    private static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) return false;

        parents[pb] = pa;
        return true;
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}