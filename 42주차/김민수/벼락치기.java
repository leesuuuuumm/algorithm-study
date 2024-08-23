import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 벼락치기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int total=Integer.parseInt(st.nextToken());
        int[] dp=new int[total+1];
        Arrays.fill(dp, -1);
        dp[0]=0;

        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            int time=Integer.parseInt(st.nextToken());
            int score=Integer.parseInt(st.nextToken());
            for(int k=total;k>=time;k--){
                if(dp[k-time]>=0){
                    dp[k]=Math.max(dp[k-time]+score, dp[k]);
                }
            }
        }

        int max=0;
        for(int i=0;i<=total;i++){
            max=Math.max(dp[i], max);
        }
        System.out.println(max);
    }
}
