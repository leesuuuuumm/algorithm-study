
import java.io.*;
import java.util.*;

public class 빙산 {

    static int n, m;
    static int[][] graph;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        int cnt = 0;

        // 빙하가 2개 이상 분리될 경우 반복문 종료
        // 빙하가 다 녹아버렸다면, 0을 출력
        while ((cnt = separateNum()) < 2) {
            if (cnt == 0) {
                ans = 0;
                break;
            }
            melt();
            ans++;
        }

        System.out.println(ans);
    }

    public static int separateNum() {
        boolean[][] visited = new boolean[n][m];

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] != 0 && !visited[i][j]) {
                    dfs(i, j, visited);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static void dfs(int x, int y, boolean[][] visited) {
        visited[x][y] = true;

        int nx, ny;
        for (int i = 0; i < 4; i++) {
            nx = x + dx[i];
            ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m ) continue;

            if (graph[nx][ny] != 0 && !visited[nx][ny]) {
                dfs(nx, ny, visited);
            }

        }
    }

    public static void melt() {
        Queue<int[]> q = new LinkedList<>();

        // 이전에 녹은 것을 미처리하기 위해 visited배열 선언
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] != 0) {
                    q.offer(new int[] {i, j});
                    visited[i][j] = true;
                }
            }
        }

        int nx, ny;
        while (!q.isEmpty()) {
            int[] current = q.poll();
            int x = current[0];
            int y = current[1];

            int seaNum = 0;
            for (int i = 0; i < 4; i++) {
                nx = x + dx[i];
                ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m ) continue;

                if (!visited[nx][ny] && graph[nx][ny] == 0) {
                    seaNum++;
                }
            }
            // 위에서 인접한 바다 영역 수를 셌으니, 이제 빙하에 반영하자
            if(graph[x][y] - seaNum < 0) {
                graph[x][y] = 0;
            } else {
                graph[x][y] -= seaNum;
            }

        }

    }


}
