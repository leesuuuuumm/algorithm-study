
import java.io.*;
import java.util.*;

public class 연구소 {

    static int n, m;
    static int[][] graph;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int max = Integer.MIN_VALUE;

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

        //  모든 정점에 대해, 벽을 하나씩 쳐보면서(dfs) 벽을 3개 쳤을 때마다, bfs로 바이러스를 전파시켜, 가장 큰 안전구역을 갱신하자

        // 1. 모든 정점에 대해 벽을 치는 경우의 수 구하기
        dfs(0);

        System.out.println(max);

    }

    static void dfs(int wallCnt) {
        if (wallCnt == 3) {
            // 현재 벽
            bfs();
            return;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] == 0) {
                    graph[i][j] = 1;
                    dfs(wallCnt + 1);
                    graph[i][j] = 0;
                }
            }
        }
    }

    static void bfs() {
        Queue<int[]> que = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] == 2) {
                    que.offer(new int[] {i, j});
                }
            }
        }

        int[][] copyMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            copyMap[i] = Arrays.copyOf(graph[i], graph[i].length); // 복사할 배열, 새로 생성될 배열의 길이
        }

        while (!que.isEmpty()) {
            int[] current = que.poll();
            for (int d = 0; d < 4; d++) {
                int nx = current[0] + dx[d];
                int ny = current[1] + dy[d];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m && copyMap[nx][ny] == 0) {
                    copyMap[nx][ny] = 2;
                    que.offer(new int[] {nx, ny});
                }

            }
        }

        // 최대값 갱신
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copyMap[i][j] == 0) {
                    cnt++;
                }
            }
        }

        max = Math.max(max, cnt);

    }

}
