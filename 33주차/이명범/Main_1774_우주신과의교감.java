import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main_1774_우주신과의교감 {

    static class Edge implements Comparable<Edge> {
        int v1;
        int v2;
        double w;

        public Edge(int v1, int v2, double w) {
            this.v1 = v1;
            this.v2 = v2;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(w, o.w);
        }
    }
    static int N, M;
    static int[] parents;
    static List<Edge> elist = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        N = read();
        M = read();
        parents = new int[N + 1];
        int[][] point = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
        for (int i = 1; i <= N; i++) {
            point[i][0] = read();
            point[i][1] = read();
        }
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                double distance = calculateDistance(point[i], point[j]);
                elist.add(new Edge(i, j, distance));
            }
        }
        Collections.sort(elist);

        int count = 0;
        for (int i = 0; i < M; i++) {
            int a = read();
            int b = read();
            union(a, b);
            count++;
        }

        double result = 0;
        for (Edge cur : elist) {
            if (count == N - 1)
                break;
            if (union(cur.v1, cur.v2))
                result += cur.w;
        }
        System.out.printf("%.2f", result);
    }

    private static int find(int a) {
        if (parents[a] == a)
            return a;
        return parents[a] = find(parents[a]);
    }

    private static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb)
            return false;
        parents[pb] = pa;
        return true;
    }

    private static double calculateDistance(int[] p1, int[] p2) {
        return Math.sqrt(Math.pow(p1[0] - p2[0], 2) + Math.pow(p1[1] - p2[1], 2));
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}