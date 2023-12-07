import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Edge {
    int to, cost;

    public Edge(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }
}

public class Main {
    /*
    간선에 가중치가 있으므로 다익스트라 알고리즘 사용
    각 정점마다 수행해야 하므로 다익스트라를 n회 수행
    O(n*nlogm)
     */
    static int n, m;
    static int[] dist;
    static ArrayList<Edge>[] edges;
    static int[][] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dist = new int[n + 1];
        edges = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }
        ans = new int[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges[from].add(new Edge(to, cost));
            edges[to].add(new Edge(from, cost)); // 집하장을 서로 오가는 게 가능하므로 양방향을 다 추가한다.
        }

        for (int i = 1; i <= n; i++) {
            dijkstra(i); // 다익스트라 n회 수행
        }

        printAns(); // 경로표 수정 후 출력
    }

    static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge e1, Edge e2) {
                return e1.cost - e2.cost;
            }
        });

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (dist[cur.to] < cur.cost) {
                continue;
            }

            for (Edge e : edges[cur.to]) {
                if (dist[cur.to] + e.cost >= dist[e.to]) {
                    continue;
                }

                ans[start][e.to] = cur.to; // start -> e.to 최단경로에서 직전에 거쳐온 정점 표시
                dist[e.to] = dist[cur.to] + e.cost;
                pq.offer(new Edge(e.to, dist[e.to]));
            }
        }
    }

    /*
    다익스트라만 수행했을 때는 직전에 거쳐온 정점이 적혀있으므로 처음 방문한 정점으로 수정
    ans[1][6]=5 -> ans[1][5]=2 -> ans[1][2]=1
    => ans[1][6]=2
     */
    static void printAns() {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (ans[i][j] != 0) {
                    int target = j;
                    while (ans[i][target] != i) {
                        target = ans[i][target];
                    }
                    sb.append(target + " ");
                } else {
                    sb.append("- ");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}