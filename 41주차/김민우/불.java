
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 불 {

    static int R, C;
    static char[][] maze;
    static int[][] fire;
    static int[][] jihoon;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        maze = new char[R][C];
        fire = new int[R][C];
        jihoon = new int[R][C];

        Queue<int[]> fireQ = new LinkedList<>();
        Queue<int[]> jihoonQ = new LinkedList<>();

        // 초기화
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                maze[i][j] = line.charAt(j);
                fire[i][j] = -1;
                jihoon[i][j] = -1;
                if (maze[i][j] == 'F') {
                    fireQ.offer(new int[]{i, j});
                    fire[i][j] = 0;
                } else if (maze[i][j] == 'J') {
                    jihoonQ.offer(new int[]{i, j});
                    jihoon[i][j] = 0;
                }
            }
        }

        while (!fireQ.isEmpty()) {
            int[] cur = fireQ.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
                if (fire[nx][ny] >= 0 || maze[nx][ny] == '#') continue;
                fire[nx][ny] = fire[cur[0]][cur[1]] + 1;
                fireQ.offer(new int[]{nx, ny});
            }
        }

        while (!jihoonQ.isEmpty()) {
            int[] cur = jihoonQ.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                // 가장자리 = 가장 가까운 곳
                if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                    System.out.println(jihoon[cur[0]][cur[1]] + 1);
                    return;
                }
                // 방문한 위치 or 벽인 경우 건너뛰기
                if (jihoon[nx][ny] >= 0 || maze[nx][ny] == '#') continue;
                // 이미 불이 도달한 경우 or 불이 먼저 도착하는 경우
                if (fire[nx][ny] != -1 && fire[nx][ny] <= jihoon[cur[0]][cur[1]] + 1) continue;
                // 이동 가능한 경우
                jihoon[nx][ny] = jihoon[cur[0]][cur[1]] + 1;
                jihoonQ.offer(new int[]{nx, ny});
            }
        }

        System.out.println("IMPOSSIBLE");

    }

}
