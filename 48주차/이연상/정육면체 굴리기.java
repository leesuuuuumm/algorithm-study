// [CDT] 정육면체 굴리기

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, x, y, k;
    static int[][] map;
    static int[][] move = {{0, 1}, {0, -1}, {-1,0}, {1, 0}};
    static int[] dice = {0, 0, 0, 0, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
 
        dice[2] = map[x][y]; // 주사위 바닥면에 복사
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < k; i++) {
            int direction = Integer.parseInt(st.nextToken());

            int nx = x + move[direction - 1][0];
            int ny = y + move[direction - 1][1];

            if(nx >= n || ny >= m || nx < 0 || ny < 0) continue;
            x = nx;
            y = ny;

            moveDice(direction);

            if(map[x][y] == 0) {
                map[x][y] = dice[2];
            }
            else {
                dice[2] = map[nx][ny];
                map[nx][ny] = 0;
            }

            System.out.println(dice[0]); // 윗면 출력
        }
    }

    static void moveDice(int direction) {
        if(direction == 1) {
            dice = new int[]{dice[3], dice[0], dice[1], dice[2], dice[4], dice[5]}; // 동
        }
        if(direction == 2) {
            dice = new int[]{dice[1], dice[2], dice[3], dice[0], dice[4], dice[5]}; // 서
        }
        if(direction == 3) {
            dice = new int[]{dice[4], dice[1], dice[5], dice[3], dice[2], dice[0]}; // 북
        }
        if(direction == 4) {
            dice = new int[]{dice[5], dice[1], dice[4], dice[3], dice[0], dice[2]}; // 남
        }
    }
}