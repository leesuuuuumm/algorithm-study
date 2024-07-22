import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int H, W, L;
    static char[][] map;
    static char[] word;
    static long[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new char[H][W];
        word = new char[L];
        dp = new long[L][H][W];

        for (int i = 0; i < H; i++) {
            String str = br.readLine();
            for (int j = 0; j < W; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        String w = br.readLine();
        for (int i = 0; i < L; i++) {
            word[i] = w.charAt(i);
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] == word[L - 1]) dp[L - 1][i][j]++;
            }
        }

        for (int idx = L - 2; idx >= 0; idx--) {
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (map[i][j] == word[idx]) {
                        for (int k = 0; k < 8; k++) {
                            int nr = i + dr[k];
                            int nc = j + dc[k];

                            if (nr < 0 || nr >= H || nc < 0 || nc >= W) continue;

                            dp[idx][i][j] += dp[idx + 1][nr][nc];
                        }
                    }
                }
            }
        }

        long result = 0;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                result += dp[0][i][j];
            }
        }

        System.out.println(result);
    }

    static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};

}