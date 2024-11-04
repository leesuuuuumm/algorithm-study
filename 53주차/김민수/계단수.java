import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 계단수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		long[][][] dp=new long[N+1][10][1<<10];
		//길이가 t이고, 마지막 자리가 𝑘이며, 숫자의 등장 상태가 visited일 때 계단 수의 개수를 저장합니다.
		for(int i=1;i<=9;i++){
			dp[1][i][1<<i]=1; //길이가 1이고 마지막 자리가 i인 계단 수
		}
		int mod=1000000000;

		for(int t=2;t<=N;t++){
			for(int k=0;k<=9;k++){
				for(int visited=0;visited<(1<<10);visited++){
					int nextVisited=visited|(1<<k); //현재 k칸 반영하기 위해
					if(k==0)
						dp[t][k][nextVisited]+=dp[t-1][k+1][visited]%mod;
					else if(k==9)
						dp[t][k][nextVisited]+=dp[t-1][k-1][visited]%mod;
					else
						dp[t][k][nextVisited]+=dp[t-1][k-1][visited]%mod+dp[t-1][k+1][visited]%mod;

				}
			}
		}
		long sum=0;
		for(int i=0;i<=9;i++){
			sum+=dp[N][i][(1<<10)-1]%mod;
			sum%=mod;
		}
		System.out.println(sum);
	}
}
