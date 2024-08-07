import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 성냥개비 {
    public static void main(String[] args) throws IOException {
        long[] dp = new long[101];
        int[] arr = {1, 7, 4, 2, 0, 8};

        Arrays.fill(dp, Long.MAX_VALUE);


        dp[2] = 1;
        dp[3] = 7;
        dp[4] = 4;
        dp[5] = 2;
        dp[6] = 6;
        dp[7] = 8;
        dp[8] = 10;

        for (int i = 9; i <= 100; i++) {
            for (int j = 2; j <= 7; j++) {
                String temp = String.valueOf(dp[i - j]) + String.valueOf(arr[j - 2]);
                dp[i] = Math.min(Long.parseLong(temp), dp[i]);
            }
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n=Integer.parseInt(br.readLine());
            StringBuilder sb=new StringBuilder();
            sb.append(dp[n]+" ");
            if(n%2==0){
                for(int k=0;k<n/2;k++){
                    sb.append("1");
                }
            }else{
                sb.append("7");
                for(int k=0;k<(n-3)/2;k++){
                    sb.append("1");
                }
            }
            System.out.println(sb);
        }
    }
}
