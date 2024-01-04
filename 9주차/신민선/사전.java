/*
알고리즘 코딩테스트 자바 10장
백준 1256
날짜 2024.01.04
*/
import java.io.*;
import java.util.*;

public class Beakjoon_1256 {
	static int N;
	static int M;
	static int K;
	static long[][] dp;
	static char[] S;
	static int index=0;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		M=sc.nextInt();
		K=sc.nextInt();
		int size=N+M;
		S=new char[N+M];
		
		dp=new long[202][202];
		for(int i=0;i<=100;i++) {
			dp[i][1]=i;
			dp[i][0]=1;
			dp[i][i]=1;
		}
		for(int i=2;i<=200;i++) {
			for(int j=1;j<=i;j++) {
				dp[i][j]=dp[i-1][j-1]+dp[i-1][j];
				if(dp[i][j]>1000000000) dp[i][j]=1000000001;
			}
		}
		
		if(dp[N+M][M]<K)
			System.out.println("-1");
		else {
			while(!(N==0 && M==0)) {
				if(dp[N-1+M][M]>=K) {
					System.out.print("a");
					N--;
				}
				else {
					System.out.print("z");
					K=(int) (K-dp[N-1+M][M]);
					M--;
				}
			}
		}
	}
}
