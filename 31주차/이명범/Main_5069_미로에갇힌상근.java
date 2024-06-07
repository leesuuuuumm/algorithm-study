import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] dr = {-1, 0, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 0, -1, -1};

    public static void main(String[] args) throws IOException {
        int[][][] dp = new int[15][21][21];

        dp[0][10][10] = 1;
        
        for (int i = 1; i < 15; i++) {

            for (int cr = 0; cr < 21; cr++) {
                for (int cc = 0; cc < 21; cc++) {
                    for (int dir = 0; dir < 6; dir++) {
                        int nr = cr + dr[dir];
                        int nc = cc + dc[dir];

                        if (nr < 0 || nr >= 21 || nc < 0 || nc >= 21) continue;

                        dp[i][cr][cc] += dp[i - 1][nr][nc];
                    }
                }
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < T; tc++) {
            int n = Integer.parseInt(br.readLine());

            sb.append(dp[n][10][10]).append("\n");
        }

        System.out.println(sb);
    }
}