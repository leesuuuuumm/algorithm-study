import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /*

     */
    static int r, c;
    static char[][] board;
    static int[] alpha;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int cnt, max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        board = new char[r][c];
        alpha = new int[26];
        max = 1;

        for (int i = 0; i < r; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < c; j++) {
                board[i][j] = tmp.charAt(j);
            }
        }

        cnt = 1;
        alpha[board[0][0] - 'A']++;
        dfs(0, 0);

        System.out.println(max);
    }

    static void dfs(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
                continue;
            }

            if (alpha[board[nx][ny] - 'A'] != 0) {
                continue;
            }

            // 백트래킹
            alpha[board[nx][ny] - 'A']++;
            cnt++;
            max = Math.max(max, cnt); // 최댓값 갱신
            dfs(nx, ny);
            alpha[board[nx][ny] - 'A']--;
            cnt--;
        }
    }
}