import java.io.*;
import java.util.*;

public class 통근버스 {
    static int N;
    static String[] st;
    static long result;
    static int[] input;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        st = br.readLine().split(" ");
        result = 0;
        input = new int[N + 1];
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st[i]);

        }
        dp = new int[N + 1][N + 1]; //dp[x][j]: j보다 오른쪽에서 x보다 작은 수
        for (int j = N - 2; j >= 0; j--) {
            for (int x = 1; x <= N; x++) {
                if (input[j + 1] < x) {
                    dp[x][j] = dp[x][j + 1] + 1;
                } else {
                    dp[x][j] = dp[x][j + 1];
                }
            }
        }

        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                if (input[i] < input[j]) {
                    result += dp[input[i]][j]; //A_i>A_K=k
                }
            }
        }
        System.out.println(result);
    }


}