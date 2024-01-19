/*
알고리즘 코딩테스트 자바 9장
백준 1328
날짜 2024.01.18
*/
import java.io.*;
import java.util.*;

public class Beakjoon_1328 {
	static int N;
	static int L;
	static int R;
	static long MOD=1000000007;
	static long[][][] dp;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		L=sc.nextInt();
		R=sc.nextInt();
		dp=new long[N+1][L+1][R+1];
		dp[1][1][1]=1;//빌딩 1개를 놓는 방법, 이외에 1개 건물을 놓는 방법은 없음. 0개
		for(int i=2;i<=N;i++) {
			for(int j=1;j<=L;j++) {
				for(int k=1;k<=R;k++) {
					dp[i][j][k]=(dp[i-1][j-1][k]+dp[i-1][j][k-1]+(dp[i-1][j][k]*(i-2)))%MOD;					
				}
				//System.out.println();
			}
			//System.out.println();
		}
		System.out.print(dp[N][L][R]);
	}

}
