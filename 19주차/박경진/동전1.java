import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] coins = new int[n];
        int[] dp = new int[k+1];

        for(int i = 0; i < n; i++){
            coins[i] = sc.nextInt();
        }

        for(int i = 0; i < n; i++){
            for(int j = 1; j <= k; j++){
                if(j > coins[i]){
                    dp[j] = dp[j] + dp[j-coins[i]];
                }
                else if(j == coins[i]){
                    dp[j]++;
                }
            }
        }

        System.out.println(dp[k]);
    }
}
