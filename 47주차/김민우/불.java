import java.io.*;
import java.util.*;

public class Main {

    static int r, c;
    static char[][] maze;
    static int[][] jihun;
    static int[][] fire;
    static Queue<int[]> fireQue = new LinkedList<>();
    static Queue<int[]> jihunQue = new LinkedList<>();
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        maze = new char[r][c];
        fire = new int[r][c];
        jihun = new int[r][c];

        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++){
                char s = str.charAt(j);
                maze[i][j] = s;
                jihun[i][j] = -1;
                fire[i][j] = -1;

                if (s == 'J') {
                    jihunQue.offer(new int[] {i, j});
                    jihun[i][j] = 0;
                }
                if (s == 'F') {
                    fireQue.offer(new int[] {i, j});
                    fire[i][j] = 0;
                }
            }
        }

        // 1. 불을 먼저 bfs해서 각 지점마다 불이 붙는 시간을 fire배열에 넣어준다.
        fireBfs();


        // 2. 지훈이를 bfs 하면서 불 - 지훈 > 0 이라면, 지훈이가 이동할 수 있으니 이동시킨다. 만약 더 이상 이동할 수 있는 곳이 없다면(영역에 벗어난다면 가능하다.)

        while (!jihunQue.isEmpty()) {
            int[] current = jihunQue.poll();
            int x = current[0];
            int y = current[1];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= r || ny < 0 || ny >= c) { // 지훈이 미로의 가장자리에 도달하면 이동시간을 출력하고 프로그램 종료 (즉, 아래에서 불이 붙어있으면 못가게 조작한다는 의미이다.)
                    System.out.println(jihun[x][y] + 1);
                    return;
                }

                // 지훈이의 위치이거나 벽이면 스킵한다.
                if (jihun[nx][ny] >= 0 || maze[nx][ny] == '#') continue;

                //
                if (fire[nx][ny] != -1 && fire[nx][ny] <= jihun[x][y] + 1) continue;
                jihun[nx][ny] = jihun[x][y] + 1;
                jihunQue.offer(new int[]{nx, ny});

            }
        }
        System.out.println("IMPOSSIBLE");

    }

    static void fireBfs() {
        while (!fireQue.isEmpty()) {
            int[] current = fireQue.poll();
            int x = current[0];
            int y = current[1];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < r && ny < c && maze[nx][ny] != '#' && fire[nx][ny] < 0) {
                    fireQue.offer(new int[] {nx, ny});
                    fire[nx][ny] = fire[x][y] + 1;
                }
            }
        }

    }

}