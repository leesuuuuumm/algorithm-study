/*
알고리즘 코딩테스트 자바 11장
백준 10844
날짜 2024.01.16
*/
import java.io.*;
import java.util.*;
public class Beakjoon_10844 {
	static long[][] dp;
	static int MOD=1000000000;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();
		dp=new long[N+1][11];
		for(int i=1;i<=9;i++) {
			dp[1][i]=1;
		}
		for(int i=2;i<=N;i++) {
			dp[i][0]=dp[i-1][1];
			dp[i][9]=dp[i-1][8];
			for(int j=1;j<=8;j++) {
				dp[i][j]=(dp[i-1][j-1]+dp[i-1][j+1])%MOD;
			}
		}
		long sum=0;
		for(int i=0;i<=9;i++) {
			sum=(sum+dp[N][i])%MOD;
		}
		System.out.println(sum);
	}

}
