import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    /*
    최단거리가 아닌 최소비용이 되도록 해야 하므로 다익스트라 사용
    O(N^2)
     */
    static class Node implements Comparable<Node> {
        int x, y, cost;

        Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node node) {
            return this.cost - node.cost;
        }
    }

    static int n;
    static int[][] cave;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] dist;
    static StringBuilder sb = new StringBuilder();
    static int problem = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            n = Integer.parseInt(br.readLine());
            if (n == 0) {
                break;
            }
            cave = new int[n][n];
            dist = new int[n][n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    cave[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dijkstra(new Node(0, 0, cave[0][0]));
        }

        System.out.println(sb);
    }

    static void dijkstra(Node start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        pq.offer(start);
        dist[0][0] = start.cost;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.x][cur.y] < cur.cost) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }

                if (dist[cur.x][cur.y] + cave[nx][ny] >= dist[nx][ny]) {
                    continue;
                }

                dist[nx][ny] = dist[cur.x][cur.y] + cave[nx][ny];
                pq.offer(new Node(nx, ny, dist[nx][ny]));
            }
        }

        sb.append("Problem " + problem + ": " + dist[n - 1][n - 1] + "\n");
        problem++;
    }
}