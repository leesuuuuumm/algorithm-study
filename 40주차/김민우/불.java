
import java.io.*;
import java.util.*;

public class 불 {
    static int R, C;
    static char[][] maze;
    static int[][] fireDist;
    static int[][] jihoonDist;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        maze = new char[R][C];
        fireDist = new int[R][C];
        jihoonDist = new int[R][C];

        Queue<int[]> fireQ = new LinkedList<>();
        Queue<int[]> jihoonQ = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                maze[i][j] = line.charAt(j);
                fireDist[i][j] = -1;
                jihoonDist[i][j] = -1;
                if (maze[i][j] == 'F') {
                    fireQ.offer(new int[]{i, j});
                    fireDist[i][j] = 0;
                } else if (maze[i][j] == 'J') {
                    jihoonQ.offer(new int[]{i, j});
                    jihoonDist[i][j] = 0;
                }
            }
        }

        // 불 BFS
        while (!fireQ.isEmpty()) {
            int[] cur = fireQ.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
                if (fireDist[nx][ny] >= 0 || maze[nx][ny] == '#') continue;
                fireDist[nx][ny] = fireDist[cur[0]][cur[1]] + 1;
                fireQ.offer(new int[]{nx, ny});
            }
        }

        // 지훈 BFS
        while (!jihoonQ.isEmpty()) {
            int[] cur = jihoonQ.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (nx < 0 || nx >= R || ny < 0 || ny >= C) { // 지훈이 미로의 가장자리에 도달하면 이동시간을 출력하고 프로그램 종료
                    System.out.println(jihoonDist[cur[0]][cur[1]] + 1);
                    return;
                }
                if (jihoonDist[nx][ny] >= 0 || maze[nx][ny] == '#') continue;
                if (fireDist[nx][ny] != -1 && fireDist[nx][ny] <= jihoonDist[cur[0]][cur[1]] + 1) continue;
                jihoonDist[nx][ny] = jihoonDist[cur[0]][cur[1]] + 1;
                jihoonQ.offer(new int[]{nx, ny});
            }
        }

        System.out.println("IMPOSSIBLE");
    }
}