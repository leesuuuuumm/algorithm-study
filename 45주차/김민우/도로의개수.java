import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 도로의개수 {
    static int N,M,K,a,b,c,d;
    static long[][] dp;
    static boolean[][][] road;
    static boolean col, row;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // col
        M = Integer.parseInt(st.nextToken()); // row
        K = Integer.parseInt(br.readLine());
        road = new boolean[M+1][N+1][2];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()); // y
            b = Integer.parseInt(st.nextToken()); // x
            c = Integer.parseInt(st.nextToken()); // y
            d = Integer.parseInt(st.nextToken()); // x
            if (a < c || b < d) { // 더 작은 쪽에서 (왼쪽이나 위쪽에 있는 좌표에서) 연결해주기 위함
                if (a < c) { // 왼쪽에 있는 좌표 기준으로 아래로 연결된 도로가 공사중임
                    road[b][a][0] = true;
                }
                else { // 위에 있는 좌표 기준으로 아래로 연결된 도로가 공사중임
                    road[b][a][1] = true;
                }
            } else {
                if (c < a) { // 왼쪽에 있는 좌표 기준으로 아래로 연결된 도로가 공사중임
                    road[d][c][0] = true;
                }
                else { // 위에 있는 좌표 기준으로 아래로 연결된 도로가 공사중임
                    road[d][c][1] = true;
                }
            }
        }
        dp = new long[M+1][N+1];
        for (int i = 1; i <= N; i++) { // 첫 경로 표시, 갈 수 없을 때까지 1개의 경로를 가짐
            if (road[0][i-1][0]) break;
            dp[0][i] = 1;
        }
        for (int i = 1; i <= M; i++) { // 첫 경로 표시, 갈 수 없을 때까지 1개의 경로를 가짐
            if(road[i-1][0][1]) break;
            dp[i][0] = 1;
        }
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                if(!road[i][j-1][0]) dp[i][j] += dp[i][j-1]; // 왼쪽 도로가 공사중이 아닐 경우
                if(!road[i-1][j][1]) dp[i][j] += dp[i-1][j]; // 위쪽 도로가 공사중이 아닐 경우
            }
        }
        System.out.println(dp[M][N]);
    }
}