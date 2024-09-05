import java.io.*;
import java.util.*;

public class 징검다리 {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine());
        String[] st=br.readLine().split(" ");
        int[] rock=new int[N];
        for(int i=0;i<N;i++){
            rock[i]=Integer.parseInt(st[i]);
        }
        int[] dp=new int[N];
        Arrays.fill(dp, 1);
        for(int i=0;i<N;i++){
            int max=0;
            for(int j=0;j<i;j++){
                if(rock[i]>rock[j]){
                    max=Math.max(max, dp[j]);
                }
            }
            if(max>0){
                dp[i]+=max;
            }
        }
        int result=0;
        for(int i=0;i<N;i++){
            result=Math.max(result, dp[i]);
        }
        System.out.println(result);
    }
}