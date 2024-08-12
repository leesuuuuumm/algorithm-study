import java.io.*;
import java.util.*;

public class 점프 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[] smallStones = new boolean[N + 1];
        for (int i = 0; i < M; i++) {
            smallStones[Integer.parseInt(br.readLine())] = true;
        }

        int[][] dp = new int[N + 1][Math.min(N, 150) + 1];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        dp[1][0] = 0;

        for(int i=1;i<=N;i++){
            if(smallStones[i])
                continue;
            for(int j=1;j<=Math.min(i-1, 150);j++){
                if(i-j>0){
                    //그대로
                    if(dp[i-j][j]!=Integer.MAX_VALUE){
                        dp[i][j]=Math.min(dp[i][j],dp[i-j][j]+1);
                    }
                    //1 증가
                    if(j-1>=0&&dp[i-j][j-1]!=Integer.MAX_VALUE){
                        dp[i][j]=Math.min(dp[i][j], dp[i-j][j-1]+1);
                    }
                    //1 감소
                    if(j+1<=150&&dp[i-j][j+1]!=Integer.MAX_VALUE){
                        dp[i][j]=Math.min(dp[i][j], dp[i-j][j+1]+1);
                    }
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int j = 1; j <= Math.min(N - 1, 150); j++) {
            result = Math.min(result, dp[N][j]);
        }

        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }
}