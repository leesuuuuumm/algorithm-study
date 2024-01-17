/*
알고리즘 코딩테스트 자바 11장
백준 1915
날짜 2024.01.17
*/
import java.io.*;
import java.util.*;
public class Beakjoon_1915 {
	static long[][] dp;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();
		int M=sc.nextInt();
		long max=0;
		dp=new long[N+1][M+1];
		//System.out.println(N+" "+M);
		for(int i=0;i<N;i++) {
			String line=sc.next();
			for(int j=0;j<M;j++) {
				dp[i][j]=Long.parseLong(String.valueOf(line.charAt(j)));
				//System.out.print(table[i][j]+" ");
				if(dp[i][j]==1 && i>0 && j>0) {
					dp[i][j]=Math.min(Math.min(dp[i-1][j-1], dp[i-1][j]), dp[i][j-1])+dp[i][j];
				}
				
				
				max=(int) Math.max(max, dp[i][j]);
			}
			//System.out.println();
		}
		System.out.println(max*max);
		
	}

}
