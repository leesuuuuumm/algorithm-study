import java.io.*;
import java.util.*;

public class Main {
    /*
    2초, 256MB

    1. 전체 맵에서 7명의 위치를 조합을 이용해 선택한다.
        0~24 중 7개 뽑기
    2. 선택한 위치가 상하좌우로 인접해 있는지 BFS를 이용해 확인한다.
    3. BFS를 진행하면서 7명 중 이다솜파가 4명 이상인지도 확인한다.
     */
    static char[][] map = new char[5][5];
    static int[] tmpX = new int[25];
    static int[] tmpY = new int[25];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int cnt = 0;

    public static void main(String[] args) throws Exception {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < 5; j++) {
                map[i][j] = tmp.charAt(j);
            }
        }

        for (int i = 0; i < 25; i++) {
            tmpX[i] = i % 5;
            tmpY[i] = i / 5;
        }

        comb(new int[7], new boolean[25], 0, 25, 7);
        System.out.println(cnt);
    }

    // 조합
    public static void comb(int[] arr, boolean[] vis, int start, int n, int r) {
        if (r == 0) {
            int idx = 0;
            for (int i = 0; i < n; i++) {
                if (vis[i]) {
                    arr[idx++] = i;
                }
            }
            bfs(arr);
            return;
        }

        for (int i = start; i < n; i++) {
            vis[i] = true;
            comb(arr, vis, i + 1, n, r - 1);
            vis[i] = false;
        }
    }

    // bfs
    public static void bfs(int[] arr) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] isP7 = new boolean[5][5]; // 칠공주 위치 배열
        boolean[][] vis = new boolean[5][5];
        int connect = 1; // 현재 연결된 자리 갯수
        int lds = 0; // 이다솜파 인원수

        for (int i = 0; i < 7; i++) { // 칠공주 위치 표시
            int tmp = arr[i];

            isP7[tmpY[tmp]][tmpX[tmp]] = true;
        }
        int tmp = arr[0];
        vis[tmpY[tmp]][tmpX[tmp]] = true;
        q.offer(new int[]{tmpY[tmp], tmpX[tmp]});

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if (map[cur[0]][cur[1]] == 'S') { // 현재 자리가 이다솜파
                lds++;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) {
                    continue;
                }

                if (vis[nx][ny] || !isP7[nx][ny]) {
                    continue;
                }

                q.offer(new int[]{nx, ny});
                vis[nx][ny] = true;
                connect++;
            }
        }

        if (connect == 7 && lds >= 4) {
            cnt++;
        }
    }
}