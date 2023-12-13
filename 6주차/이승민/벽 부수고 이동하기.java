import java.io.*;
import java.util.*;

class Node {
    int x, y, broken;

    public Node(int x, int y, int broken) {
        this.x = x;
        this.y = y;
        this.broken = broken; // 1이면 부순 것
    }
}

public class Main {
    /*
    2초, 192MB
    O(n^2)
    벽을 부수고 지나갈 때보다 부수지 않고 지나갔을 때가 더 빠른 경우가 있을 수 있다.
    visited 3차원으로 만들어서 처리
    벽이면
    횟수가 남았으면 부수고 visited[][][1] 사용
    벽이 아니면
    visited[][][0] 벽을 부수지 않았을 때
    visited[][][1] 벽을 부쉈을 때
     */
    static int[][] map;
    static int[][][] dist;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<Node> q = new LinkedList<>();
    static int n, m;

    public static void main(String[] args) throws Exception {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        dist = new int[n][m][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(dist[i][j], -1);
            }
        }

        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = tmp.charAt(j) - '0';
            }
        }

        dist[0][0][0] = dist[0][0][1] = 1;
        System.out.println(bfs(new Node(0, 0, 0)));
    }

    public static int bfs(Node node) {
        q.offer(node);

        while (!q.isEmpty()) {
            Node cur = q.poll();

            // 마지막 칸에 도달하면 출력 후 종료
            if (cur.x == n - 1 && cur.y == m - 1) {
                return dist[cur.x][cur.y][cur.broken];
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }

                // 벽 O
                if (map[nx][ny] == 1) {
                    if (cur.broken == 0 && dist[nx][ny][1] == -1) {
                        dist[nx][ny][1] = dist[cur.x][cur.y][cur.broken] + 1;
                        q.offer(new Node(nx, ny, 1));
                    }
                    // 벽 X
                } else if (map[nx][ny] == 0) {
                    if (dist[nx][ny][cur.broken] == -1) { // 이렇게 하면 broken 값에 따라 알아서 dist[][][0]이나 dist[][][1]에 들어가게 됨.
                        dist[nx][ny][cur.broken] = dist[cur.x][cur.y][cur.broken] + 1;
                        q.offer(new Node(nx, ny, cur.broken));
                    }
                }
            }
        }

        return -1;
    }
}