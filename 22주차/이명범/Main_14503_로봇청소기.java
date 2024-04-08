import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, map[][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        work(r, c, dir);
        System.out.println(result);
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int result = 0;
    private static void work(int r, int c, int dir) {
        if (r < 0 || r >= N || c < 0 || c >= M) return;
        if (map[r][c] == 1) return;

        if (map[r][c] == 0) {
            map[r][c] = -1;
            result++;
        }

        if (isClean(r, c)) {
            if (!canMoveBack(r, c, dir)) return;
            work(r - dr[dir], c - dc[dir], dir);
        }

        for (int i = 0; i < 4; i++) {
            dir = (dir + 3) % 4;

            int nr = r + dr[dir];
            int nc = c + dc[dir];

            if (map[nr][nc] != 0) continue;

            work(nr, nc, dir);
            break;
        }
    }

    private static boolean isClean(int r, int c) {
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (map[nr][nc] != 0) continue;

            return false;
        }
        return true;
    }

    private static boolean canMoveBack(int r, int c, int dir) {
        int nr = r - dr[dir];
        int nc = c - dc[dir];

        if (map[nr][nc] == 1) return false;

        return true;
    }
}