import java.io.*;
import java.util.*;

public class 쉬운최단거리 {
    static int[][] graph;
    static int n, m;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        visited = new boolean[n][m];

        int[] target = new int[2]; // 탐색 시작 지점의 x,y 좌표를 보관

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 2) {
                    target[0] = i;
                    target[1] = j;
                    graph[i][j] = 0; // 0으로 초기화
                    visited[i][j] = true;
                }
            }
        }

        bfs(target);

        // 갈 수 있는데 방문하지 못한 경우
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && graph[i][j] == 1) {
                    graph[i][j] = -1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(graph[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void bfs(int[] target) {


        Queue<int[]> que = new LinkedList<>();
        que.offer(target);

        while (!que.isEmpty()) {
            int[] poll = que.poll();
            int currentX = poll[0];
            int currentY = poll[1];

            for (int i = 0; i < 4; i++) {
                int nx = currentX + dx[i];
                int ny = currentY + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[nx][ny]) {
                    if (graph[nx][ny] == 0) { // 갈 수 없을 경우엔 탐색x
                        continue;
                    }
                    // 갈 수 있는 유효한 경우
                    que.offer(new int[] {nx, ny});
                    visited[nx][ny] = true;
                    graph[nx][ny] = graph[currentX][currentY] + 1;
                }

            }
        }

    }
}