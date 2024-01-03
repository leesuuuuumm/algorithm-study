/*
알고리즘 코딩테스트 자바 10장
백준 11051
날짜 2024.01.03
*/
import java.io.*;
import java.util.*;

public class Beakjoon_11051 {
	static int N;
	static int K;
	static int[][] dp;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();
		int K=sc.nextInt();
		dp=new int[N+1][N+1];
		for(int i=0;i<=N;i++) {
			dp[i][0]=1;
			dp[i][1]=i;
			dp[i][i]=1;
		}
		for(int i=2;i<=N;i++) {
			for(int j=1;j<i;j++) {
				dp[i][j]=(dp[i-1][j-1]+dp[i-1][j])%10007;
			}
		}
		System.out.println(dp[N][K]);
	}

}
