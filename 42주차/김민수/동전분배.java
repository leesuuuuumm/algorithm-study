import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 동전분배 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            int n = Integer.parseInt(br.readLine());
            int[][] coins = new int[n][2];
            int sum = 0;

            for (int j = 0; j < n; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                coins[j][0] = Integer.parseInt(st.nextToken());
                coins[j][1] = Integer.parseInt(st.nextToken());
                sum += (coins[j][0] * coins[j][1]);
            }

            if (sum % 2 != 0) {
                sb.append("0\n");
                continue;
            }

            int target = sum / 2;
            boolean[] dp = new boolean[target + 1];
            dp[0] = true;

           for(int [] coin: coins){
               int value=coin[0];
               int count=coin[1];
               for(int j=target;j>=value;j--){
                   if(dp[j-value]){
                       for(int k=0;k<count&&j+k*value<=target;k++){
                           dp[j+k*value]=true;
                       }
                   }
               }
               if(dp[target])
                   break;
           }
            sb.append(dp[target] ? "1\n" : "0\n");
        }

        System.out.print(sb);
    }
}