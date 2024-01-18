/*
알고리즘 코딩테스트 자바 11장
백준 2342
날짜 2024.01.18
*/
import java.io.*;
import java.util.*;
public class Beakjoon_2342 {
	static ArrayList<Integer> table;
	static int[][][] dp;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		table=new ArrayList<>();
		dp=new int[100001][5][5];//N번째, 왼쪽 발의 위치, 오른쪽 발의 위치일 때 최솟값.
		int[][] mp= {{0,2,2,2,2},{2,1,3,4,3},{2,3,1,3,4},{2,4,3,1,3},{2,3,4,3,1}};
		int n=0;
		int s=1;
		for(int i=0;i<100001;i++) {
			for(int j=0;j<5;j++) {
				for(int k=0;k<5;k++) {
					dp[i][j][k]=100001*4;
				}
			}
		}
		dp[0][0][0]=0;
		while(true) {
			n=sc.nextInt();
			if(n==0) 
				break;
			for(int i=0;i<5;i++) {
				if(n==i)
					continue;
				for(int j=0;j<5;j++) {
					dp[s][i][n]=Math.min(dp[s-1][i][j] + mp[j][n], dp[s][i][n]);
				}
			}
			for(int j=0;j<5;j++) {
				if(n==j)
					continue;
				for(int i=0;i<5;i++) {
					dp[s][n][j]=Math.min(dp[s-1][i][j]+mp[i][n], dp[s][n][j]);
					
				}
			}
			s++;
		}
		s--;
		
		int min=Integer.MAX_VALUE;
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				min=Math.min(min, dp[s][i][j]);
			}
		}
		System.out.println(min);
	}

}
