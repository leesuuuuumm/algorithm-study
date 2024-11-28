// [BOJ] 로봇 청소기

import java.util.*;
import java.io.*;

public class RobotVacuum {
    static int n, m, cnt = 0;
    static int[][] graph, visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        visited = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs(r, c, d);
    }

    static void bfs(int i, int j, int d) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{i, j});
        visited[i][j] = 1;
        cnt++;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            boolean flag = false;

            for (int k = 0; k < 4; k++) {
                d = (d + 3) % 4; // 방향 변경
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && graph[nx][ny] == 0) {
                    if (visited[nx][ny] == 0) {
                        visited[nx][ny] = 1;
                        queue.add(new int[]{nx, ny});
                        cnt++;
                        flag = true;
                        break;
                    }
                }
            }

            if (!flag) { // 4방향 모두 이동 불가
                int backX = x - dx[d];
      