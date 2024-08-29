import java.io.*;
import java.util.*;

public class 토마토 {
    static int n, m, h;
    static int[][][] graph;
    static boolean[][][] visited;
    static Queue<int[]> que = new LinkedList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[] ud = {1, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken()); // 상자의 가로 칸 크기
        n = Integer.parseInt(st.nextToken()); // 상자의 세로 칸 크기
        h = Integer.parseInt(st.nextToken()); // 상자의 개수
        graph = new int[h][n][m];
        visited = new boolean[h][n][m];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < m; k++) {
                    graph[i][j][k] = Integer.parseInt(st.nextToken());
                    if (graph[i][j][k] == 1) {
                        que.offer(new int[] {i, j, k});
                        visited[i][j][k] = true;
                    }
                }
            }
        }

        bfs();
        int result = Integer.MIN_VALUE;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (graph[i][j][k] == 0) {
                        System.out.println(-1);
                        return;
                    }
                    result = Math.max(result, graph[i][j][k]);
                }
            }
        }

        System.out.println(result - 1);

    }

    static void bfs() {

        while (!que.isEmpty()) {
            int[] current = que.poll();
            int height = current[0];
            int x = current[1];
            int y = current[2];

            // 상하좌우 체크
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[height][nx][ny] && graph[height][nx][ny] == 0) {
                    visited[height][nx][ny] = true;
                    graph[height][nx][ny] = graph[height][x][y] + 1;
                    que.offer(new int[] {height, nx, ny});
                }
            }
            // 위쪽 아래쪽 체크
            for (int i = 0; i < 2; i++) {
                int dir = height + ud[i];
                if (dir >= 0 && dir < h && !visited[dir][x][y] && graph[dir][x][y] == 0) {
                    visited[dir][x][y] = true;
                    graph[dir][x][y] = graph[height][x][y] + 1;
                    que.offer(new int[] {dir, x, y});
                }
            }

        }

    }
}
