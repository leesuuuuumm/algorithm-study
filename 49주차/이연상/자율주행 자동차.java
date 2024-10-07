// [CDT] 자율주행 자동차

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m, x, y, dir;
    static int[][] map;
    static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static boolean[][] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());

        visit = new boolean[n][m];
        visit[x][y] = true;

        map = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 1;
        while(true) {
            
            boolean replay = false;
            for(int i = 0; i < 4; i++) {
                dir = getNextDir();
                int nx = x + move[dir][0];
                int ny = y + move[dir][1];

                if(visit[nx][ny])
                    continue;

                if(map[nx][ny] == 1)
                    continue;

                visit[nx][ny] = true;
                answer += 1;
                x = nx;
                y = ny;
                replay = true;
                break;
            }

            if(replay) continue;

            int nx = x - move[dir][0];
            int ny = y - move[dir][1];

            if(map[nx][ny] == 1)
                break;

            x = nx;
            y = ny;
        }

        System.out.println(answer);
    }

    static int getNextDir() { // 다음 방향 설정

        if (dir == 0) return 3; // 북 -> 서
        if (dir == 1) return 0; // 동 -> 북
        if (dir == 2) return 1; // 남 -> 동
        if (dir == 3) return 2; // 서 -> 남

        return 0;
    }
}