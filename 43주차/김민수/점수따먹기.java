import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 점수따먹기 {
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());
        int[][] map=new int[n+1][m+1];

        for(int i=1;i<=n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=1;j<=m;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp=new int[n+1][m+1];
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                dp[i][j]=dp[i][j-1]+map[i][j];
            }
        }

        int max=Integer.MIN_VALUE;
        for(int x1=1;x1<=m;x1++){
            for(int x2=x1;x2<=m;x2++){
                int sum=0;
                for(int k=1;k<=n;k++){
                    int row=dp[k][x2]-dp[k][x1-1];
                    sum+=row;
                    if (row > sum) {
                        sum=row;
                    }
                    max=Math.max(max, sum);
                }
            }
        }

        System.out.println(max);
    }
}
