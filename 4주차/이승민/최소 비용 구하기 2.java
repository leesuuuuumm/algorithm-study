import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
    int idx, cost;

    public Edge(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge edge) {
        return edge.cost - this.cost;
    }
}

public class Main {
    /*
    다익스트라 알고리즘 + 경로복원법 활용
    O(mlogn)
     */
    static int n, m, from, to;
    static int[] dist; // Integer.MAX_VALUE 가능
    static ArrayList<Edge>[] edges;
    static int[] path;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        dist = new int[n + 1];
        edges = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }
        path = new int[n + 1];
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges[f].add(new Edge(t, cost));
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        from = Integer.parseInt(st.nextToken());
        to = Integer.parseInt(st.nextToken());

        dijkstra(from);

        printAns();
    }

    static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Arrays.fill(dist, Integer.MAX_VALUE);
        pq.offer(new Edge(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (dist[cur.idx] < cur.cost) {
                continue;
            }

            for (Edge e : edges[cur.idx]) {
                if (dist[cur.idx] + e.cost >= dist[e.idx]) {
                    continue;
                }

                path[e.idx] = cur.idx; // 마지막으로 지나온 정점 표시
                dist[e.idx] = dist[cur.idx] + e.cost;
                pq.offer(new Edge(e.idx, dist[e.idx]));
            }
        }
    }

    static void printAns() {
        StringBuilder sb = new StringBuilder();
        sb.append(dist[to] + "\n");

        int target = to;
        int cnt = 1; // 경로에 포함된 도시 갯수
        ArrayList<Integer> list = new ArrayList<>(); // 출력할 경로
        list.add(to);
        while (path[target] != from) {
            list.add(path[target]);
            cnt++;
            target = path[target];
        }
        list.add(from);
        cnt++;

        sb.append(cnt + "\n");
        for (int n = list.size() - 1; n >= 0; n--) {
            sb.append(list.get(n) + " ");
        }

        System.out.println(sb);
    }
}