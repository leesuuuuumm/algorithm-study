import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 포도주시식 {
    static int[] val;
    static long[] dp;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        n=Integer.parseInt(br.readLine());
        val=new int[n+1];
        dp=new long[n+1];
        for(int i=1;i<=n;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            val[i]=Integer.parseInt(st.nextToken());
        }
        if(n==1){
            System.out.println(val[1]);
            return;
        }else if(n==2){
            System.out.println(val[1]+val[2]);
            return;
        }else{
            dp();
        }
        long max=Long.MIN_VALUE;
        for(int i=0;i<=n;i++){
            max=Math.max(dp[i], max);
        }
        System.out.println(max);
    }
    public static void dp(){
        dp[1]=val[1];
        dp[2]=val[1]+val[2];
        for(int i=3;i<=n;i++){
            long first=dp[i-3]+val[i-1]+val[i]; //
            long second=dp[i-2]+val[i];
            dp[i]=Math.max(dp[i-3]+val[i-1]+val[i],dp[i-2]+val[i]);
            if(i>4) {
                long third = dp[i - 4] + val[i - 1] + val[i];
                dp[i]=Math.max(dp[i],third);
            }

        }
    }
}
