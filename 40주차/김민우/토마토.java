package August;

import java.io.*;
import java.util.*;

public class 토마토 {


    // 철수는 익는 최소 일자를 알고 싶다.
    // bfs로 한 칸을 탐색할 때마다, 해당 칸의 최소 일자를 담을 수 있으니, bfs를 이용하자.
    // 탐색하는 칸마다 해당 칸의 최소 일자를 보관한다.
    public static void main(String[] args) throws IOException{

        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken()); // M N 입력
        int n = Integer.parseInt(st.nextToken());
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0}; // 상하좌우

        int[][] area = new int[n][m];
        for (int i = 0 ; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                area[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        // 만약 모든 칸을 순회하며 bfs를 수행한다고하면, 순회하는데 O(NM) * O(NM)bfs탐색 의 결과가 나와 O(N^2M^2)의 시간복잡도로 인해 시간 초과
        Queue<int[]> que = new LinkedList<>();
        // 모든 칸을 순회하며 먼저 익은토마토를 체크하고, 큐에 삽입해 동시에 bfs를 탐색하자.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (area[i][j] == 1) que.offer(new int[] {i, j});
            }
        }

        // 큐 내부에 익은 칸들이 준비되어 있으니 bfs를 수행하면 최소 일자를 구할 수 있다.
        while (!que.isEmpty()) {
            int[] current = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m && area[nx][ny] == 0) { // 영역의 인덱스가 넘어가지않고(-1, n보다 큼) 인접한 토마토가 익지 않았다면
                    que.offer(new int[] {nx, ny}); // 큐에 삽입후
                    area[nx][ny] = area[current[0]][current[1]] + 1; // 이전 탐색에서 + 1처리
                }
            }
        }

        int ans = 0; // 배열의 가장 큰 값이 최소 시간이다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (area[i][j] == 0) {
                    System.out.println(-1);
                    return; // 익지 못한게 있다면 -1출력
                }
                ans = Math.max(ans, area[i][j]);
            }
        }

        System.out.println(ans - 1); // 시작 날을 0일이 아닌, 1일을 기준으로 잡았기에 하루를 빼준다.


    }

}