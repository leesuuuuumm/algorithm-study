/*
알고리즘 코딩테스트 자바 11장
백준 9252
날짜 2024.01.17
*/
import java.io.*;
import java.util.*;
public class Beakjoon_9252 {
	static long[][] dp;
	static char[] A;
	static char[] B;
	static char[] result;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		A=br.readLine().toCharArray();
		B=br.readLine().toCharArray();
		
		dp=new long[A.length+1][B.length+1];
		
		for(int i=1;i<=A.length;i++) {
			for(int j=1;j<=B.length;j++) {
				if(A[i-1]==B[j-1]) {
					dp[i][j]=dp[i-1][j-1]+1;
				}
				else {
					dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		long LCS=dp[A.length][B.length];
		System.out.println(LCS);
		result=new char[(int) LCS];
		getResult(A.length,B.length);
		for(int i=(int) (LCS-1);i>=0;i--) {
			System.out.print(result[i]);
		}
	}
	static void getResult(int i, int j) {
		int index=0;
		while(i>0 && j>0) {
			if(dp[i][j]==dp[i][j-1]) {
				j--;
			}
			else if(dp[i][j]==dp[i-1][j]){
				i--;
			}
			else {
				result[index]=A[i-1];
				//System.out.println(result[i]);
				i--;
				j--;
				index++;
			}
		}
	}

}
